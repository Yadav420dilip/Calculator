package com.example.calculator;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class history extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        DatabaseHandler dbhandler=new DatabaseHandler(this);
        Cursor cursor =dbhandler.getAllContacts();


        String[] fromColumns = {"Statement","Result_stmt"};
        int[] toViews = {R.id.my_statement, R.id.my_result};


        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.activity_history, cursor, fromColumns, toViews, 0);

            listView =findViewById(R.id.my_list_view);
            listView.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.history_clear,menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() ==R.id.history_clear)
        {
            DatabaseHandler dbhandler=new DatabaseHandler(this);
            dbhandler.delete_contact();
            String[] emptyarray= {""};
            ArrayAdapter adp = new ArrayAdapter<>(this, R.layout.activity_history,R.id.my_statement, emptyarray);
            listView.setAdapter(adp);
        }
        return super.onOptionsItemSelected(item);
    }
}
