import configparser
import json
import os
from pathlib import Path
import argparse


class ConfigManager:
    """Manage application configuration from multiple sources."""

    def __init__(self, app_name="myapp"):
        self.app_name = app_name
        self.config = {}
        self.config_sources = []

    def load_from_ini(self, config_file):
        """Load configuration from INI file."""
        config_parser = configparser.ConfigParser()

        try:
            config_parser.read(config_file)

            # Convert to nested dictionary
            for section_name in config_parser.sections():
                self.config[section_name] = {}
                for key, value in config_parser.items(section_name):
                    # Try to convert to appropriate type
                    self.config[section_name][key] = self._convert_value(value)

            self.config_sources.append(f"INI file: {config_file}")

        except Exception as e:
            print(f"Error loading INI config: {e}")

    def load_from_json(self, config_file):
        """Load configuration from JSON file."""
        try:
            with open(config_file, 'r') as f:
                json_config = json.load(f)
                self.config.update(json_config)

            self.config_sources.append(f"JSON file: {config_file}")

        except Exception as e:
            print(f"Error loading JSON config: {e}")

    def load_from_env(self, prefix=None):
        """Load configuration from environment variables."""
        if prefix is None:
            prefix = f"{self.app_name.upper()}_"

        env_config = {}
        for key, value in os.environ.items():
            if key.startswith(prefix):
                config_key = key[len(prefix):].lower()
                env_config[config_key] = self._convert_value(value)

        if env_config:
            self.config.update(env_config)
            self.config_sources.append(f"Environment variables (prefix: {prefix})")

    def load_from_args(self):
        """Load configuration from command line arguments."""
        parser = argparse.ArgumentParser(description=f'{self.app_name} Configuration')

        # Add common arguments
        parser.add_argument('--debug', action='store_true', help='Enable debug mode')
        parser.add_argument('--config', type=str, help='Configuration file path')
        parser.add_argument('--log-level', type=str, default='INFO',
                            choices=['DEBUG', 'INFO', 'WARNING', 'ERROR'],
                            help='Logging level')
        parser.add_argument('--port', type=int, default=8000, help='Server port')
        parser.add_argument('--host', type=str, default='localhost', help='Server host')

        args = parser.parse_args()

        # Convert to dictionary
        args_dict = {k: v for k, v in vars(args).items() if v is not None}
        self.config.update(args_dict)

        self.config_sources.append("Command line arguments")

        return args

    def _convert_value(self, value):
        """Convert string values to appropriate Python types."""
        if isinstance(value, str):
            # Boolean values
            if value.lower() in ('true', 'yes', '1', 'on'):
                return True
            elif value.lower() in ('false', 'no', '0', 'off'):
                return False

            # Numeric values
            try:
                return int(value)
            except ValueError:
                try:
                    return float(value)
                except ValueError:
                    pass

        return value

    def get(self, key, default=None):
        """Get configuration value with optional default."""
        keys = key.split('.')
        value = self.config

        try:
            for k in keys:
                value = value[k]
            return value
        except (KeyError, TypeError):
            return default

    def save_to_json(self, filename):
        """Save current configuration to JSON file."""
        with open(filename, 'w') as f:
            json.dump(self.config, f, indent=2)
        print(f"Configuration saved to {filename}")

    def print_config(self):
        """Print current configuration."""
        print(f"\n{self.app_name} Configuration:")
        print(f"Sources: {', '.join(self.config_sources)}")
        print("\nCurrent settings:")
        self._print_dict(self.config, indent=2)

    def _print_dict(self, d, indent=0):
        """Recursively print dictionary with indentation."""
        for key, value in d.items():
            if isinstance(value, dict):
                print(" " * indent + f"{key}:")
                self._print_dict(value, indent + 2)
            else:
                print(" " * indent + f"{key}: {value}")


# Example configuration files:
# config.ini:
# [database]
# host = localhost
# port = 5432
# name = myapp_db
#
# [api]
# base_url = https://api.example.com
# timeout = 30
# debug = true

# config.json:
# {
#   "database": {
#     "host": "localhost",
#     "port": 5432,
#     "name": "myapp_db"
#   },
#   "api": {
#     "base_url": "https://api.example.com",
#     "timeout": 30,
#     "debug": true
#   }
# }

# Example usage
config = ConfigManager("myapp")

# Load from multiple sources (order matters - later sources override earlier ones)
config.load_from_json("config.json")  # Base configuration
config.load_from_ini("config.ini")  # Override with INI file
config.load_from_env("MYAPP_")  # Override with environment variables
args = config.load_from_args()  # Override with command line args

# Use configuration
db_host = config.get("database.host", "localhost")
api_timeout = config.get("api.timeout", 30)
debug_mode = config.get("debug", False)

print(f"Database host: {db_host}")
print(f"API timeout: {api_timeout}")
print(f"Debug mode: {debug_mode}")

config.print_config()