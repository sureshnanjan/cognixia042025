"""
Key Points:
Use snake_case for variables and functions
Use descriptive names that explain what the variable contains
Avoid abbreviations unless they're very common (like url, id)
"""
# Clear, descriptive names
celsius = 25
student_name = "John"
numbers = [1, 2, 3, 4, 5]
fahrenheit = celsius * 9 / 5 + 32


# Descriptive function parameters
def calculate_total_cost(base_price, tax_rate, quantity):
    return base_price + (base_price * tax_rate * quantity)
