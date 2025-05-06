from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options as ChromeOptions
command_executor="http://localhost:8090"
chrome_options = ChromeOptions()
#driver1 = webdriver.Chrome()
#driver2 = webdriver.Firefox()
#driver1.get('https://the-internet.herokuapp.com/')
driver = webdriver.Remote(
        command_executor=command_executor,
        options=chrome_options
    )
driver.get('https://the-internet.herokuapp.com/')
assert 'The Internet' in driver.title
#assert 'The Internet' in driver2.title
#elem = driver.find_element(By.ID, 'm-documentationwebdriver')
#elem.click()
#assert 'WebDriver' in driver.title
#driver.quit()