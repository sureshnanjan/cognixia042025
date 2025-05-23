import re
import json
from datetime import datetime
from typing import Any, Dict, List, Union
import csv


class ValidationError(Exception):
    """Custom exception for validation errors."""
    pass


class DataValidator:
    """Comprehensive data validation using standard library."""

    def __init__(self):
        self.rules = {}
        self.errors = []

    def add_rule(self, field_name, rule_type, **kwargs):
        """Add a validation rule for a field."""
        if field_name not in self.rules:
            self.rules[field_name] = []

        rule = {'type': rule_type, 'params': kwargs}
        self.rules[field_name].append(rule)

    def validate_required(self, value, **kwargs):
        """Check if value is present and not empty."""
        if value is None or (isinstance(value, str) and value.strip() == ''):
            return False, "Field is required"
        return True, None

    def validate_email(self, value, **kwargs):
        """Validate email format."""
        if not value:
            return True, None  # Let required check handle empty values

        email_pattern = r'^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$'
        if not re.match(email_pattern, value):
            return False, "Invalid email format"
        return True, None

    def validate_length(self, value, min_length=None, max_length=None, **kwargs):
        """Validate string length."""
        if not value:
            return True, None

        length = len(str(value))

        if min_length is not None and length < min_length:
            return False, f"Must be at least {min_length} characters"

        if max_length is not None and length > max_length:
            return False, f"Must not exceed {max_length} characters"

        return True, None

    def validate_numeric(self, value, min_value=None, max_value=None, **kwargs):
        """Validate numeric values."""
        if value is None:
            return True, None

        try:
            num_value = float(value)
        except (ValueError, TypeError):
            return False, "Must be a number"

        if min_value is not None and num_value < min_value:
            return False, f"Must be at least {min_value}"

        if max_value is not None and num_value > max_value:
            return False, f"Must not exceed {max_value}"

        return True, None

    def validate_date(self, value, date_format='%Y-%m-%d', **kwargs):
        """Validate date format."""
        if not value:
            return True, None

        try:
            datetime.strptime(str(value), date_format)
            return True, None
        except ValueError:
            return False, f"Invalid date format. Expected: {date_format}"

    def validate_regex(self, value, pattern, **kwargs):
        """Validate against regex pattern."""
        if not value:
            return True, None

        if not re.match(pattern, str(value)):
            return False, f"Does not match required pattern"
        return True, None

    def validate_choice(self, value, choices, **kwargs):
        """Validate value is in allowed choices."""
        if value is None:
            return True, None

        if value not in choices:
            return False, f"Must be one of: {', '.join(map(str, choices))}"
        return True, None

    def validate_record(self, record):
        """Validate a single record against all rules."""
        record_errors = {}

        for field_name, rules in self.rules.items():
            field_value = record.get(field_name)
            field_errors = []

            for rule in rules:
                rule_type = rule['type']
                rule_params = rule['params']

                # Get validation method
                validator_method = f"validate_{rule_type}"
                if hasattr(self, validator_method):
                    validator = getattr(self, validator_method)
                    is_valid, error_msg = validator(field_value, **rule_params)

                    if not is_valid:
                        field_errors.append(error_msg)

            if field_errors:
                record_errors[field_name] = field_errors

        return record_errors

    def validate_dataset(self, data):
        """Validate an entire dataset."""
        all_errors = []
        valid_records = []

        for i, record in enumerate(data):
            record_errors = self.validate_record(record)

            if record_errors:
                all_errors.append({
                    'record_index': i,
                    'record': record,
                    'errors': record_errors
                })
            else:
                valid_records.append(record)

        return {
            'valid_records': valid_records,
            'invalid_records': all_errors,
            'total_records': len(data),
            'valid_count': len(valid_records),
            'invalid_count': len(all_errors)
        }


class DataCleaner:
    """Clean and normalize data using standard library."""

    def __init__(self):
        self.transformations = []

    def clean_text(self, text):
        """Clean text data."""
        if not isinstance(text, str):
            return text

        # Remove extra whitespace
        text = re.sub(r'\s+', ' ', text).strip()

        # Remove special characters if needed
        # text = re.sub(r'[^\w\s]', '', text)

        return text

    def normalize_email(self, email):
        """Normalize email addresses."""
        if not email:
            return email

        return email.lower().strip()

    def normalize_phone(self, phone):
        """Normalize phone numbers."""
        if not phone:
            return phone

        # Remove all non-digit characters
        digits_only = re.sub(r'\D', '', phone)

        # Format as (XXX) XXX-XXXX if 10 digits
        if len(digits_only) == 10:
            return f"({digits_only[:3]}) {digits_only[3:6]}-{digits_only[6:]}"
        elif len(digits_only) == 11 and digits_only[0] == '1':
            return f"({digits_only[1:4]}) {digits_only[4:7]}-{digits_only[7:]}"

        return phone  # Return original if can't normalize

    def clean_numeric(self, value):
        """Clean numeric values."""
        if value is None:
            return None

        # Remove currency symbols and commas
        if isinstance(value, str):
            value = re.sub(r'[$,]', '', value)

            try:
                return float(value) if '.' in value else int(value)
            except ValueError:
                return None

        return value

    def clean_dataset(self, data, field_cleaners=None):
        """Clean an entire dataset."""
        if field_cleaners is None:
            field_cleaners = {}

        cleaned_data = []

        for record in data:
            cleaned_record = {}

            for field, value in record.items():
                if field in field_cleaners:
                    cleaner_func = field_cleaners[field]
                    cleaned_record[field] = cleaner_func(value)
                else:
                    # Apply general text cleaning
                    if isinstance(value, str):
                        cleaned_record[field] = self.clean_text(value)
                    else:
                        cleaned_record[field] = value

            cleaned_data.append(cleaned_record)

        return cleaned_data


# Example usage and demonstration
def demonstrate_data_validation():
    """Demonstrate data validation capabilities."""

    # Sample data with various issues
    sample_data = [
        {
            'name': 'John Doe',
            'email': 'john@example.com',
            'age': 25,
            'phone': '555-123-4567',
            'salary': 50000,
            'hire_date': '2020-01-15',
            'department': 'Engineering'
        },
        {
            'name': '',  # Missing name
            'email': 'invalid_email',  # Invalid email
            'age': 150,  # Invalid age
            'phone': '555-123-456',  # Invalid phone
            'salary': -5000,  # Negative salary
            'hire_date': '2020-13-45',  # Invalid date
            'department': 'Unknown'  # Invalid department
        },
        {
            'name': 'Jane Smith',
            'email': 'jane@company.com',
            'age': 30,
            'phone': '(555) 987-6543',
            'salary': 75000,
            'hire_date': '2019-06-01',
            'department': 'Sales'
        }
    ]

    # Setup validator
    validator = DataValidator()

    # Add validation rules
    validator.add_rule('name', 'required')
    validator.add_rule('name', 'length', min_length=2, max_length=50)

    validator.add_rule('email', 'required')
    validator.add_rule('email', 'email')

    validator.add_rule('age', 'required')
    validator.add_rule('age', 'numeric', min_value=18, max_value=100)

    validator.add_rule('salary', 'required')
    validator.add_rule('salary', 'numeric', min_value=0)

    validator.add_rule('hire_date', 'required')
    validator.add_rule('hire_date', 'date')

    validator.add_rule('department', 'required')
    validator.add_rule('department', 'choice', choices=['Engineering', 'Sales', 'Marketing', 'HR'])

    # Validate dataset
    validation_results = validator.validate_dataset(sample_data)

    print("Data Validation Results:")
    print("=" * 50)
    print(f"Total records: {validation_results['total_records']}")
    print(f"Valid records: {validation_results['valid_count']}")
    print(f"Invalid records: {validation_results['invalid_count']}")

    print("\nInvalid Records:")
    for invalid_record in validation_results['invalid_records']:
        print(f"\nRecord {invalid_record['record_index']}:")
        for field, errors in invalid_record['errors'].items():
            print(f"  {field}: {', '.join(errors)}")

    # Demonstrate data cleaning
    print("\n\nData Cleaning Demonstration:")
    print("=" * 50)

    cleaner = DataCleaner()

    # Define field-specific cleaners
    field_cleaners = {
        'email': cleaner.normalize_email,
        'phone': cleaner.normalize_phone,
        'salary': cleaner.clean_numeric
    }

    # Clean the data
    cleaned_data = cleaner.clean_dataset(sample_data, field_cleaners)

    print("Original vs Cleaned Data:")
    for i, (original, cleaned) in enumerate(zip(sample_data, cleaned_data)):
        print(f"\nRecord {i}:")
        for field in original:
            if original[field] != cleaned[field]:
                print(f"  {field}: '{original[field]}' -> '{cleaned[field]}'")


# Example of custom validation rules
class CustomValidator(DataValidator):
    """Extended validator with custom rules."""

    def validate_credit_card(self, value, **kwargs):
        """Validate credit card number using Luhn algorithm."""
        if not value:
            return True, None

        # Remove spaces and hyphens
        card_number = re.sub(r'[\s-]', '', str(value))

        # Check if all digits
        if not card_number.isdigit():
            return False, "Credit card must contain only digits"

        # Luhn algorithm
        def luhn_check(card_num):
            digits = [int(d) for d in card_num]
            for i in range(len(digits) - 2, -1, -2):
                digits[i] *= 2
                if digits[i] > 9:
                    digits[i] -= 9
            return sum(digits) % 10 == 0

        if not luhn_check(card_number):
            return False, "Invalid credit card number"

        return True, None

    def validate_password_strength(self, value, min_length=8, require_uppercase=True,
                                   require_lowercase=True, require_digits=True,
                                   require_special=True, **kwargs):
        """Validate password strength."""
        if not value:
            return True, None

        errors = []

        if len(value) < min_length:
            errors.append(f"Must be at least {min_length} characters")

        if require_uppercase and not re.search(r'[A-Z]', value):
            errors.append("Must contain uppercase letter")

        if require_lowercase and not re.search(r'[a-z]', value):
            errors.append("Must contain lowercase letter")

        if require_digits and not re.search(r'\d', value):
            errors.append("Must contain digit")

        if require_special and not re.search(r'[!@#$%^&*(),.?":{}|<>]', value):
            errors.append("Must contain special character")

        if errors:
            return False, "; ".join(errors)

        return True, None


if __name__ == "__main__":
    demonstrate_data_validation()