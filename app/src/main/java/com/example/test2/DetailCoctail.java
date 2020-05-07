package com.example.test2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

import static com.example.test2.DBHelper.KEY_strAlcoholic;
import static com.example.test2.DBHelper.KEY_strDrink;
import static com.example.test2.DBHelper.KEY_strDrinkThumb;
import static com.example.test2.DBHelper.KEY_strGlass;
import static com.example.test2.DBHelper.KEY_strIngredient1;
import static com.example.test2.DBHelper.KEY_strIngredient10;
import static com.example.test2.DBHelper.KEY_strIngredient11;
import static com.example.test2.DBHelper.KEY_strIngredient12;
import static com.example.test2.DBHelper.KEY_strIngredient13;
import static com.example.test2.DBHelper.KEY_strIngredient14;
import static com.example.test2.DBHelper.KEY_strIngredient15;
import static com.example.test2.DBHelper.KEY_strIngredient2;
import static com.example.test2.DBHelper.KEY_strIngredient3;
import static com.example.test2.DBHelper.KEY_strIngredient4;
import static com.example.test2.DBHelper.KEY_strIngredient5;
import static com.example.test2.DBHelper.KEY_strIngredient6;
import static com.example.test2.DBHelper.KEY_strIngredient7;
import static com.example.test2.DBHelper.KEY_strIngredient8;
import static com.example.test2.DBHelper.KEY_strIngredient9;
import static com.example.test2.DBHelper.KEY_strInstructions;
import static com.example.test2.DBHelper.TABLE_DRINKS;

public class DetailCoctail extends AppCompatActivity {
    ImageView imageView;
    TextView textDrink;
    TextView textAlcoholic;
    TextView textGlass;
    TextView textInstructions;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle(getIntent().getStringExtra("DrinkName"));
        setContentView(R.layout.activity_detail_coctail);

        imageView = findViewById(R.id.imageViewCoctail);
        Bundle extras = getIntent().getExtras();
        Bitmap bmp = (Bitmap) extras.getParcelable("imageBitMap");
        imageView.setImageBitmap(bmp);


        textDrink = findViewById(R.id.textView7);
        textAlcoholic = findViewById(R.id.textView8);
        textGlass = findViewById(R.id.textView9);
        textInstructions = findViewById(R.id.textView12);

        textDrink.setText(getIntent().getStringExtra("DrinkName"));
        textAlcoholic.setText(getIntent().getStringExtra("Alcoholic"));
        textGlass.setText(getIntent().getStringExtra("Glass"));
        textInstructions.setText(getIntent().getStringExtra("Instructions"));

        ContentValues contentValues = new ContentValues();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 0, stream);
        contentValues.put(KEY_strDrinkThumb, stream.toByteArray());

        contentValues.put(KEY_strDrink, getIntent().getStringExtra("DrinkName"));
        contentValues.put(KEY_strAlcoholic, getIntent().getStringExtra("Alcoholic"));
        contentValues.put(KEY_strGlass, getIntent().getStringExtra("Glass"));
        contentValues.put(KEY_strInstructions, getIntent().getStringExtra("Instructions"));
        contentValues.put(KEY_strIngredient1, "");
        contentValues.put(KEY_strIngredient2, "");
        contentValues.put(KEY_strIngredient3, "");
        contentValues.put(KEY_strIngredient4, "");
        contentValues.put(KEY_strIngredient5, "");
        contentValues.put(KEY_strIngredient6, "");
        contentValues.put(KEY_strIngredient7, "");
        contentValues.put(KEY_strIngredient8, "");
        contentValues.put(KEY_strIngredient9, "");
        contentValues.put(KEY_strIngredient10, "");
        contentValues.put(KEY_strIngredient11, "");
        contentValues.put(KEY_strIngredient12, "");
        contentValues.put(KEY_strIngredient13, "");
        contentValues.put(KEY_strIngredient14, "");
        contentValues.put(KEY_strIngredient15, "");


        dbHelper = new DBHelper(this);
        SQLiteDatabase db;
        try {
            db = dbHelper.getWritableDatabase();
        }
        catch (SQLiteException ex){
            db = dbHelper.getReadableDatabase();
        }


        db.insert(TABLE_DRINKS, null, contentValues);
        db.close();

    }
}
