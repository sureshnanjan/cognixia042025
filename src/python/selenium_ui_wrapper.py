import logging
import os
import time
from datetime import datetime
from typing import Callable, List, Optional, Tuple, Union, Any

from selenium.common.exceptions import (
    ElementClickInterceptedException,
    ElementNotInteractableException,
    ElementNotSelectableException,
    ElementNotVisibleException,
    InvalidElementStateException,
    NoAlertPresentException,
    NoSuchElementException,
    StaleElementReferenceException,
    TimeoutException,
    WebDriverException
)
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.common.alert import Alert
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.remote.webdriver import WebDriver
from selenium.webdriver.remote.webelement import WebElement
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.select import Select
from selenium.webdriver.support.ui import WebDriverWait


class SeleniumUIWrapper:
    """
    A comprehensive wrapper for Selenium WebDriver that provides robust element interactions,
    wait strategies, exception handling, and logging capabilities.
    """

    # Default timeout and polling values
    DEFAULT_TIMEOUT = 30
    DEFAULT_POLLING_INTERVAL = 0.5
    
    # Common retry settings
    MAX_RETRIES = 3
    RETRY_DELAY = 1

    def __init__(self, driver: WebDriver, timeout: int = DEFAULT_TIMEOUT, 
                 log_level: int = logging.INFO, screenshots_dir: str = "screenshots"):
        """
        Initialize the UI wrapper with a WebDriver instance.
        
        Args:
            driver: The Selenium WebDriver instance
            timeout: Default timeout in seconds for waits
            log_level: The logging level to use
            screenshots_dir: Directory to save screenshots
        """
        self.driver = driver
        self.timeout = timeout
        self.screenshots_dir = screenshots_dir
        
        # Set up logging
        self.logger = self._setup_logging(log_level)
        
        # Create screenshots directory if it doesn't exist
        if not os.path.exists(screenshots_dir):
            os.makedirs(screenshots_dir)
            
        self.logger.info(f"Initialized UI Wrapper with {driver.name} driver")

    def _setup_logging(self, log_level: int) -> logging.Logger:
        """Set up the logger for the UI wrapper."""
        logger = logging.getLogger('SeleniumUIWrapper')
        logger.setLevel(log_level)
        
        # Create console handler if not already added
        if not logger.handlers:
            console_handler = logging.StreamHandler()
            console_handler.setLevel(log_level)
            formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')
            console_handler.setFormatter(formatter)
            logger.addHandler(console_handler)
        
        return logger
    
    def _take_screenshot(self, name_suffix: str = "") -> str:
        """
        Take a screenshot and save it with a timestamp.
        
        Args:
            name_suffix: Optional suffix to add to the screenshot name
            
        Returns:
            The path to the saved screenshot
        """
        timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
        filename = f"screenshot_{timestamp}{name_suffix}.png"
        filepath = os.path.join(self.screenshots_dir, filename)
        
        try:
            self.driver.save_screenshot(filepath)
            self.logger.info(f"Screenshot saved: {filepath}")
            return filepath
        except Exception as e:
            self.logger.error(f"Failed to scroll element {element_str} into view: {str(e)}")
            self._take_screenshot(f"_scroll_error_{locator[1].replace(' ', '_')}")
            raise
    
    def scroll_to_top(self) -> None:
        """Scroll to the top of the page."""
        self.logger.info("Scrolling to top of page")
        self.execute_js("window.scrollTo(0, 0);")
    
    def scroll_to_bottom(self) -> None:
        """Scroll to the bottom of the page."""
        self.logger.info("Scrolling to bottom of page")
        self.execute_js("window.scrollTo(0, document.body.scrollHeight);")

    # ====== Alert Handling Methods ======
    
    def accept_alert(self, timeout: int = None) -> None:
        """
        Accept an alert/confirm dialog.
        
        Args:
            timeout: Wait timeout in seconds
            
        Raises:
            TimeoutException if no alert appears
            NoAlertPresentException if no alert is present
        """
        timeout = timeout or self.timeout
        self.logger.info("Accepting alert")
        
        try:
            wait = WebDriverWait(self.driver, timeout)
            alert = wait.until(EC.alert_is_present())
            alert_text = alert.text
            alert.accept()
            self.logger.debug(f"Alert accepted. Alert text: {alert_text}")
                
        except (TimeoutException, NoAlertPresentException) as e:
            self.logger.error(f"No alert present to accept: {str(e)}")
            self._take_screenshot("_no_alert_error")
            raise
        except Exception as e:
            self.logger.error(f"Failed to accept alert: {str(e)}")
            self._take_screenshot("_accept_alert_error")
            raise
    
    def dismiss_alert(self, timeout: int = None) -> None:
        """
        Dismiss an alert/confirm dialog.
        
        Args:
            timeout: Wait timeout in seconds
            
        Raises:
            TimeoutException if no alert appears
            NoAlertPresentException if no alert is present
        """
        timeout = timeout or self.timeout
        self.logger.info("Dismissing alert")
        
        try:
            wait = WebDriverWait(self.driver, timeout)
            alert = wait.until(EC.alert_is_present())
            alert_text = alert.text
            alert.dismiss()
            self.logger.debug(f"Alert dismissed. Alert text: {alert_text}")
                
        except (TimeoutException, NoAlertPresentException) as e:
            self.logger.error(f"No alert present to dismiss: {str(e)}")
            self._take_screenshot("_no_alert_error")
            raise
        except Exception as e:
            self.logger.error(f"Failed to dismiss alert: {str(e)}")
            self._take_screenshot("_dismiss_alert_error")
            raise
    
    def get_alert_text(self, timeout: int = None) -> str:
        """
        Get text from an alert/confirm dialog.
        
        Args:
            timeout: Wait timeout in seconds
            
        Returns:
            Text content of the alert
            
        Raises:
            TimeoutException if no alert appears
            NoAlertPresentException if no alert is present
        """
        timeout = timeout or self.timeout
        self.logger.info("Getting alert text")
        
        try:
            wait = WebDriverWait(self.driver, timeout)
            alert = wait.until(EC.alert_is_present())
            text = alert.text
            self.logger.debug(f"Alert text: {text}")
            return text
                
        except (TimeoutException, NoAlertPresentException) as e:
            self.logger.error(f"No alert present to get text from: {str(e)}")
            self._take_screenshot("_no_alert_error")
            raise
        except Exception as e:
            self.logger.error(f"Failed to get alert text: {str(e)}")
            self._take_screenshot("_get_alert_text_error")
            raise
    
    def send_text_to_alert(self, text: str, timeout: int = None) -> None:
        """
        Send text to an alert prompt.
        
        Args:
            text: Text to send to the alert
            timeout: Wait timeout in seconds
            
        Raises:
            TimeoutException if no alert appears
            NoAlertPresentException if no alert is present
        """
        timeout = timeout or self.timeout
        self.logger.info(f"Sending text to alert: {text}")
        
        try:
            wait = WebDriverWait(self.driver, timeout)
            alert = wait.until(EC.alert_is_present())
            alert.send_keys(text)
            alert.accept()
            self.logger.debug(f"Text sent to alert and accepted")
                
        except (TimeoutException, NoAlertPresentException) as e:
            self.logger.error(f"No alert present to send text to: {str(e)}")
            self._take_screenshot("_no_alert_error")
            raise
        except Exception as e:
            self.logger.error(f"Failed to send text to alert: {str(e)}")
            self._take_screenshot("_send_alert_text_error")
            raise
    
    # ====== Element State/Verification Methods ======
    
    def is_element_present(self, locator: Tuple[By, str], 
                         timeout: int = 5) -> bool:
        """
        Check if an element is present in the DOM.
        
        Args:
            locator: Tuple of (By.X, "locator_string")
            timeout: Wait timeout in seconds
            
        Returns:
            True if element is present, False otherwise
        """
        element_str = f"{locator[0]} '{locator[1]}'"
        self.logger.debug(f"Checking if element is present: {element_str}")
        
        try:
            self._find_element(locator, timeout, visible=False)
            self.logger.debug(f"Element is present: {element_str}")
            return True
        except (TimeoutException, NoSuchElementException):
            self.logger.debug(f"Element is not present: {element_str}")
            return False
        except Exception as e:
            self.logger.error(f"Error checking if element is present {element_str}: {str(e)}")
            return False
    
    def is_element_visible(self, locator: Tuple[By, str], 
                         timeout: int = 5) -> bool:
        """
        Check if an element is visible.
        
        Args:
            locator: Tuple of (By.X, "locator_string")
            timeout: Wait timeout in seconds
            
        Returns:
            True if element is visible, False otherwise
        """
        element_str = f"{locator[0]} '{locator[1]}'"
        self.logger.debug(f"Checking if element is visible: {element_str}")
        
        try:
            self._find_element(locator, timeout, visible=True)
            self.logger.debug(f"Element is visible: {element_str}")
            return True
        except TimeoutException:
            self.logger.debug(f"Element is not visible: {element_str}")
            return False
        except Exception as e:
            self.logger.error(f"Error checking if element is visible {element_str}: {str(e)}")
            return False
    
    def is_element_clickable(self, locator: Tuple[By, str], 
                           timeout: int = 5) -> bool:
        """
        Check if an element is clickable (visible and enabled).
        
        Args:
            locator: Tuple of (By.X, "locator_string")
            timeout: Wait timeout in seconds
            
        Returns:
            True if element is clickable, False otherwise
        """
        element_str = f"{locator[0]} '{locator[1]}'"
        self.logger.debug(f"Checking if element is clickable: {element_str}")
        
        try:
            self._find_element(locator, timeout, clickable=True)
            self.logger.debug(f"Element is clickable: {element_str}")
            return True
        except TimeoutException:
            self.logger.debug(f"Element is not clickable: {element_str}")
            return False
        except Exception as e:
            self.logger.error(f"Error checking if element is clickable {element_str}: {str(e)}")
            return False
    
    def wait_for_element_to_disappear(self, locator: Tuple[By, str], 
                                    timeout: int = None) -> bool:
        """
        Wait for an element to disappear.
        
        Args:
            locator: Tuple of (By.X, "locator_string")
            timeout: Wait timeout in seconds
            
        Returns:
            True if element disappeared, False if it's still visible after timeout
        """
        timeout = timeout or self.timeout
        element_str = f"{locator[0]} '{locator[1]}'"
        self.logger.info(f"Waiting for element to disappear: {element_str}")
        
        try:
            wait = WebDriverWait(self.driver, timeout, poll_frequency=self.DEFAULT_POLLING_INTERVAL)
            disappeared = wait.until(EC.invisibility_of_element_located(locator))
            
            if disappeared:
                self.logger.debug(f"Element disappeared: {element_str}")
            else:
                self.logger.debug(f"Element did not disappear: {element_str}")
                
            return disappeared
                
        except TimeoutException:
            self.logger.warning(f"Element did not disappear within timeout: {element_str}")
            self._take_screenshot(f"_element_not_disappeared_{locator[1].replace(' ', '_')}")
            return False
        except Exception as e:
            self.logger.error(f"Error waiting for element to disappear {element_str}: {str(e)}")
            self._take_screenshot(f"_element_disappear_error_{locator[1].replace(' ', '_')}")
            return False
    
    def wait_for_element_with_text(self, locator: Tuple[By, str], 
                                 text: str,
                                 timeout: int = None) -> WebElement:
        """
        Wait for an element containing specific text.
        
        Args:
            locator: Tuple of (By.X, "locator_string")
            text: Text that the element should contain
            timeout: Wait timeout in seconds
            
        Returns:
            WebElement when found
            
        Raises:
            TimeoutException if element with text not found within timeout
        """
        timeout = timeout or self.timeout
        element_str = f"{locator[0]} '{locator[1]}' with text '{text}'"
        self.logger.info(f"Waiting for element with text: {element_str}")
        
        try:
            wait = WebDriverWait(self.driver, timeout, poll_frequency=self.DEFAULT_POLLING_INTERVAL)
            element = wait.until(
                EC.text_to_be_present_in_element(locator, text)
            )
            
            self.logger.debug(f"Found element with text: {element_str}")
            
            # We need to return the actual element, not just the boolean from text_to_be_present
            return self._find_element(locator, 1)  # Short timeout since we know it exists
                
        except TimeoutException:
            self.logger.error(f"Element with text not found: {element_str}")
            self._take_screenshot(f"_element_with_text_not_found_{locator[1].replace(' ', '_')}")
            raise
        except Exception as e:
            self.logger.error(f"Error waiting for element with text {element_str}: {str(e)}")
            self._take_screenshot(f"_element_with_text_error_{locator[1].replace(' ', '_')}")
            raise
    
    def get_element_text(self, locator: Tuple[By, str], 
                       timeout: int = None) -> str:
        """
        Get text content of an element.
        
        Args:
            locator: Tuple of (By.X, "locator_string")
            timeout: Wait timeout in seconds
            
        Returns:
            Text content of the element
            
        Raises:
            Various WebDriver exceptions if operation fails
        """
        timeout = timeout or self.timeout
        element_str = f"{locator[0]} '{locator[1]}'"
        self.logger.info(f"Getting text from element: {element_str}")
        
        try:
            element = self._find_element(locator, timeout)
            
            def get_text_action():
                return element.text
            
            text = self._retry_on_exception(get_text_action)
            self.logger.debug(f"Element text: {text}")
            return text
                
        except Exception as e:
            self.logger.error(f"Failed to get text from element {element_str}: {str(e)}")
            self._take_screenshot(f"_get_text_error_{locator[1].replace(' ', '_')}")
            raise
    
    def get_element_attribute(self, locator: Tuple[By, str], 
                            attribute: str,
                            timeout: int = None) -> str:
        """
        Get attribute value of an element.
        
        Args:
            locator: Tuple of (By.X, "locator_string")
            attribute: Name of the attribute to get
            timeout: Wait timeout in seconds
            
        Returns:
            Value of the attribute
            
        Raises:
            Various WebDriver exceptions if operation fails
        """
        timeout = timeout or self.timeout
        element_str = f"{locator[0]} '{locator[1]}'"
        self.logger.info(f"Getting attribute '{attribute}' from element: {element_str}")
        
        try:
            element = self._find_element(locator, timeout, visible=False)
            
            def get_attribute_action():
                return element.get_attribute(attribute)
            
            value = self._retry_on_exception(get_attribute_action)
            self.logger.debug(f"Attribute '{attribute}' value: {value}")
            return value
                
        except Exception as e:
            self.logger.error(f"Failed to get attribute from element {element_str}: {str(e)}")
            self._take_screenshot(f"_get_attribute_error_{locator[1].replace(' ', '_')}")
            raise
    
    def get_element_css_property(self, locator: Tuple[By, str], 
                               property_name: str,
                               timeout: int = None) -> str:
        """
        Get CSS property value of an element.
        
        Args:
            locator: Tuple of (By.X, "locator_string")
            property_name: Name of the CSS property to get
            timeout: Wait timeout in seconds
            
        Returns:
            Value of the CSS property
            
        Raises:
            Various WebDriver exceptions if operation fails
        """
        timeout = timeout or self.timeout
        element_str = f"{locator[0]} '{locator[1]}'"
        self.logger.info(f"Getting CSS property '{property_name}' from element: {element_str}")
        
        try:
            element = self._find_element(locator, timeout, visible=False)
            
            def get_css_property_action():
                return element.value_of_css_property(property_name)
            
            value = self._retry_on_exception(get_css_property_action)
            self.logger.debug(f"CSS property '{property_name}' value: {value}")
            return value
                
        except Exception as e:
            self.logger.error(f"Failed to get CSS property from element {element_str}: {str(e)}")
            self._take_screenshot(f"_get_css_property_error_{locator[1].replace(' ', '_')}")
            raise
    
    # ====== Additional Wait Methods ======
    
    def wait_for(self, condition_function: Callable, 
               timeout: int = None,
               polling_interval: float = None,
               message: str = "Condition not met") -> Any:
        """
        Wait for a custom condition to be met.
        
        Args:
            condition_function: Function that returns True when condition is met
            timeout: Wait timeout in seconds
            polling_interval: How often to check condition in seconds
            message: Error message if timeout occurs
            
        Returns:
            Result of the condition function
            
        Raises:
            TimeoutException if condition not met within timeout
        """
        timeout = timeout or self.timeout
        polling_interval = polling_interval or self.DEFAULT_POLLING_INTERVAL
        self.logger.info(f"Waiting for custom condition: {message}")
        
        try:
            wait = WebDriverWait(
                self.driver, timeout, poll_frequency=polling_interval
            )
            result = wait.until(lambda driver: condition_function())
            self.logger.debug(f"Custom condition met: {message}")
            return result
                
        except TimeoutException:
            self.logger.error(f"Custom condition not met within timeout: {message}")
            self._take_screenshot("_custom_condition_timeout")
            raise TimeoutException(f"Timed out waiting for condition: {message}")
        except Exception as e:
            self.logger.error(f"Error while waiting for condition: {str(e)}")
            self._take_screenshot("_custom_condition_error")
            raise
    
    def wait_for_page_load(self, timeout: int = None) -> None:
        """
        Wait for page to load completely.
        
        Args:
            timeout: Wait timeout in seconds
            
        Raises:
            TimeoutException if page doesn't load within timeout
        """
        timeout = timeout or self.timeout
        self.logger.info("Waiting for page to load")
        
        try:
            def document_ready():
                return self.driver.execute_script("return document.readyState") == "complete"
                
            self.wait_for(document_ready, timeout, message="Page did not load completely")
            self.logger.debug("Page loaded successfully")
                
        except Exception as e:
            self.logger.error(f"Error waiting for page to load: {str(e)}")
            self._take_screenshot("_page_load_error")
            raise
    
    def wait_for_ajax(self, timeout: int = None) -> None:
        """
        Wait for all AJAX requests to complete.
        
        Args:
            timeout: Wait timeout in seconds
            
        Raises:
            TimeoutException if AJAX requests don't complete within timeout
        """
        timeout = timeout or self.timeout
        self.logger.info("Waiting for AJAX requests to complete")
        
        try:
            def ajax_complete():
                return self.driver.execute_script(
                    "return (typeof jQuery !== 'undefined') ? jQuery.active === 0 : true"
                )
                
            self.wait_for(ajax_complete, timeout, message="AJAX requests did not complete")
            self.logger.debug("All AJAX requests completed")
                
        except Exception as e:
            self.logger.error(f"Error waiting for AJAX: {str(e)}")
            self._take_screenshot("_ajax_wait_error")
            raise
    
    def wait_for_angular(self, timeout: int = None) -> None:
        """
        Wait for Angular to finish rendering.
        
        Args:
            timeout: Wait timeout in seconds
            
        Raises:
            TimeoutException if Angular doesn't finish within timeout
        """
        timeout = timeout or self.timeout
        self.logger.info("Waiting for Angular to finish rendering")
        
        try:
            def angular_ready():
                angular_script = """
                    try {
                        return angular.element(document).injector().get('$http').pendingRequests.length === 0;
                    } catch (e) {
                        return true;
                    }
                """
                return self.driver.execute_script(angular_script)
                
            self.wait_for(angular_ready, timeout, message="Angular did not finish rendering")
            self.logger.debug("Angular rendering completed")
                
        except Exception as e:
            self.logger.error(f"Error waiting for Angular: {str(e)}")
            self._take_screenshot("_angular_wait_error")
            raise
    
    # ====== Browser Window Methods ======
    
    def switch_to_window(self, window_index: int = 0) -> None:
        """
        Switch to a browser window by index.
        
        Args:
            window_index: Index of the window to switch to (0-based)
            
        Raises:
            IndexError if window index is out of range
        """
        self.logger.info(f"Switching to window at index {window_index}")
        
        try:
            windows = self.driver.window_handles
            
            if window_index >= len(windows):
                self.logger.error(f"Window index {window_index} out of range. "
                                f"Only {len(windows)} windows available.")
                self._take_screenshot("_window_index_error")
                raise IndexError(f"Window index {window_index} out of range")
                
            self.driver.switch_to.window(windows[window_index])
            self.logger.debug(f"Switched to window at index {window_index}")
                
        except Exception as e:
            self.logger.error(f"Failed to switch to window at index {window_index}: {str(e)}")
            self._take_screenshot("_window_switch_error")
            raise
    
    def switch_to_new_window(self) -> None:
        """
        Switch to the newest opened window.
        
        Raises:
            IndexError if no windows available
        """
        self.logger.info("Switching to most recently opened window")
        
        try:
            windows = self.driver.window_handles
            latest_window = windows[-1]
            self.driver.switch_to.window(latest_window)
            self.logger.debug(f"Switched to newest window. Total windows: {len(windows)}")
                
        except Exception as e:
            self.logger.error(f"Failed to switch to newest window: {str(e)}")
            self._take_screenshot("_new_window_switch_error")
            raise
    
    def close_current_window(self) -> None:
        """Close the current browser window and switch to the previous one."""
        self.logger.info("Closing current window")
        
        try:
            current_window = self.driver.current_window_handle
            windows = self.driver.window_handles
            
            self.driver.close()
            
            # Switch to previous window if any remain
            remaining_windows = [w for w in windows if w != current_window]
            if remaining_windows:
                self.driver.switch_to.window(remaining_windows[0])
                self.logger.debug("Closed window and switched to previous window")
            else:
                self.logger.debug("Closed the only window")
                
        except Exception as e:
            self.logger.error(f"Failed to close current window: {str(e)}")
            self._take_screenshot("_window_close_error")
            raise
    
    def get_window_title(self) -> str:
        """
        Get the title of the current window.
        
        Returns:
            Title of the current window
        """
        self.logger.debug("Getting window title")
        return self.driver.title
    
    def get_window_url(self) -> str:
        """
        Get the URL of the current window.
        
        Returns:
            URL of the current window
        """
        self.logger.debug("Getting window URL")
        return self.driver.current_url
    
    # ====== Cookie Methods ======
    
    def add_cookie(self, name: str, value: str, **kwargs) -> None:
        """
        Add a cookie to the browser.
        
        Args:
            name: Cookie name
            value: Cookie value
            **kwargs: Additional cookie parameters (domain, path, etc.)
        """
        self.logger.info(f"Adding cookie: {name}")
        
        try:
            cookie_dict = {"name": name, "value": value}
            cookie_dict.update(kwargs)
            self.driver.add_cookie(cookie_dict)
            self.logger.debug(f"Added cookie: {name}")
                
        except Exception as e:
            self.logger.error(f"Failed to add cookie {name}: {str(e)}")
            raise
    
    def get_cookie(self, name: str) -> dict:
        """
        Get a cookie by name.
        
        Args:
            name: Cookie name
            
        Returns:
            Cookie as dictionary or None if not found
        """
        self.logger.info(f"Getting cookie: {name}")
        
        try:
            cookie = self.driver.get_cookie(name)
            self.logger.debug(f"Retrieved cookie: {name}: {cookie}")
            return cookie
                
        except Exception as e:
            self.logger.error(f"Failed to get cookie {name}: {str(e)}")
            raise
    
    def delete_cookie(self, name: str) -> None:
        """
        Delete a cookie by name.
        
        Args:
            name: Cookie name
        """
        self.logger.info(f"Deleting cookie: {name}")
        
        try:
            self.driver.delete_cookie(name)
            self.logger.debug(f"Deleted cookie: {name}")
                
        except Exception as e:
            self.logger.error(f"Failed to delete cookie {name}: {str(e)}")
            raise
    
    def delete_all_cookies(self) -> None:
        """Delete all cookies."""
        self.logger.info("Deleting all cookies")
        
        try:
            self.driver.delete_all_cookies()
            self.logger.debug("Deleted all cookies")
                
        except Exception as e:
            self.logger.error(f"Failed to delete all cookies: {str(e)}")
            raise
    
    # ====== Utility Methods ======
    
    def get_page_source(self) -> str:
        """
        Get the HTML source of the current page.
        
        Returns:
            HTML source code
        """
        self.logger.debug("Getting page source")
        return self.driver.page_source
    
    def save_page_source(self, filepath: str) -> None:
        """
        Save the current page source to a file.
        
        Args:
            filepath: Path to save the file
        """
        self.logger.info(f"Saving page source to: {filepath}")
        
        try:
            source = self.get_page_source()
            with open(filepath, 'w', encoding='utf-8') as file:
                file.write(source)
            self.logger.debug(f"Page source saved to: {filepath}")
                
        except Exception as e:
            self.logger.error(f"Failed to save page source: {str(e)}")
            raise
    
    def wait_and_accept_cookie_banner(self, accept_button_locator: Tuple[By, str],
                                  banner_locator: Tuple[By, str] = None,
                                  timeout: int = 10) -> bool:
        """
        Wait for and accept a cookie consent banner if present.
        
        Args:
            accept_button_locator: Locator for the accept button
            banner_locator: Optional locator for the banner container
            timeout: Wait timeout in seconds
            
        Returns:
            True if banner was found and accepted, False otherwise
        """
        self.logger.info("Checking for cookie consent banner")
        
        try:
            # First check if banner exists if a banner locator was provided
            if banner_locator and not self.is_element_visible(banner_locator, timeout=timeout):
                self.logger.debug("No cookie banner detected")
                return False
            
            # Look for the accept button
            if not self.is_element_clickable(accept_button_locator, timeout=timeout):
                self.logger.debug("No cookie accept button detected")
                return False
                
            # Click the accept button
            self.click(accept_button_locator)
            self.logger.info("Cookie consent banner accepted")
            
            # Verify banner disappears if banner locator was provided
            if banner_locator:
                return self.wait_for_element_to_disappear(banner_locator, timeout=5)
            
            return True
                
        except Exception as e:
            self.logger.warning(f"Error handling cookie banner: {str(e)}")
            # Don't fail the test if cookie banner handling fails
            return False
    
    def perform_actions(self, action_chain: List[Callable]) -> None:
        """
        Perform a sequence of actions with error handling.
        
        Args:
            action_chain: List of callables to execute in order
            
        Example:
            ui.perform_actions([
                lambda: ui.type_text(EMAIL_FIELD, "test@example.com"),
                lambda: ui.type_text(PASSWORD_FIELD, "password"),
                lambda: ui.click(LOGIN_BUTTON)
            ])
        """
        self.logger.info(f"Performing action chain with {len(action_chain)} steps")
        
        for i, action in enumerate(action_chain):
            try:
                action()
                self.logger.debug(f"Action {i+1}/{len(action_chain)} completed successfully")
            except Exception as e:
                self.logger.error(f"Action {i+1}/{len(action_chain)} failed: {str(e)}")
                self._take_screenshot(f"_action_chain_error_step_{i+1}")
                raise


# Example usage
if __name__ == "__main__":
    from selenium import webdriver
    from selenium.webdriver.chrome.service import Service
    #from webdriver_manager.chrome import ChromeDriverManager
    
    # Set up logging
    logging.basicConfig(level=logging.INFO)
    
    # Set up Chrome options
    chrome_options = webdriver.ChromeOptions()
    chrome_options.add_argument("--start-maximized")  # Start maximized
    
    # Initialize the Chrome WebDriver
    driver = webdriver.Chrome(options=chrome_options);
    # Initialize our UI wrapper
    ui = SeleniumUIWrapper(driver, timeout=10)
    
    try:
        # Navigate to a website
        ui.navigate_to("https://www.example.com")
        
        # Wait for page to load
        ui.wait_for_page_load()
        
        # Get page title
        title = ui.get_window_title()
        print(f"Page title: {title}")
        
        # Example of working with elements (if they exist on the page)
        if ui.is_element_present((By.TAG_NAME, "h1")):
            h1_text = ui.get_element_text((By.TAG_NAME, "h1"))
            print(f"H1 text: {h1_text}")
        
        # Example of taking a screenshot
        ui._take_screenshot("example_page")
        
        # Example of executing JavaScript
        viewport_width = ui.execute_js("return window.innerWidth")
        viewport_height = ui.execute_js("return window.innerHeight")
        print(f"Viewport size: {viewport_width}x{viewport_height}")
        
    except Exception as e:
        print(f"Test failed: {str(e)}")
    finally:
        # Clean up
        driver.quit()
        print(f"Failed to take screenshot: {str(e)}")

    def _find_element(self, locator: Tuple[By, str], 
                      timeout: int = None, 
                      visible: bool = True, 
                      clickable: bool = False) -> WebElement:
        """
        Find an element with explicit wait strategy.
        
        Args:
            locator: Tuple of (By.X, "locator_string")
            timeout: Wait timeout in seconds
            visible: Whether to wait for visibility
            clickable: Whether to wait for clickability
            
        Returns:
            WebElement: The found element
            
        Raises:
            TimeoutException: If element not found within timeout
        """
        timeout = timeout or self.timeout
        wait = WebDriverWait(self.driver, timeout, poll_frequency=self.DEFAULT_POLLING_INTERVAL)
        
        self.logger.debug(f"Finding element: {locator}")
        
        try:
            if clickable:
                return wait.until(EC.element_to_be_clickable(locator))
            elif visible:
                return wait.until(EC.visibility_of_element_located(locator))
            else:
                return wait.until(EC.presence_of_element_located(locator))
        except TimeoutException:
            self.logger.error(f"Element not found: {locator}")
            self._take_screenshot(f"_element_not_found_{locator[1].replace(' ', '_')}")
            raise
    
    def _find_elements(self, locator: Tuple[By, str], 
                       timeout: int = None,
                       visible: bool = True) -> List[WebElement]:
        """
        Find multiple elements with explicit wait strategy.
        
        Args:
            locator: Tuple of (By.X, "locator_string")
            timeout: Wait timeout in seconds
            visible: Whether to wait for visibility
            
        Returns:
            List of WebElements
            
        Raises:
            TimeoutException: If no elements found within timeout
        """
        timeout = timeout or self.timeout
        wait = WebDriverWait(self.driver, timeout, poll_frequency=self.DEFAULT_POLLING_INTERVAL)
        
        self.logger.debug(f"Finding elements: {locator}")
        
        try:
            if visible:
                wait.until(EC.visibility_of_any_elements_located(locator))
            else:
                wait.until(EC.presence_of_all_elements_located(locator))
            
            return self.driver.find_elements(*locator)
        except TimeoutException:
            self.logger.error(f"No elements found: {locator}")
            self._take_screenshot(f"_elements_not_found_{locator[1].replace(' ', '_')}")
            raise
    
    def _retry_on_exception(self, action: Callable, 
                            exceptions: Tuple = (StaleElementReferenceException,),
                            max_retries: int = MAX_RETRIES,
                            retry_delay: float = RETRY_DELAY) -> Any:
        """
        Execute an action with retry logic.
        
        Args:
            action: Callable function to execute
            exceptions: Tuple of exceptions to catch and retry on
            max_retries: Maximum number of retry attempts
            retry_delay: Delay between retries in seconds
            
        Returns:
            The result of the action if successful
            
        Raises:
            The last exception caught if all retries fail
        """
        last_exception = None
        
        for attempt in range(max_retries + 1):
            try:
                return action()
            except exceptions as e:
                last_exception = e
                if attempt < max_retries:
                    self.logger.debug(f"Retrying action after exception: {str(e)}. "
                                     f"Attempt {attempt + 1}/{max_retries}")
                    time.sleep(retry_delay)
                else:
                    self.logger.error(f"Action failed after {max_retries} retries. "
                                    f"Last error: {str(e)}")
                    raise
        
        # Should not reach here, but just in case
        if last_exception:
            raise last_exception
    
    # ====== Navigation Methods ======
    
    def navigate_to(self, url: str) -> None:
        """
        Navigate to a URL.
        
        Args:
            url: The URL to navigate to
        """
        self.logger.info(f"Navigating to: {url}")
        self.driver.get(url)
    
    def refresh_page(self) -> None:
        """Refresh the current page."""
        self.logger.info("Refreshing page")
        self.driver.refresh()
    
    def go_back(self) -> None:
        """Navigate back in browser history."""
        self.logger.info("Navigating back")
        self.driver.back()
    
    def go_forward(self) -> None:
        """Navigate forward in browser history."""
        self.logger.info("Navigating forward")
        self.driver.forward()
    
    # ====== Element Interaction Methods ======
    
    def click(self, locator: Tuple[By, str], 
              timeout: int = None,
              force_js: bool = False,
              retry: bool = True) -> None:
        """
        Click on an element with robust error handling.
        
        Args:
            locator: Tuple of (By.X, "locator_string")
            timeout: Wait timeout in seconds
            force_js: Force JavaScript click
            retry: Whether to retry on StaleElementReferenceException
            
        Raises:
            Various WebDriver exceptions if click fails
        """
        timeout = timeout or self.timeout
        element_str = f"{locator[0]} '{locator[1]}'"
        self.logger.info(f"Clicking on element: {element_str}")
        
        try:
            element = self._find_element(locator, timeout, clickable=True)
            
            if force_js:
                self.logger.debug("Using JavaScript click")
                self.driver.execute_script("arguments[0].click();", element)
                return
                
            def click_action():
                try:
                    element.click()
                except (ElementClickInterceptedException, ElementNotVisibleException) as e:
                    self.logger.debug(f"Standard click failed with {type(e).__name__}, trying JS click")
                    self.driver.execute_script("arguments[0].click();", element)
            
            if retry:
                exceptions = (StaleElementReferenceException, ElementClickInterceptedException, 
                             ElementNotVisibleException, ElementNotInteractableException)
                self._retry_on_exception(click_action, exceptions=exceptions)
            else:
                click_action()
                
        except Exception as e:
            self.logger.error(f"Failed to click element {element_str}: {str(e)}")
            self._take_screenshot(f"_click_error_{locator[1].replace(' ', '_')}")
            raise
    
    def double_click(self, locator: Tuple[By, str], timeout: int = None) -> None:
        """
        Double-click on an element.
        
        Args:
            locator: Tuple of (By.X, "locator_string")
            timeout: Wait timeout in seconds
            
        Raises:
            Various WebDriver exceptions if action fails
        """
        timeout = timeout or self.timeout
        element_str = f"{locator[0]} '{locator[1]}'"
        self.logger.info(f"Double-clicking on element: {element_str}")
        
        try:
            element = self._find_element(locator, timeout, clickable=True)
            
            def double_click_action():
                actions = ActionChains(self.driver)
                actions.double_click(element).perform()
            
            self._retry_on_exception(double_click_action)
                
        except Exception as e:
            self.logger.error(f"Failed to double-click element {element_str}: {str(e)}")
            self._take_screenshot(f"_double_click_error_{locator[1].replace(' ', '_')}")
            raise
    
    def right_click(self, locator: Tuple[By, str], timeout: int = None) -> None:
        """
        Right-click on an element.
        
        Args:
            locator: Tuple of (By.X, "locator_string")
            timeout: Wait timeout in seconds
            
        Raises:
            Various WebDriver exceptions if action fails
        """
        timeout = timeout or self.timeout
        element_str = f"{locator[0]} '{locator[1]}'"
        self.logger.info(f"Right-clicking on element: {element_str}")
        
        try:
            element = self._find_element(locator, timeout, clickable=True)
            
            def right_click_action():
                actions = ActionChains(self.driver)
                actions.context_click(element).perform()
            
            self._retry_on_exception(right_click_action)
                
        except Exception as e:
            self.logger.error(f"Failed to right-click element {element_str}: {str(e)}")
            self._take_screenshot(f"_right_click_error_{locator[1].replace(' ', '_')}")
            raise
    
    def type_text(self, locator: Tuple[By, str], 
                 text: str, 
                 timeout: int = None,
                 clear_first: bool = True,
                 press_enter: bool = False,
                 delay: float = 0) -> None:
        """
        Type text into an input element.
        
        Args:
            locator: Tuple of (By.X, "locator_string")
            text: Text to type
            timeout: Wait timeout in seconds
            clear_first: Whether to clear the field first
            press_enter: Whether to press Enter after typing
            delay: Delay between keystrokes in seconds (for slow typing)
            
        Raises:
            Various WebDriver exceptions if typing fails
        """
        timeout = timeout or self.timeout
        element_str = f"{locator[0]} '{locator[1]}'"
        self.logger.info(f"Typing '{text}' into {element_str}")
        
        try:
            element = self._find_element(locator, timeout)
            
            def type_action():
                if clear_first:
                    element.clear()
                
                if delay > 0:
                    for char in text:
                        element.send_keys(char)
                        time.sleep(delay)
                else:
                    element.send_keys(text)
                    
                if press_enter:
                    element.send_keys(Keys.ENTER)
            
            self._retry_on_exception(type_action)
                
        except Exception as e:
            self.logger.error(f"Failed to type text into {element_str}: {str(e)}")
            self._take_screenshot(f"_type_error_{locator[1].replace(' ', '_')}")
            raise
    
    def clear_text(self, locator: Tuple[By, str], timeout: int = None) -> None:
        """
        Clear text from an input element.
        
        Args:
            locator: Tuple of (By.X, "locator_string")
            timeout: Wait timeout in seconds
            
        Raises:
            Various WebDriver exceptions if action fails
        """
        timeout = timeout or self.timeout
        element_str = f"{locator[0]} '{locator[1]}'"
        self.logger.info(f"Clearing text from {element_str}")
        
        try:
            element = self._find_element(locator, timeout)
            
            def clear_action():
                element.clear()
            
            self._retry_on_exception(clear_action)
                
        except Exception as e:
            self.logger.error(f"Failed to clear text from {element_str}: {str(e)}")
            self._take_screenshot(f"_clear_error_{locator[1].replace(' ', '_')}")
            raise
    
    def select_dropdown_by_text(self, locator: Tuple[By, str], 
                              text: str, 
                              timeout: int = None) -> None:
        """
        Select dropdown option by visible text.
        
        Args:
            locator: Tuple of (By.X, "locator_string")
            text: Visible text of the option to select
            timeout: Wait timeout in seconds
            
        Raises:
            Various WebDriver exceptions if selection fails
        """
        timeout = timeout or self.timeout
        element_str = f"{locator[0]} '{locator[1]}'"
        self.logger.info(f"Selecting option '{text}' from dropdown {element_str}")
        
        try:
            element = self._find_element(locator, timeout)
            
            def select_action():
                select = Select(element)
                select.select_by_visible_text(text)
            
            self._retry_on_exception(select_action)
                
        except Exception as e:
            self.logger.error(f"Failed to select '{text}' from dropdown {element_str}: {str(e)}")
            self._take_screenshot(f"_select_error_{locator[1].replace(' ', '_')}")
            raise
    
    def select_dropdown_by_value(self, locator: Tuple[By, str], 
                               value: str, 
                               timeout: int = None) -> None:
        """
        Select dropdown option by value attribute.
        
        Args:
            locator: Tuple of (By.X, "locator_string")
            value: Value attribute of the option to select
            timeout: Wait timeout in seconds
            
        Raises:
            Various WebDriver exceptions if selection fails
        """
        timeout = timeout or self.timeout
        element_str = f"{locator[0]} '{locator[1]}'"
        self.logger.info(f"Selecting option with value '{value}' from dropdown {element_str}")
        
        try:
            element = self._find_element(locator, timeout)
            
            def select_action():
                select = Select(element)
                select.select_by_value(value)
            
            self._retry_on_exception(select_action)
                
        except Exception as e:
            self.logger.error(f"Failed to select value '{value}' from dropdown {element_str}: {str(e)}")
            self._take_screenshot(f"_select_value_error_{locator[1].replace(' ', '_')}")
            raise
    
    def select_dropdown_by_index(self, locator: Tuple[By, str], 
                               index: int, 
                               timeout: int = None) -> None:
        """
        Select dropdown option by index.
        
        Args:
            locator: Tuple of (By.X, "locator_string")
            index: Index of the option to select (0-based)
            timeout: Wait timeout in seconds
            
        Raises:
            Various WebDriver exceptions if selection fails
        """
        timeout = timeout or self.timeout
        element_str = f"{locator[0]} '{locator[1]}'"
        self.logger.info(f"Selecting option at index {index} from dropdown {element_str}")
        
        try:
            element = self._find_element(locator, timeout)
            
            def select_action():
                select = Select(element)
                select.select_by_index(index)
            
            self._retry_on_exception(select_action)
                
        except Exception as e:
            self.logger.error(f"Failed to select index {index} from dropdown {element_str}: {str(e)}")
            self._take_screenshot(f"_select_index_error_{locator[1].replace(' ', '_')}")
            raise
    
    def get_dropdown_options(self, locator: Tuple[By, str], 
                           timeout: int = None) -> List[str]:
        """
        Get all options from a dropdown.
        
        Args:
            locator: Tuple of (By.X, "locator_string")
            timeout: Wait timeout in seconds
            
        Returns:
            List of option texts
            
        Raises:
            Various WebDriver exceptions if operation fails
        """
        timeout = timeout or self.timeout
        element_str = f"{locator[0]} '{locator[1]}'"
        self.logger.info(f"Getting all options from dropdown {element_str}")
        
        try:
            element = self._find_element(locator, timeout)
            
            def get_options_action():
                select = Select(element)
                return [option.text for option in select.options]
            
            return self._retry_on_exception(get_options_action)
                
        except Exception as e:
            self.logger.error(f"Failed to get options from dropdown {element_str}: {str(e)}")
            self._take_screenshot(f"_get_options_error_{locator[1].replace(' ', '_')}")
            raise
    
    def get_selected_option(self, locator: Tuple[By, str], 
                          timeout: int = None) -> str:
        """
        Get selected option from a dropdown.
        
        Args:
            locator: Tuple of (By.X, "locator_string")
            timeout: Wait timeout in seconds
            
        Returns:
            Text of the selected option
            
        Raises:
            Various WebDriver exceptions if operation fails
        """
        timeout = timeout or self.timeout
        element_str = f"{locator[0]} '{locator[1]}'"
        self.logger.info(f"Getting selected option from dropdown {element_str}")
        
        try:
            element = self._find_element(locator, timeout)
            
            def get_selected_action():
                select = Select(element)
                return select.first_selected_option.text
            
            return self._retry_on_exception(get_selected_action)
                
        except Exception as e:
            self.logger.error(f"Failed to get selected option from dropdown {element_str}: {str(e)}")
            self._take_screenshot(f"_get_selected_option_error_{locator[1].replace(' ', '_')}")
            raise
    
    def check_checkbox(self, locator: Tuple[By, str], 
                     timeout: int = None,
                     uncheck_first: bool = False) -> None:
        """
        Check a checkbox.
        
        Args:
            locator: Tuple of (By.X, "locator_string")
            timeout: Wait timeout in seconds
            uncheck_first: Whether to uncheck first to ensure proper state
            
        Raises:
            Various WebDriver exceptions if operation fails
        """
        timeout = timeout or self.timeout
        element_str = f"{locator[0]} '{locator[1]}'"
        self.logger.info(f"Checking checkbox {element_str}")
        
        try:
            element = self._find_element(locator, timeout, clickable=True)
            
            def check_action():
                if uncheck_first and element.is_selected():
                    element.click()
                    time.sleep(0.1)  # Small delay to ensure UI updates
                
                if not element.is_selected():
                    element.click()
            
            self._retry_on_exception(check_action)
                
        except Exception as e:
            self.logger.error(f"Failed to check checkbox {element_str}: {str(e)}")
            self._take_screenshot(f"_check_checkbox_error_{locator[1].replace(' ', '_')}")
            raise
    
    def uncheck_checkbox(self, locator: Tuple[By, str], 
                       timeout: int = None) -> None:
        """
        Uncheck a checkbox.
        
        Args:
            locator: Tuple of (By.X, "locator_string")
            timeout: Wait timeout in seconds
            
        Raises:
            Various WebDriver exceptions if operation fails
        """
        timeout = timeout or self.timeout
        element_str = f"{locator[0]} '{locator[1]}'"
        self.logger.info(f"Unchecking checkbox {element_str}")
        
        try:
            element = self._find_element(locator, timeout, clickable=True)
            
            def uncheck_action():
                if element.is_selected():
                    element.click()
            
            self._retry_on_exception(uncheck_action)
                
        except Exception as e:
            self.logger.error(f"Failed to uncheck checkbox {element_str}: {str(e)}")
            self._take_screenshot(f"_uncheck_checkbox_error_{locator[1].replace(' ', '_')}")
            raise
    
    def is_checkbox_checked(self, locator: Tuple[By, str], 
                          timeout: int = None) -> bool:
        """
        Check if a checkbox is checked.
        
        Args:
            locator: Tuple of (By.X, "locator_string")
            timeout: Wait timeout in seconds
            
        Returns:
            True if checkbox is checked, False otherwise
            
        Raises:
            Various WebDriver exceptions if operation fails
        """
        timeout = timeout or self.timeout
        element_str = f"{locator[0]} '{locator[1]}'"
        self.logger.info(f"Checking if checkbox {element_str} is checked")
        
        try:
            element = self._find_element(locator, timeout)
            
            def is_checked_action():
                return element.is_selected()
            
            result = self._retry_on_exception(is_checked_action)
            self.logger.debug(f"Checkbox {element_str} checked status: {result}")
            return result
                
        except Exception as e:
            self.logger.error(f"Failed to get checkbox status {element_str}: {str(e)}")
            self._take_screenshot(f"_checkbox_status_error_{locator[1].replace(' ', '_')}")
            raise
    
    def switch_to_frame(self, locator: Tuple[By, str], timeout: int = None) -> None:
        """
        Switch to an iframe.
        
        Args:
            locator: Tuple of (By.X, "locator_string")
            timeout: Wait timeout in seconds
            
        Raises:
            Various WebDriver exceptions if operation fails
        """
        timeout = timeout or self.timeout
        element_str = f"{locator[0]} '{locator[1]}'"
        self.logger.info(f"Switching to frame {element_str}")
        
        try:
            wait = WebDriverWait(self.driver, timeout)
            frame = wait.until(EC.frame_to_be_available_and_switch_to_it(locator))
            self.logger.debug(f"Successfully switched to frame {element_str}")
                
        except Exception as e:
            self.logger.error(f"Failed to switch to frame {element_str}: {str(e)}")
            self._take_screenshot(f"_switch_frame_error_{locator[1].replace(' ', '_')}")
            raise
    
    def switch_to_default_content(self) -> None:
        """Switch back to the main document from an iframe."""
        self.logger.info("Switching back to default content")
        self.driver.switch_to.default_content()
    
    def switch_to_parent_frame(self) -> None:
        """Switch back to the parent frame."""
        self.logger.info("Switching back to parent frame")
        self.driver.switch_to.parent_frame()
    
    def hover(self, locator: Tuple[By, str], timeout: int = None) -> None:
        """
        Hover over an element.
        
        Args:
            locator: Tuple of (By.X, "locator_string")
            timeout: Wait timeout in seconds
            
        Raises:
            Various WebDriver exceptions if operation fails
        """
        timeout = timeout or self.timeout
        element_str = f"{locator[0]} '{locator[1]}'"
        self.logger.info(f"Hovering over element {element_str}")
        
        try:
            element = self._find_element(locator, timeout)
            
            def hover_action():
                actions = ActionChains(self.driver)
                actions.move_to_element(element).perform()
            
            self._retry_on_exception(hover_action)
                
        except Exception as e:
            self.logger.error(f"Failed to hover over element {element_str}: {str(e)}")
            self._take_screenshot(f"_hover_error_{locator[1].replace(' ', '_')}")
            raise
    
    def drag_and_drop(self, source_locator: Tuple[By, str], 
                     target_locator: Tuple[By, str],
                     timeout: int = None) -> None:
        """
        Drag and drop an element.
        
        Args:
            source_locator: Tuple of (By.X, "locator_string") for the source element
            target_locator: Tuple of (By.X, "locator_string") for the target element
            timeout: Wait timeout in seconds
            
        Raises:
            Various WebDriver exceptions if operation fails
        """
        timeout = timeout or self.timeout
        source_str = f"{source_locator[0]} '{source_locator[1]}'"
        target_str = f"{target_locator[0]} '{target_locator[1]}'"
        self.logger.info(f"Dragging {source_str} to {target_str}")
        
        try:
            source = self._find_element(source_locator, timeout)
            target = self._find_element(target_locator, timeout)
            
            def drag_drop_action():
                actions = ActionChains(self.driver)
                actions.drag_and_drop(source, target).perform()
            
            self._retry_on_exception(drag_drop_action)
                
        except Exception as e:
            self.logger.error(f"Failed to drag and drop from {source_str} to {target_str}: {str(e)}")
            self._take_screenshot(f"_drag_drop_error_{source_locator[1].replace(' ', '_')}")
            raise
    
    def execute_js(self, script: str, *args) -> Any:
        """
        Execute JavaScript in the browser.
        
        Args:
            script: JavaScript code to execute
            *args: Arguments to pass to the script
            
        Returns:
            Result of the script execution
            
        Raises:
            JavaScriptException if script execution fails
        """
        self.logger.info(f"Executing JavaScript: {script[:50]}{'...' if len(script) > 50 else ''}")
        
        try:
            result = self.driver.execute_script(script, *args)
            return result
        except Exception as e:
            self.logger.error(f"Failed to execute JavaScript: {str(e)}")
            self._take_screenshot("_js_error")
            raise
    
    def scroll_into_view(self, locator: Tuple[By, str], timeout: int = None) -> None:
        """
        Scroll an element into view.
        
        Args:
            locator: Tuple of (By.X, "locator_string")
            timeout: Wait timeout in seconds
            
        Raises:
            Various WebDriver exceptions if operation fails
        """
        timeout = timeout or self.timeout
        element_str = f"{locator[0]} '{locator[1]}'"
        self.logger.info(f"Scrolling element into view: {element_str}")
        
        try:
            element = self._find_element(locator, timeout, visible=False)
            self.execute_js("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element)
            time.sleep(0.5)  # Allow time for scroll to complete
                
        except Exception as e:
            self.logger.error(f"")