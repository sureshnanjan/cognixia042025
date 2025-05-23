"""
Key Points:
Write functions that do one thing well
Use docstrings to explain what functions do
Never use mutable objects as default arguments
Use descriptive function names
"""
# Single responsibility functions
def clean_data(data):
    """Remove None values and normalize strings."""
    return [item.strip().lower() for item in data if item is not None]


def calculate_average_length(text_list):
    """Calculate average length of strings in list."""
    if not text_list:
        return 0
    return sum(len(item) for item in text_list) / len(text_list)


def format_statistics(average):
    """Format statistics for display."""
    return f"Average length: {average:.2f}"


# Safe default arguments
def add_item(item, my_list=None):
    if my_list is None:
        my_list = []
    my_list.append(item)
    return my_list


# Using the functions together
def process_data(data):
    """Process data and return formatted statistics."""
    cleaned_data = clean_data(data)
    average = calculate_average_length(cleaned_data)
    formatted_result = format_statistics(average)
    return formatted_result, cleaned_data
