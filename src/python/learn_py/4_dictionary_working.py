"""
Use in dict not in dict.keys()
Use .get() method to avoid KeyError
Use .items() to iterate over key-value pairs
Dictionary comprehensions work like list comprehensions
"""
student_grades = {"Alice": 85, "Bob": 92, "Charlie": 78}

# Direct membership testing
if "Alice" in student_grades:
    print(student_grades["Alice"])

# Using .get() method with default
grade = student_grades.get("David", 0)  # Returns 0 if "David" not found

# Pythonic iteration
for name, grade in student_grades.items():
    print(f"{name}: {grade}")

# Dictionary comprehension
passing_grades = {name: grade for name, grade in student_grades.items() if grade >= 80}

# Using .setdefault() for conditional assignment
student_grades.setdefault("Eve", 0)  # Only adds if "Eve" doesn't exist