package com.deemiensa.dictionary;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class proofreadingMarks extends AppCompatActivity{

    private ImageButton proof, proof_2;
    private ImageView proofImage, proof_2Image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proofreading_marks);

        proof=(ImageButton) findViewById(R.id.proof);
        proof_2=(ImageButton) findViewById(R.id.proof_2);

        proofImage=(ImageView) findViewById(R.id.proofImage);
        proof_2Image=(ImageView) findViewById(R.id.proof_2Image);


        proof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proof_2.setVisibility(View.VISIBLE);
                proof_2Image.setVisibility(View.VISIBLE);
                proof.setVisibility(View.INVISIBLE);
                proofImage.setVisibility(View.INVISIBLE);
            }
        });

        proof_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                proof_2.setVisibility(View.INVISIBLE);
                proof_2Image.setVisibility(View.INVISIBLE);
                proof.setVisibility(View.VISIBLE);
                proofImage.setVisibility(View.VISIBLE);
            }
        });

    }
}
