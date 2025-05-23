"""
Key Points:
Use built-in functions like min(), max(), sum()
Use any() and all() for boolean checks on sequences
Use join() for combining strings
Take advantage of Python's unpacking features
"""
# Using 'in' operator
if target in items:
    print("Found it!")

# Using built-in functions
numbers = [3, 1, 4, 1, 5, 9, 2, 6]
smallest = min(numbers)
largest = max(numbers)
total = sum(numbers)

# Using join method
words = ["Hello", "world", "from", "Python"]
sentence = " ".join(words)

# More pythonic patterns
# Check if any/all items meet condition
numbers = [2, 4, 6, 8, 10]
all_even = all(num % 2 == 0 for num in numbers)
any_large = any(num > 5 for num in numbers)

# Swapping variables
a, b = b, a

# Multiple assignment
name, age, city = "Alice", 25, "New York"

# Unpacking
first, *middle, last = [1, 2, 3, 4, 5]  # first=1, middle=[2,3,4], last=5