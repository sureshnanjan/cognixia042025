"""
Key Points:
Use f-strings for modern Python (3.6+)
f-strings can include expressions inside the braces
Use :.2f for formatting numbers to 2 decimal places
"""
name = "Alice"
age = 25
city = "New York"

# f-strings (Python 3.6+) - Most Pythonic
message = f"Hello, my name is {name} and I am {age} years old."
greeting = f"Welcome to {city}!"

# .format() method (if you need Python < 3.6 compatibility)
message = "Hello, my name is {} and I am {} years old.".format(name, age)

# f-strings with expressions
total = 47.50
tip_percentage = 18
tip_amount = f"Tip: ${total * tip_percentage / 100:.2f}"