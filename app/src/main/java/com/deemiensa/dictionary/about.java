package com.deemiensa.dictionary;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class about extends AppCompatActivity {

    TextView textView, textView1, version;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        version = findViewById(R.id.version);
        version.setText(R.string.version +  BuildConfig.VERSION_NAME);

        textView = (TextView) findViewById(R.id.link2);
        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


                Intent webIntent = new Intent(Intent.ACTION_VIEW);
                webIntent.setData(Uri.parse("http://www.apache.org/licenses/"));


                try {
                    startActivity(Intent.createChooser(webIntent, "Complete action using"));

                } catch (android.content.ActivityNotFoundException ex) {
                    Snackbar.make(arg0, "No web clients available.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }


            }

        });

        textView1 = (TextView) findViewById(R.id.link);
        textView1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


                Intent webIntent = new Intent(Intent.ACTION_VIEW);
                webIntent.setData(Uri.parse("http://creativecommons.org/licenses/by-sa/3.0"));


                try {
                    startActivity(Intent.createChooser(webIntent, "Complete action using"));

                } catch (android.content.ActivityNotFoundException ex) {
                    Snackbar.make(arg0, "No web clients available.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }


            }

        });
    }
}