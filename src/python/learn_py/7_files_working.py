"""
Key Points:
Always use with statements for file operations
Files are automatically closed even if an error occurs
Process large files line by line to save memory
Handle file errors appropriately
"""


# Using context managers (with statement)
def read_file_content(filename):
    try:
        with open(filename, 'r') as file:
            return file.read()
    except FileNotFoundError:
        print(f"File {filename} not found")
        return None


# Processing files line by line (memory efficient)
def process_large_file(filename):
    try:
        with open(filename, 'r') as file:
            for line_number, line in enumerate(file, 1):
                process_line(line.strip())
    except FileNotFoundError:
        print(f"File {filename} not found")


# Reading all lines when needed
def get_all_lines(filename):
    try:
        with open(filename, 'r') as file:
            return [line.strip() for line in file]
    except FileNotFoundError:
        return []


def process_line(line):
    """Process a single line of text."""
    print(f"Processing: {line}")
