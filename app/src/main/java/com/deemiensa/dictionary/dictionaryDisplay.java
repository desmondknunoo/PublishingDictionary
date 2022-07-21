package com.deemiensa.dictionary;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class dictionaryDisplay extends AppCompatActivity {
    private TextView wordMeaning;
    private TextView type;
    private TextView word;
    private int id;
    private ImageButton mBookmark;
    favouriteSQLiteDatabase dbBackendfav;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        int dictionaryId = bundle.getInt("DICTIONARY_ID");

        id = dictionaryId + 1;

        word = (TextView)findViewById(R.id.word);

        wordMeaning = (TextView)findViewById(R.id.dictionary);
        wordMeaning.setMovementMethod(new ScrollingMovementMethod());

        type= (TextView)findViewById(R.id.type);

        mBookmark=(ImageButton) findViewById(R.id.bookmark);
        ImageButton mShare = (ImageButton) findViewById(R.id.share);

        dictionarySQLiteDatabase dbBackend = new dictionarySQLiteDatabase(dictionaryDisplay.this);
        dictionaryModel obj = dbBackend.getById(id);

        dbBackendfav = new favouriteSQLiteDatabase(dictionaryDisplay.this);

        word.setText(obj.getWord());
        type.setText(obj.getType());
        wordMeaning.setText(obj.getDefinition());

        mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Orange Dictionary");
                intent.putExtra(Intent.EXTRA_TEXT,word.getText() + "\n\n" + type.getText() + "\n\n" + wordMeaning.getText()
                        + "\n\n" + "Text is extracted from various dictionaries available under the CC BY-SA 3.0 license"
                        + "\n===============\n\nText is generated from the Orange Dictionary.\nGet it from: http://play.google.com/store/apps/details?id="
                        + getBaseContext().getPackageName());
                startActivity(Intent.createChooser(intent, "Share word via..."));
            }
        });



       /* if(dbBackendfav.exists(id)) {
            mBookmark.setText("UnBookmark");
        } else {
            mBookmark.setText("Bookmark");
        }*/

        if(dbBackendfav.exists(id)) {
            mBookmark.setImageResource(R.mipmap.ic_bookmark_yellow);
            mBookmark.setContentDescription("UnFavourite");
        } else {
            mBookmark.setImageResource(R.mipmap.ic_bookmark_white);
            mBookmark.setContentDescription("Favourite");
        }
    }

    public void mButton(View view) {
        mBookmark=(ImageButton) findViewById(R.id.bookmark);
        if(mBookmark.getContentDescription().equals("Favourite")) {

            dbBackendfav.save(id, word.getText().toString(), type.getText().toString(),
                              wordMeaning.getText().toString());
            Toast.makeText(getApplicationContext(), "Added to Favourites", Toast.LENGTH_SHORT).show();
            mBookmark.setContentDescription("UnFavourite");
            mBookmark.setImageResource(R.mipmap.ic_bookmark_yellow);

        } else {
            dbBackendfav.delete(id);
            Toast.makeText(getApplicationContext(), "Removed from Favourites", Toast.LENGTH_SHORT).show();
            mBookmark.setContentDescription("Favourite");
            mBookmark.setImageResource(R.mipmap.ic_bookmark_white);
        }
    }
/*

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.help, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The actionbar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.help) {
            Intent intent = new Intent
                    (dictionaryDisplay.this,
                            onBoardingHelp.class);
            startActivity(intent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
*/

}