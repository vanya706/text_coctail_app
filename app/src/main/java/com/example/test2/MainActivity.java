package com.example.test2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.example.test2.DBHelper.TABLE_DRINKS;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<String> mNames = new ArrayList<>();
    ArrayList<Bitmap> mImage = new ArrayList<>();//Bitmap
    ArrayList<String> mAlcoholic = new ArrayList<>();
    ArrayList<String> mGlass = new ArrayList<>();
    ArrayList<String> mInstructions = new ArrayList<>();

    TextView textView;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton imageButton = findViewById(R.id.imageButton2);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(i);
            }
        });

        DataGetFromSQL();
        initRecyclerView();

    }

    private void DataGetFromSQL() {
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DRINKS, null, null, null, null, null, null);

        if (cursor.moveToLast()) {
            textView = findViewById(R.id.TextHistory);
            textView.setText("");

            int id_drink = cursor.getColumnIndex(DBHelper.KEY_strDrink);
            int id_alcoholic = cursor.getColumnIndex(DBHelper.KEY_strAlcoholic);
            int id_glass = cursor.getColumnIndex(DBHelper.KEY_strGlass);
            int id_instructions = cursor.getColumnIndex(DBHelper.KEY_strInstructions);
            int id_image = cursor.getColumnIndex(DBHelper.KEY_strDrinkThumb);
            do {
                mNames.add(cursor.getString(id_drink));
                mAlcoholic.add(cursor.getString(id_alcoholic));
                mGlass.add(cursor.getString(id_glass));
                mInstructions.add(cursor.getString(id_instructions));
                mImage.add(BitmapFactory.decodeByteArray(cursor.getBlob(id_image), 0, cursor.getBlob(id_image).length));

            } while (cursor.moveToPrevious());
            cursor.close();
        }
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view_main);
        RecyclerViewAdapterMain adapter = new RecyclerViewAdapterMain(this, mNames, mImage, mAlcoholic, mGlass, mInstructions);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}