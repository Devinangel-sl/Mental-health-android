package com.example.wellcomepage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeActivity extends AppCompatActivity {
    private ProgressBar spinner;
    private TextView events_texts;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize views
        spinner = findViewById(R.id.spinner);
        events_texts = findViewById(R.id.events_texts);

        // Get the keypass from the Intent
        String keypass = getIntent().getStringExtra("KEYPASS");
        Log.d("HomeActivity", "get KEYPASS: " + keypass);
        if (keypass == null || keypass.isEmpty()) {
            Log.d("HomeActivity", "KEYPASS is null or empty: " + keypass);
            Toast.makeText(this, "Invalid KEYPASS", Toast.LENGTH_SHORT).show();
        } else {
            fetchDashboardData(keypass);  // Call method to fetch the data
        }

        Button DetailPageButton = findViewById(R.id.DetailPage);
        DetailPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start appDetailsPage when the button is clicked
                Intent intent = new Intent(HomeActivity.this, appDetailsPage.class);
                startActivity(intent);
            }
        });
    }





    private void fetchDashboardData(String keypass) {
        // Show loading spinner
        spinner.setVisibility(View.VISIBLE);

        // Construct URL
        String url = "https://vu-nit3213-api.onrender.com/dashboard/" + keypass;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> {
                    // Hide loading spinner and show error
                    spinner.setVisibility(View.GONE);
                    Toast.makeText(HomeActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();  // Get response body
                Log.d("HomeActivity", "Response: " + responseData);

                if (response.isSuccessful()) {
                    runOnUiThread(() -> {
                        // Hide loading spinner
                        spinner.setVisibility(View.GONE);
                        // Update UI with the events data
                        updateUIWithEvents(responseData);
                    });
                } else {
                    runOnUiThread(() -> {
                        spinner.setVisibility(View.GONE);
                        Toast.makeText(HomeActivity.this, "Data fetching failed", Toast.LENGTH_SHORT).show();
                    });
                }
            }
        });
    }

    void updateUIWithEvents(String responseData) {
        // Parse the JSON response
        try {
            JSONObject jsonObject = new JSONObject(responseData);

            // Check if entities key exists
            if (jsonObject.has("entities")) {
                JSONArray entities = jsonObject.getJSONArray("entities");

                StringBuilder eventsText = new StringBuilder();
                for (int i = 0; i < entities.length(); i++) {
                    JSONObject event = entities.getJSONObject(i);
                    String eventName = event.optString("event", "No Event Name");  // Use optString to avoid crash
                    String description = event.optString("description", "No Description");

                    eventsText.append("Event: ").append(eventName).append("\n")
                            .append("Description: ").append(description).append("\n\n");
                }

                // Set the events to the TextView
                events_texts.setText(eventsText.toString());
            } else {
                // Show a message if entities array is not found
                events_texts.setText("No events found.");
                Log.d("HomeActivity", "No entities found in the response.");
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(HomeActivity.this, "Error parsing data", Toast.LENGTH_SHORT).show();
        }
    }
}