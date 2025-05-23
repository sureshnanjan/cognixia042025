"""
Don't compare boolean values to True or False
Use early returns to avoid deep nesting
Leverage Python's truthiness (empty containers are falsy)
Use conditional expressions for simple assignments
"""
# Direct boolean evaluation
is_valid = True
if is_valid:
    print("Valid")


# No unnecessary else
def check_age(age):
    if age >= 18:
        return "Adult"
    return "Minor"


# Simplified boolean logic
def check_access(user_type, is_premium, account_active):
    if not account_active:
        return False

    if user_type == "admin":
        return True

    return user_type == "user" and is_premium


# Using truthiness of Python objects
def process_items(items):
    if not items:  # Empty lists are falsy
        print("No items to process")
        return

    for item in items:
        print(f"Processing {item}")


# Elegant conditional assignment
status = "premium" if is_premium else "basic"