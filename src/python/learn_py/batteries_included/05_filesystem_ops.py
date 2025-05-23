import os
import shutil
import zipfile
import tarfile
from pathlib import Path
import hashlib
from datetime import datetime


def organize_files_by_extension(source_dir, target_dir):
    """Organize files into folders by their extension."""
    source = Path(source_dir)
    target = Path(target_dir)
    target.mkdir(exist_ok=True)

    file_counts = {}

    for file_path in source.rglob('*'):
        if file_path.is_file():
            extension = file_path.suffix.lower()
            if not extension:
                extension = 'no_extension'

            # Create folder for this extension
            ext_folder = target / extension[1:]  # Remove the dot
            ext_folder.mkdir(exist_ok=True)

            # Move file
            new_path = ext_folder / file_path.name

            # Handle duplicates
            counter = 1
            while new_path.exists():
                stem = file_path.stem
                new_path = ext_folder / f"{stem}_{counter}{extension}"
                counter += 1

            shutil.move(str(file_path), str(new_path))

            # Count files
            file_counts[extension] = file_counts.get(extension, 0) + 1

    print("Files organized:")
    for ext, count in file_counts.items():
        print(f"  {ext}: {count} files")


def _get_file_hash(file_path):
    """Calculate SHA-256 hash of a file."""
    hash_sha256 = hashlib.sha256()
    with open(file_path, "rb") as f:
        for chunk in iter(lambda: f.read(4096), b""):
            hash_sha256.update(chunk)
    return hash_sha256.hexdigest()


def extract_archive(archive_path, extract_to=None):
    """Extract various archive formats."""
    archive = Path(archive_path)

    if extract_to is None:
        extract_to = archive.parent / archive.stem

    extract_path = Path(extract_to)
    extract_path.mkdir(exist_ok=True)

    if archive.suffix.lower() == '.zip':
        with zipfile.ZipFile(archive, 'r') as zip_ref:
            zip_ref.extractall(extract_path)
    elif archive.suffix.lower() in ['.tar', '.gz', '.bz2']:
        with tarfile.open(archive, 'r:*') as tar_ref:
            tar_ref.extractall(extract_path)
    else:
        print(f"Unsupported archive format: {archive.suffix}")
        return None

    print(f"Archive extracted to: {extract_path}")
    return extract_path


def get_directory_info(directory):
    """Get comprehensive information about a directory."""
    path = Path(directory)

    if not path.exists():
        return None

    info = {
        'path': str(path.absolute()),
        'total_files': 0,
        'total_directories': 0,
        'total_size': 0,
        'file_types': {},
        'largest_files': [],
        'oldest_file': None,
        'newest_file': None
    }

    files_with_dates = []

    for item in path.rglob('*'):
        if item.is_file():
            info['total_files'] += 1

            # Size
            size = item.stat().st_size
            info['total_size'] += size

            # File type
            extension = item.suffix.lower()
            info['file_types'][extension] = info['file_types'].get(extension, 0) + 1

            # For largest files and date tracking
            files_with_dates.append({
                'path': item,
                'size': size,
                'modified': datetime.fromtimestamp(item.stat().st_mtime)
            })

        elif item.is_dir():
            info['total_directories'] += 1

    # Find largest files
    info['largest_files'] = sorted(
        files_with_dates,
        key=lambda x: x['size'],
        reverse=True
    )[:10]

    # Find oldest and newest files
    if files_with_dates:
        sorted_by_date = sorted(files_with_dates, key=lambda x: x['modified'])
        info['oldest_file'] = sorted_by_date[0]
        info['newest_file'] = sorted_by_date[-1]

    return info


def _format_size(size_bytes):
    """Convert bytes to human readable format."""
    if size_bytes == 0:
        return "0 B"

    size_names = ["B", "KB", "MB", "GB", "TB"]
    import math
    i = int(math.floor(math.log(size_bytes, 1024)))
    p = math.pow(1024, i)
    s = round(size_bytes / p, 2)
    return f"{s} {size_names[i]}"


class FileManager:
    """Advanced file operations using standard library."""

    def __init__(self, base_path="."):
        self.base_path = Path(base_path)

    def find_duplicate_files(self, directory):
        """Find duplicate files based on content hash."""
        file_hashes = {}
        duplicates = []

        for file_path in Path(directory).rglob('*'):
            if file_path.is_file():
                file_hash = _get_file_hash(file_path)

                if file_hash in file_hashes:
                    duplicates.append({
                        'original': file_hashes[file_hash],
                        'duplicate': file_path,
                        'size': file_path.stat().st_size
                    })
                else:
                    file_hashes[file_hash] = file_path

        return duplicates

    def create_backup(self, source_dir, backup_name=None):
        """Create a compressed backup of a directory."""
        if backup_name is None:
            timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
            backup_name = f"backup_{timestamp}"

        source = Path(source_dir)
        backup_path = Path(f"{backup_name}.tar.gz")

        with tarfile.open(backup_path, "w:gz") as tar:
            tar.add(source, arcname=source.name)

        print(f"Backup created: {backup_path}")
        print(f"Backup size: {_format_size(backup_path.stat().st_size)}")

        return backup_path


# Example usage
file_manager = FileManager()

# Get directory information
# info = file_manager.get_directory_info("/path/to/directory")
# if info:
#     print(f"Directory: {info['path']}")
#     print(f"Files: {info['total_files']}")
#     print(f"Directories: {info['total_directories']}")
#     print(f"Total size: {file_manager._format_size(info['total_size'])}")

# Find duplicates
# duplicates = file_manager.find_duplicate_files("/path/to/directory")
# for dup in duplicates:
#     print(f"Duplicate found: {dup['duplicate']} (original: {dup['original']})")

# Create backup
# file_manager.create_backup("/important/directory")