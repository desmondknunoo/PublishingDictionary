package com.deemiensa.dictionary;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

public class favourites extends AppCompatActivity {
    private SearchView filterText;
    private ArrayAdapter<String> listAdapter;
    private TextView textView;
    private ImageView imageView;
    private ListView itemList;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        textView = (TextView) findViewById(R.id.no_favourites);
        imageView = (ImageView) findViewById(R.id.favourites_red);

        imageView.setVisibility(View.GONE);
        textView.setVisibility(View.GONE);

        filterText = (SearchView) findViewById(R.id.search);
        itemList = (ListView) findViewById(R.id.listView);

        favouriteSQLiteDatabase dbBackend = new favouriteSQLiteDatabase(favourites.this);
        final String[] terms = dbBackend.allwords();

        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, terms);
        itemList.setAdapter(listAdapter);
        //itemList.setVisibility(View.INVISIBLE);

        if(listAdapter.isEmpty()){
            filterText.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
        }

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "Position " + position, Toast.LENGTH_LONG).show();
                Object o = ((ListView) parent).getItemAtPosition(position);
                String s = (String) o;
                Intent intent = new Intent(favourites.this, favouriteDisplay.class);
                intent.putExtra("DICTIONARY_WD", s);
                startActivity(intent);
                finish();
            }
        });
        filterText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                favourites.this.listAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }
    @Override
    public void onBackPressed() {
            super.onBackPressed();
            itemList.setVisibility(View.GONE);
            return;
        }
}
