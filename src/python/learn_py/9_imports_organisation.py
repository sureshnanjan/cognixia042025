"""
Key Points:
Import only what you actually use
Be explicit about where functions come from
Group imports: standard library, third-party, local
Use pathlib instead of os.path for file operations
"""
# Import only what you need
import math
from datetime import datetime, timedelta
from pathlib import Path

# Standard library imports first
import json
import sys

# Third-party imports second
# import requests
# import pandas as pd

# Local imports last
# from my_module import my_function

def calculate_area(radius):
    """Calculate area of a circle."""
    return math.pi * radius ** 2

def get_file_info(filepath):
    """Get information about a file."""
    path = Path(filepath)
    return {
        'name': path.name,
        'size': path.stat().st_size,
        'modified': datetime.fromtimestamp(path.stat().st_mtime)
    }