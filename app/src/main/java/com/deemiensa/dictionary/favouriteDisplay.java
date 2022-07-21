package com.deemiensa.dictionary;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class favouriteDisplay extends AppCompatActivity {
    private TextView wordMeaning;
    private TextView type;
    private TextView word;
    private ImageButton mBookmark;
    private favouriteSQLiteDatabase dbBackend;
    int idm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String dictionarywd = bundle.getString("DICTIONARY_WD");
        //int id = dictionaryId + 1;
        word = (TextView)findViewById(R.id.word);

        wordMeaning = (TextView)findViewById(R.id.dictionary);
        wordMeaning.setMovementMethod(new ScrollingMovementMethod());

        type= (TextView)findViewById(R.id.type);

        mBookmark=(ImageButton)findViewById(R.id.bookmark);
        ImageButton mShare = (ImageButton) findViewById(R.id.share);

        dbBackend = new favouriteSQLiteDatabase(favouriteDisplay.this);

        dictionaryModel obj = dbBackend.getBywd(dictionarywd);
        word.setText(obj.getWord());
        type.setText(obj.getType());
        idm = obj.getId();
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


        mBookmark.setContentDescription("UnFavourite");
        mBookmark.setImageResource(R.mipmap.ic_bookmark_yellow);
    }

    public void mButton(View view) {
        if(mBookmark.getContentDescription().equals("Favourite")) {
            dbBackend.save(idm, word.getText().toString(), type.getText().toString(),
                           wordMeaning.getText().toString());
            Toast.makeText(getApplicationContext(), "Added to Favourites", Toast.LENGTH_SHORT).show();
            mBookmark.setContentDescription("UnFavourite");
            mBookmark.setImageResource(R.mipmap.ic_bookmark_yellow);
        } else {
            dbBackend.delete(idm);
            Toast.makeText(getApplicationContext(), "Removed from Favourites", Toast.LENGTH_SHORT).show();
            mBookmark.setContentDescription("Favourite");
            mBookmark.setImageResource(R.mipmap.ic_bookmark_white);
        }
    }

   /* @Override
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
                    (favouriteDisplay.this,
                            onBoardingHelp.class);
            startActivity(intent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
*/
}
