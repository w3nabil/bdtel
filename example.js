const number = "01500000000";
const modifiedNumber = "+88" + number;

const url = "https://w3nabil.github.io/bdtelcom-api/v1/simple";
const rawUrl = "https://w3nabil.github.io/bdtelcom-api/v1/raw";

const apiEndpoint = `${url}/${modifiedNumber.slice(0, 8)}.json`;

fetch(apiEndpoint)
  .then(response => {
    if (response.ok) {
      return response.json();
    } else {
      throw new Error(`Error: HTTP status code ${response.status}`);
    }
  })
  .then(data => {
    if (data && data.telecom !== null && data.telecom !== undefined) {
      console.log(data.telecom);
    } else {
      console.log("No telecom data found or value is null.");
    }
  })
  .catch(error => {
    console.error(`Error: ${error.message}`);
  });
