package com.example.tourguide;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class PlacesActivity extends AppCompatActivity {

    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        layout = findViewById(R.id.places_layout);

        String cityName = getIntent().getStringExtra("city_name");

        String[][] places = getPlacesForCity(cityName);

        for (String[] place : places) {
            TextView placeName = new TextView(this);
            placeName.setText(place[0]);
            placeName.setTextSize(20);
            placeName.setTextColor(ContextCompat.getColor(this, R.color.teal_700));
            placeName.setPadding(0, 16, 0, 0);
            placeName.setTypeface(null, android.graphics.Typeface.BOLD);

            TextView placeAddress = new TextView(this);
            placeAddress.setText(place[1]);
            placeAddress.setTextSize(16);
            placeAddress.setTextColor(ContextCompat.getColor(this, android.R.color.black));
            placeAddress.setPadding(0, 4, 0, 8);

            // 🌟 Add Click to open Google Maps
            placeName.setOnClickListener(v -> {
                String mapQuery = place[0] + ", " + place[1];
                Uri locationUri = Uri.parse("geo:0,0?q=" + Uri.encode(mapQuery));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, locationUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            });

            layout.addView(placeName);
            layout.addView(placeAddress);
        }
    }

    private String[][] getPlacesForCity(String city) {
        switch (city) {
            case "Coimbatore":
                return new String[][]{
                        {"Marudhamalai Temple", "Marudamalai, Coimbatore"},
                        {"Brookefields Mall", "Brookebond Road, Coimbatore"},
                        {"VOC Park", "Gandhipuram, Coimbatore"},
                        {"Isha Yoga Center", "Velliangiri Foothills, Coimbatore"},
                        {"TNAU Botanical Garden", "Lawley Road, Coimbatore"}
                };
            case "Chennai":
                return new String[][]{
                        {"Marina Beach", "Marina, Chennai"},
                        {"Fort St. George", "Rajaji Salai, Chennai"},
                        {"Kapaleeshwarar Temple", "Mylapore, Chennai"},
                        {"Guindy National Park", "Guindy, Chennai"},
                        {"Vivekananda House", "Kamarajar Salai, Chennai"}
                };
            case "Erode":
                return new String[][]{
                        {"Velalar College of Engineering and Technology", "Thindal, Erode"},
                        {"Periya Mariamman Temple", "Erode Town"},
                        {"Vellode Bird Sanctuary", "Vadamugam Vellode, Erode"},
                        {"Bhavani Sangameshwarar Temple", "Bhavani, Erode"},
                        {"Chennimalai Murugan Temple", "Chennimalai, Erode"}
                };

            case "Dindigul":
                return new String[][]{
                        {"Dindigul Fort", "Dindigul City"},
                        {"Sirumalai Hills", "Sirumalai, Dindigul"},
                        {"Begambur Big Mosque", "Begambur, Dindigul"},
                        {"St. Joseph's Church", "Dindigul"},
                        {"Kodaikanal Lake", "Near Dindigul"}
                };
            default:
                return new String[][]{};
        }
    }
}
