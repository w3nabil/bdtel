import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.os.AsyncTask;
import org.json.JSONObject;
import org.json.JSONException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends Activity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        // Example API endpoint
        String apiEndpoint = "https://w3nabil.github.io/bdtelcom-api/v1/simple/880231247.json";

        // Execute AsyncTask to perform the HTTP request in the background
        new HttpRequestTask().execute(apiEndpoint);
    }

    private class HttpRequestTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                // Open connection to the API endpoint
                URL url = new URL(params[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                try {
                    // Read the response
                    InputStream in = urlConnection.getInputStream();
                    Scanner scanner = new Scanner(in);
                    scanner.useDelimiter("\\A");

                    return scanner.hasNext() ? scanner.next() : null;
                } finally {
                    urlConnection.disconnect();
                }
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                try {
                    // Parse JSON response
                    JSONObject json = new JSONObject(result);

                    // Check if "telecom" key exists
                    if (json.has("telecom")) {
                        String telecomValue = json.getString("telecom");
                        textView.setText(telecomValue);
                    } else {
                        textView.setText("No telecom data found");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    textView.setText("Error parsing JSON");
                }
            } else {
                textView.setText("Error fetching data");
            }
        }
    }
}

// This file was made by Github Copilot , If there is any error , please fill free to mark it out, Personally I don't do JAVA coding nor I did try to learn this :P 
