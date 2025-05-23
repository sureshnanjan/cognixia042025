import unittest
import doctest
import timeit
import cProfile
import pstats
from io import StringIO


class Calculator:
    """A simple calculator for demonstration."""

    def add(self, a, b):
        """
        Add two numbers.

        >>> calc = Calculator()
        >>> calc.add(2, 3)
        5
        >>> calc.add(-1, 1)
        0
        """
        return a + b

    def divide(self, a, b):
        """
        Divide two numbers.

        >>> calc = Calculator()
        >>> calc.divide(10, 2)
        5.0
        >>> calc.divide(1, 0)
        Traceback (most recent call last):
        ...
        ValueError: Cannot divide by zero
        """
        if b == 0:
            raise ValueError("Cannot divide by zero")
        return a / b

    def power(self, base, exponent):
        """Calculate base raised to exponent."""
        return base ** exponent


class TestCalculator(unittest.TestCase):
    """Unit tests for Calculator class."""

    def setUp(self):
        """Set up test fixtures before each test method."""
        self.calc = Calculator()

    def test_add_positive_numbers(self):
        """Test addition of positive numbers."""
        result = self.calc.add(3, 5)
        self.assertEqual(result, 8)

    def test_add_negative_numbers(self):
        """Test addition of negative numbers."""
        result = self.calc.add(-3, -5)
        self.assertEqual(result, -8)

    def test_add_mixed_numbers(self):
        """Test addition of mixed positive and negative numbers."""
        result = self.calc.add(-3, 5)
        self.assertEqual(result, 2)

    def test_divide_normal(self):
        """Test normal division."""
        result = self.calc.divide(10, 2)
        self.assertEqual(result, 5.0)

    def test_divide_by_zero(self):
        """Test division by zero raises ValueError."""
        with self.assertRaises(ValueError):
            self.calc.divide(10, 0)

    def test_power(self):
        """Test power calculation."""
        result = self.calc.power(2, 3)
        self.assertEqual(result, 8)

    def test_power_zero_exponent(self):
        """Test power with zero exponent."""
        result = self.calc.power(5, 0)
        self.assertEqual(result, 1)


class PerformanceTester:
    """Performance testing utilities."""

    def __init__(self):
        self.results = {}

    def time_function(self, func, *args, number=1000, **kwargs):
        """Time a function execution."""

        def wrapper():
            return func(*args, **kwargs)

        # Time the function
        execution_time = timeit.timeit(wrapper, number=number)
        avg_time = execution_time / number

        func_name = func.__name__
        self.results[func_name] = {
            'total_time': execution_time,
            'average_time': avg_time,
            'iterations': number
        }

        print(f"{func_name}:")
        print(f"  Total time: {execution_time:.6f} seconds")
        print(f"  Average time: {avg_time:.6f} seconds per call")
        print(f"  Iterations: {number}")

        return avg_time

    def profile_function(self, func, *args, **kwargs):
        """Profile a function to see detailed performance statistics."""

        # Create a profiler
        profiler = cProfile.Profile()

        # Run the function under profiler
        profiler.enable()
        result = func(*args, **kwargs)
        profiler.disable()

        # Get statistics
        stats_stream = StringIO()
        stats = pstats.Stats(profiler, stream=stats_stream)
        stats.sort_stats('cumulative')
        stats.print_stats()

        print(f"\nProfile for {func.__name__}:")
        print(stats_stream.getvalue())

        return result

    def compare_functions(self, functions, *args, **kwargs):
        """Compare performance of multiple functions."""
        print("Performance Comparison:")
        print("-" * 50)

        results = {}
        for func in functions:
            avg_time = self.time_function(func, *args, **kwargs)
            results[func.__name__] = avg_time
            print()

        # Sort by performance
        sorted_results = sorted(results.items(), key=lambda x: x[1])

        print("Performance Ranking (fastest to slowest):")
        for i, (name, time) in enumerate(sorted_results, 1):
            print(f"{i}. {name}: {time:.6f} seconds")

        return sorted_results


def run_all_tests():
    """Run all types of tests."""

    print("Running Unit Tests:")
    print("=" * 50)

    # Run unit tests
    unittest.main(argv=[''], exit=False, verbosity=2)

    print("\nRunning Docstring Tests:")
    print("=" * 50)

    # Run docstring tests
    doctest_results = doctest.testmod(verbose=True)

    print(f"\nDoctest Results:")
    print(f"Tests run: {doctest_results.attempted}")
    print(f"Failures: {doctest_results.failed}")

    print("\nRunning Performance Tests:")
    print("=" * 50)

    # Performance testing
    calc = Calculator()
    perf_tester = PerformanceTester()

    # Test different implementations
    def fast_power(base, exp):
        return base ** exp

    def slow_power(base, exp):
        result = 1
        for _ in range(abs(exp)):
            result *= base
        return result if exp >= 0 else 1 / result

    # Compare implementations
    perf_tester.compare_functions([fast_power, slow_power], 2, 10, number=10000)

    # Profile a complex function
    def complex_calculation():
        total = 0
        for i in range(1000):
            total += i ** 2
        return total

    perf_tester.profile_function(complex_calculation)


if __name__ == "__main__":
    # Example of running specific tests

    # Run just unit tests
    # unittest.main()

    # Run just doctests
    # doctest.testmod()

    # Run performance comparison
    calc = Calculator()
    perf_tester = PerformanceTester()

    # Time individual functions
    perf_tester.time_function(calc.add, 5, 3, number=100000)
    perf_tester.time_function(calc.power, 2, 10, number=100000)

    print("\nTesting completed!")