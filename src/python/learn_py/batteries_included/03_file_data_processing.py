import csv
import json
from collections import defaultdict, Counter
from datetime import datetime


class DataProcessor:
    """Process various data formats using standard library."""

    def __init__(self):
        self.data = []

    def load_csv(self, filename, delimiter=','):
        """Load CSV data with automatic type detection."""
        self.data = []

        with open(filename, 'r') as file:
            reader = csv.DictReader(file, delimiter=delimiter)

            for row in reader:
                # Try to convert numeric strings to numbers
                processed_row = {}
                for key, value in row.items():
                    processed_row[key] = self._convert_value(value)
                self.data.append(processed_row)

        print(f"Loaded {len(self.data)} rows from {filename}")
        return self.data

    def _convert_value(self, value):
        """Try to convert string values to appropriate types."""
        if not value or value.strip() == '':
            return None

        value = value.strip()

        # Try integer
        try:
            return int(value)
        except ValueError:
            pass

        # Try float
        try:
            return float(value)
        except ValueError:
            pass

        # Try boolean
        if value.lower() in ('true', 'false'):
            return value.lower() == 'true'

        # Try date (simple format)
        try:
            return datetime.strptime(value, '%Y-%m-%d')
        except ValueError:
            pass

        # Return as string
        return value

    def save_csv(self, filename, data=None):
        """Save data to CSV file."""
        if data is None:
            data = self.data

        if not data:
            print("No data to save")
            return

        with open(filename, 'w', newline='') as file:
            fieldnames = data[0].keys()
            writer = csv.DictWriter(file, fieldnames=fieldnames)
            writer.writeheader()
            writer.writerows(data)

        print(f"Saved {len(data)} rows to {filename}")

    def filter_data(self, condition_func):
        """Filter data based on a condition function."""
        return [row for row in self.data if condition_func(row)]

    def group_by(self, field):
        """Group data by a specific field."""
        groups = defaultdict(list)
        for row in self.data:
            key = row.get(field)
            if key is not None:
                groups[key].append(row)
        return dict(groups)

    def summarize(self, group_field, value_field, operation='sum'):
        """Summarize data by grouping and aggregating."""
        groups = self.group_by(group_field)
        summary = {}

        for group_key, rows in groups.items():
            values = [row[value_field] for row in rows if row.get(value_field) is not None]

            if operation == 'sum':
                summary[group_key] = sum(values)
            elif operation == 'avg':
                summary[group_key] = sum(values) / len(values) if values else 0
            elif operation == 'count':
                summary[group_key] = len(values)
            elif operation == 'max':
                summary[group_key] = max(values) if values else 0
            elif operation == 'min':
                summary[group_key] = min(values) if values else 0

        return summary

    def to_json(self, filename):
        """Export data to JSON file."""
        with open(filename, 'w') as file:
            json.dump(self.data, file, indent=2, default=str)
        print(f"Data exported to {filename}")


# Example usage
processor = DataProcessor()

# Create sample data
sample_data = [
    {'name': 'Alice', 'department': 'Sales', 'salary': 50000, 'hire_date': '2020-01-15'},
    {'name': 'Bob', 'department': 'Engineering', 'salary': 75000, 'hire_date': '2019-03-10'},
    {'name': 'Charlie', 'department': 'Sales', 'salary': 45000, 'hire_date': '2021-06-01'},
    {'name': 'Diana', 'department': 'Engineering', 'salary': 80000, 'hire_date': '2018-11-20'},
]

# Save sample data
processor.data = sample_data
processor.save_csv('employees.csv')

# Load and process
processor.load_csv('employees.csv')

# Filter high earners
high_earners = processor.filter_data(lambda row: row['salary'] > 60000)
print("High earners:", [p['name'] for p in high_earners])

# Summarize by department
dept_summary = processor.summarize('department', 'salary', 'avg')
print("Average salary by department:", dept_summary)

# Export to JSON
processor.to_json('employees.json')