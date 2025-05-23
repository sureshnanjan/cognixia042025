"""
Key Points:
Catch specific exceptions, not all exceptions
Use with statements for file operations
Don't use exceptions for normal program flow
Handle errors at the appropriate level
"""
# Specific exception handling
def safe_divide(a, b):
    try:
        return a / b
    except ZeroDivisionError:
        print("Cannot divide by zero!")
        return None

def read_config_file(filename):
    try:
        with open(filename, 'r') as file:
            return file.read()
    except FileNotFoundError:
        print(f"Config file {filename} not found")
        return None
    except PermissionError:
        print(f"Permission denied reading {filename}")
        return None

# Using built-in methods instead of exceptions
def find_item(items, target):
    """Return index of target in items, or -1 if not found."""
    try:
        return items.index(target)
    except ValueError:
        return -1

# Even better - be explicit about what you're checking
def item_position(items, target):
    """Return position of target in items, or None if not found."""
    for index, item in enumerate(items):
        if item == target:
            return index
    return None