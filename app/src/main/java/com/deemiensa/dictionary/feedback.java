package com.deemiensa.dictionary;

import android.content.Intent;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.View;
import android.widget.EditText;

public class feedback extends AppCompatActivity {

    CardView send;
    EditText developers;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

                    send = (CardView) findViewById(R.id.Button1);
                    editText = (EditText) findViewById(R.id.EditText01);

                    send.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub

                            Intent emailIntent = new Intent(Intent.ACTION_SEND);
                            emailIntent.setType("plain/text");
                            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"desmondknunoo@gmail.com"});
                            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback: Orange Dictionary");
                            emailIntent.putExtra(Intent.EXTRA_TEXT, editText.getText());
                            try {
                                feedback.this.startActivity(Intent.createChooser(emailIntent, "Send email using"));

                            } catch (android.content.ActivityNotFoundException ex) {
                                Snackbar.make(v, "No email clients available.", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                            }


                        }

                    });



                    send = (CardView) findViewById(R.id.Button2);
                    developers = (EditText) findViewById(R.id.editText3);

                    send.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub

                            Intent emailIntent = new Intent(Intent.ACTION_SEND);
                            emailIntent.setType("plain/text");
                            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"desmondknunoo@gmail.com"});
                            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Problem report: Orange Dictionary");
                            emailIntent.putExtra(Intent.EXTRA_TEXT, developers.getText());
                            try {
                                feedback.this.startActivity(Intent.createChooser(emailIntent, "Send email using"));

                            } catch (android.content.ActivityNotFoundException ex) {
                                Snackbar.make(v, "No email clients available.", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                            }


                        }

                    });
                }


        }