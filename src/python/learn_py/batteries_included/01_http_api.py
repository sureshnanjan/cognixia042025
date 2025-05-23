import urllib.request
import urllib.parse
import json


def fetch_weather_data(city, api_key):
    """Fetch weather data using only standard library."""
    base_url = "http://api.openweathermap.org/data/2.5/weather"
    params = {
        'q': city,
        'appid': api_key,
        'units': 'metric'
    }

    # Build URL with parameters
    url = f"{base_url}?{urllib.parse.urlencode(params)}"

    try:
        with urllib.request.urlopen(url) as response:
            data = json.loads(response.read().decode())

        return {
            'city': data['name'],
            'temperature': data['main']['temp'],
            'description': data['weather'][0]['description'],
            'humidity': data['main']['humidity']
        }
    except urllib.error.HTTPError as e:
        print(f"HTTP Error: {e.code}")
        return None
    except Exception as e:
        print(f"Error: {e}")
        return None


def post_data_to_api(url, data):
    """Send POST request with JSON data."""
    json_data = json.dumps(data).encode('utf-8')

    req = urllib.request.Request(
        url,
        data=json_data,
        headers={'Content-Type': 'application/json'}
    )

    try:
        with urllib.request.urlopen(req) as response:
            return json.loads(response.read().decode())
    except Exception as e:
        print(f"Error posting data: {e}")
        return None

# Example usage
# weather = fetch_weather_data("London", "your_api_key")
# if weather:
#     print(f"Weather in {weather['city']}: {weather['temperature']}°C")
