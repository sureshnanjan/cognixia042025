"""
Python's philosophy of "batteries included" means that the standard library provides a
rich set of modules and tools that handle common programming tasks without needing external dependencies. You can build substantial applications using only what comes with Python.
"""
# Create a web server in just a few lines
import http.server
import socketserver
from pathlib import Path


def create_simple_server(port=8000, directory="."):
    """Create a simple HTTP server to serve files."""
    import os
    os.chdir(directory)

    handler = http.server.SimpleHTTPRequestHandler
    with socketserver.TCPServer(("", port), handler) as httpd:
        print(f"Server running at http://localhost:{port}/")
        print("Press Ctrl+C to stop")
        httpd.serve_forever()

# Usage: python -m http.server 8000
# Or programmatically:
# create_simple_server(8080, "./my_website")