import requests

number = "01500000000"
modified_number = "+88" + f"{number}"

# URL (Read readme.md before using the raw_url)
url = "https://w3nabil.github.io/bdtel/v1/simple"
raw_url = "https://w3nabil.github.io/bdtel/v1/raw"

# API endpoint 
api_endpoint = f"{url}/{modified_number[0:8]}.json"

# Try method
try:
  # GET request 
  response = requests.get(api_endpoint)

  # Status code = 200 
  if response.status_code == 200:
      data = response.json()

      # if "telecom" key exists 
      if "telecom" in data:
          print(data["telecom"])
  else:
      # Status code not 200 
      print(f"Error: HTTP status code {response.status_code}")

except requests.RequestException as e:
  print(f"Error: {e}")
