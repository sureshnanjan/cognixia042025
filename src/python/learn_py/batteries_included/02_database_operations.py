import sqlite3
from datetime import datetime
import csv

class SimpleDatabase:
    """A simple database manager using SQLite."""

    def __init__(self, db_name="app.db"):
        self.db_name = db_name
        self.setup_database()

    def setup_database(self):
        """Create tables if they don't exist."""
        with sqlite3.connect(self.db_name) as conn:
            conn.execute('''
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    email TEXT UNIQUE NOT NULL,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
            ''')

            conn.execute('''
                CREATE TABLE IF NOT EXISTS products (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    price REAL NOT NULL,
                    category TEXT,
                    stock INTEGER DEFAULT 0
                )
            ''')

    def add_user(self, name, email):
        """Add a new user to the database."""
        try:
            with sqlite3.connect(self.db_name) as conn:
                conn.execute(
                    "INSERT INTO users (name, email) VALUES (?, ?)",
                    (name, email)
                )
                return True
        except sqlite3.IntegrityError:
            print(f"User with email {email} already exists")
            return False

    def get_users(self):
        """Get all users from database."""
        with sqlite3.connect(self.db_name) as conn:
            conn.row_factory = sqlite3.Row  # Return rows as dictionaries
            cursor = conn.execute("SELECT * FROM users ORDER BY created_at DESC")
            return [dict(row) for row in cursor.fetchall()]

    def export_to_csv(self, table_name, filename):
        """Export table data to CSV file."""
        with sqlite3.connect(self.db_name) as conn:
            conn.row_factory = sqlite3.Row
            cursor = conn.execute(f"SELECT * FROM {table_name}")

            with open(filename, 'w', newline='') as csvfile:
                if cursor.description:
                    fieldnames = [description[0] for description in cursor.description]
                    writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
                    writer.writeheader()

                    for row in cursor:
                        writer.writerow(dict(row))

    def search_users(self, search_term):
        """Search users by name or email."""
        with sqlite3.connect(self.db_name) as conn:
            conn.row_factory = sqlite3.Row
            cursor = conn.execute(
                "SELECT * FROM users WHERE name LIKE ? OR email LIKE ?",
                (f"%{search_term}%", f"%{search_term}%")
            )
            return [dict(row) for row in cursor.fetchall()]


# Example usage
db = SimpleDatabase()
db.add_user("Alice Johnson", "alice@example.com")
db.add_user("Bob Smith", "bob@example.com")

users = db.get_users()
for user in users:
    print(f"{user['name']} ({user['email']}) - {user['created_at']}")

# Export data
db.export_to_csv("users", "users_backup.csv")