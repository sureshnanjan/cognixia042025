"""
Key Points:
List comprehensions are more readable for simple transformations
Use enumerate() when you need both index and value
Use zip() to iterate over multiple lists simultaneously
Avoid range(len()) - iterate directly over the list
"""

numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

# List comprehensions
even_numbers = [num for num in numbers if num % 2 == 0]
squared = [num ** 2 for num in numbers]

# Direct iteration
for num in numbers:
    print(num)

# When you need the index, use enumerate
for index, num in enumerate(numbers):
    print(f"Position {index}: {num}")

# Multiple lists with zip
names = ["Alice", "Bob", "Charlie"]
ages = [25, 30, 35]
for name, age in zip(names, ages):
    print(f"{name} is {age} years old")