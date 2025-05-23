import logging
import logging.handlers
from datetime import datetime
import json
import traceback
import functools


class ApplicationLogger:
    """Advanced logging system using standard library."""

    def __init__(self, app_name="MyApp", log_level=logging.INFO):
        self.app_name = app_name
        self.logger = logging.getLogger(app_name)
        self.logger.setLevel(log_level)

        # Prevent duplicate logs
        if not self.logger.handlers:
            self._setup_handlers()

    def _setup_handlers(self):
        """Setup various log handlers."""

        # Console handler
        console_handler = logging.StreamHandler()
        console_handler.setLevel(logging.INFO)
        console_format = logging.Formatter(
            '%(asctime)s - %(name)s - %(levelname)s - %(message)s'
        )
        console_handler.setFormatter(console_format)

        # File handler with rotation
        file_handler = logging.handlers.RotatingFileHandler(
            f'{self.app_name.lower()}.log',
            maxBytes=10 * 1024 * 1024,  # 10MB
            backupCount=5
        )
        file_handler.setLevel(logging.DEBUG)
        file_format = logging.Formatter(
            '%(asctime)s - %(name)s - %(levelname)s - %(funcName)s:%(lineno)d - %(message)s'
        )
        file_handler.setFormatter(file_format)

        # Error file handler
        error_handler = logging.FileHandler(f'{self.app_name.lower()}_errors.log')
        error_handler.setLevel(logging.ERROR)
        error_handler.setFormatter(file_format)

        # Add handlers
        self.logger.addHandler(console_handler)
        self.logger.addHandler(file_handler)
        self.logger.addHandler(error_handler)

    def log_function_calls(self, func):
        """Decorator to log function calls and execution time."""

        @functools.wraps(func)
        def wrapper(*args, **kwargs):
            start_time = datetime.now()

            # Log function call
            args_str = ', '.join([str(arg) for arg in args])
            kwargs_str = ', '.join([f"{k}={v}" for k, v in kwargs.items()])
            params = ', '.join(filter(None, [args_str, kwargs_str]))

            self.logger.info(f"Calling {func.__name__}({params})")

            try:
                result = func(*args, **kwargs)
                execution_time = (datetime.now() - start_time).total_seconds()
                self.logger.info(f"{func.__name__} completed in {execution_time:.3f}s")
                return result

            except Exception as e:
                execution_time = (datetime.now() - start_time).total_seconds()
                self.logger.error(
                    f"{func.__name__} failed after {execution_time:.3f}s: {str(e)}\n"
                    f"Traceback: {traceback.format_exc()}"
                )
                raise

        return wrapper

    def log_performance(self, operation_name):
        """Context manager for performance logging."""

        class PerformanceLogger:
            def __init__(self, logger, name):
                self.logger = logger
                self.name = name
                self.start_time = None

            def __enter__(self):
                self.start_time = datetime.now()
                self.logger.info(f"Starting {self.name}")
                return self

            def __exit__(self, exc_type, exc_val, exc_tb):
                duration = (datetime.now() - self.start_time).total_seconds()
                if exc_type:
                    self.logger.error(f"{self.name} failed after {duration:.3f}s: {exc_val}")
                else:
                    self.logger.info(f"{self.name} completed in {duration:.3f}s")

        return PerformanceLogger(self.logger, operation_name)

    def log_structured_data(self, event_name, data):
        """Log structured data as JSON."""
        log_entry = {
            'timestamp': datetime.now().isoformat(),
            'event': event_name,
            'data': data
        }
        self.logger.info(f"STRUCTURED_LOG: {json.dumps(log_entry)}")


# Example usage
app_logger = ApplicationLogger("DataProcessor")


@app_logger.log_function_calls
def process_data(data):
    """Example function with automatic logging."""
    if not data:
        raise ValueError("No data provided")

    processed = []
    for item in data:
        processed.append(item * 2)

    return processed


@app_logger.log_function_calls
def fetch_user_data(user_id):
    """Simulate fetching user data."""
    import time
    time.sleep(0.5)  # Simulate API call

    return {
        'id': user_id,
        'name': f'User {user_id}',
        'email': f'user{user_id}@example.com'
    }


# Example usage with performance logging
with app_logger.log_performance("Data Processing Pipeline"):
    data = [1, 2, 3, 4, 5]
    result = process_data(data)

    user = fetch_user_data(123)

    # Log structured data
    app_logger.log_structured_data("user_processed", {
        'user_id': user['id'],
        'processing_result': result,
        'success': True
    })

print("Check the log files for detailed information!")