from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options as ChromeOptions
command_executor="http://localhost:8090"
#command_executor="http://localhost:4444/wd/hub"
chrome_options = ChromeOptions()
driver1 = webdriver.Chrome()

driver = webdriver.Remote(
        command_executor=command_executor,
        options=chrome_options
   )

#driver2 = webdriver.Firefox()
driver1.get('https://the-internet.herokuapp.com/')

#driver1.get('https://the-internet.herokuapp.com/')
assert 'The Internet' in driver1.title
#assert 'The Internet' in driver2.title
#elem = driver.find_element(By.ID, 'm-documentationwebdriver')
#elem.click()
#assert 'WebDriver' in driver.title
#driver.quit()



####### this is used to direct chrome browser and application to the same port number to append the data. 
driver = webdriver.Remote(
        command_executor=command_executor,
        options=chrome_options
   )
