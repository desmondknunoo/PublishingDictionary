package com.deemiensa.dictionary;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class main extends AppCompatActivity {

        private SearchView filterText;
        private ArrayAdapter<String> listAdapter;
        Dialog alertDialog1 = null;
        private GridLayout gridLayout;
        private ListView itemList;
        //String[] terms;
        boolean doubleBackToExitPressedOnce = false;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            alertDialog1 = new Dialog(this);

            getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
            );

            gridLayout = findViewById(R.id.gridLayout);
            gridLayout.setVisibility(View.VISIBLE);
            filterText = findViewById(R.id.search);
            itemList = (ListView) findViewById(R.id.listView);

            dictionarySQLiteDatabase dbBackend = new dictionarySQLiteDatabase(main.this);
            final String[] terms = dbBackend.allwords();

            listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, terms);
            itemList.setAdapter(listAdapter);
            itemList.setVisibility(View.GONE);
            itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Toast.makeText(getApplicationContext(), "Position " + position, Toast.LENGTH_LONG).show();
                    Object o = ((ListView) parent).getItemAtPosition(position);
                    String s = (String) o;
                    Intent intent = new Intent(main.this, dictionaryDisplay.class);
                    intent.putExtra("DICTIONARY_ID", Arrays.asList(terms).indexOf(s));
                    startActivity(intent);
                }
            });


            getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
            );



            filterText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    if (newText.length() == 0) {
                        itemList.setVisibility(View.GONE);
                        gridLayout.setVisibility(View.VISIBLE);
                    } else {
                        main.this.listAdapter.getFilter().filter(newText);
                        itemList.setVisibility(View.VISIBLE);
                        gridLayout.setVisibility(View.GONE);
                    }
                    return false;
                }
            });
        }

    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
    }

    public void mShare(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Orange Dictionary");
        intent.putExtra(Intent.EXTRA_TEXT,"Hello,\nI just downloaded the Orange Dictionary app, " +
                "a very useful app containing terms and their meanings in the publishing, printing, design and " +
                "illustration industry.\nGet it from: http://play.google.com/store/apps/details?id="
                + getBaseContext().getPackageName());
        startActivity(Intent.createChooser(intent, "Share via..."));

    }

    public void mRandom(View view) {
        Random r=new Random();
        int id = r.nextInt(4000);  // int id = r.nextInt(117124);
        Intent intent = new Intent(main.this, dictionaryDisplay.class);
        intent.putExtra("DICTIONARY_ID", id);
        startActivity(intent);
    }


    public void mFavourites(View view) {
        Intent intent = new Intent(main.this, favourites.class);
        startActivity(intent);
    }

    public void mAbout(View view) {
        Intent intent = new Intent(main.this,about.class);
        startActivity(intent);
    }


    public void mMarks(View view) {
        Intent intent = new Intent(main.this, proofreadingMarks.class);
        startActivity(intent);
    }

    public void mDiacritics(View view) {
        Intent intent = new Intent(main.this,diacritics.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The actionbar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.about) {
            Intent intent = new Intent
                    (main.this,
                            developers.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.rate) {

            Uri uri = Uri.parse("market://details?id=" +
                    getBaseContext().getPackageName());
            Intent goToMarket = new Intent
                    (Intent.ACTION_VIEW, uri);
            // To count with Play market backstack, After pressing back button,
            // to taken back to our application, we need to add following flags to intent.
            goToMarket.addFlags
                    (Intent.FLAG_ACTIVITY_NO_HISTORY |
                            Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                            Intent.FLAG_ACTIVITY_MULTIPLE_TASK |
                            Intent.FLAG_ACTIVITY_NO_HISTORY |
                            Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                            Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            try {
                startActivity(goToMarket);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse
                                ("http://play.google.com/store/apps/details?id="
                                        + getBaseContext().getPackageName())));
                return true;
            }

        }

        else if (id == R.id.feedback) {
            Intent intent = new Intent
                    (main.this, feedback.class);
            startActivity(intent);
            return true;
        }

        else if (id == R.id.help) {
            Intent intent = new Intent
                    (main.this, onBoardingHelp.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        itemList.setVisibility(View.GONE);
        gridLayout.setVisibility(View.VISIBLE);
        Toast.makeText(this, "Touch again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }


}
