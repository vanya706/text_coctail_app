package com.example.test2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "contactDb";
    public static final String TABLE_DRINKS = "drinks";

    public static final String KEY_ID = "_id";//int
    public static final String KEY_strDrink = "strDrink";
    public static final String KEY_strAlcoholic = "strAlcoholic";
    public static final String KEY_strGlass = "strGlass";
    public static final String KEY_strInstructions = "strInstructions";

    public static final String KEY_strDrinkThumb = "strDrinkThumb";//image

    public static final String KEY_strIngredient1 = "strIngredient1";
    public static final String KEY_strIngredient2 = "strIngredient2";
    public static final String KEY_strIngredient3 = "strIngredient3";
    public static final String KEY_strIngredient4 = "strIngredient4";
    public static final String KEY_strIngredient5 = "strIngredient5";
    public static final String KEY_strIngredient6 = "strIngredient6";
    public static final String KEY_strIngredient7 = "strIngredient7";
    public static final String KEY_strIngredient8 = "strIngredient8";
    public static final String KEY_strIngredient9 = "strIngredient9";
    public static final String KEY_strIngredient10 = "strIngredient10";
    public static final String KEY_strIngredient11 = "strIngredient11";
    public static final String KEY_strIngredient12 = "strIngredient12";
    public static final String KEY_strIngredient13 = "strIngredient13";
    public static final String KEY_strIngredient14 = "strIngredient14";
    public static final String KEY_strIngredient15 = "strIngredient15";

    public static final String KEY_strMeasure1 = "strMeasure1";
    public static final String KEY_strMeasure2 = "strMeasure2";
    public static final String KEY_strMeasure3 = "strMeasure3";
    public static final String KEY_strMeasure4 = "strMeasure4";
    public static final String KEY_strMeasure5 = "strMeasure5";
    public static final String KEY_strMeasure6 = "strMeasure6";
    public static final String KEY_strMeasure7 = "strMeasure7";
    public static final String KEY_strMeasure8 = "strMeasure8";
    public static final String KEY_strMeasure9 = "strMeasure9";
    public static final String KEY_strMeasure10 = "strMeasure10";
    public static final String KEY_strMeasure11 = "strMeasure11";
    public static final String KEY_strMeasure12 = "strMeasure12";
    public static final String KEY_strMeasure13 = "strMeasure13";
    public static final String KEY_strMeasure14 = "strMeasure14";
    public static final String KEY_strMeasure15 = "strMeasure15";


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_DRINKS + "(" +
                KEY_ID + " integer primary key," + KEY_strDrink + " text," +
                KEY_strAlcoholic + " text," + KEY_strGlass + " text," +
                KEY_strInstructions + " text," + KEY_strDrinkThumb + " blob," +

                KEY_strIngredient1 + " text," + KEY_strIngredient2 + " text," +
                KEY_strIngredient3 + " text," + KEY_strIngredient4 + " text," +
                KEY_strIngredient5 + " text," + KEY_strIngredient6 + " text," +
                KEY_strIngredient7 + " text," + KEY_strIngredient8 + " text," +
                KEY_strIngredient9 + " text," + KEY_strIngredient10 + " text," +
                KEY_strIngredient11 + " text," + KEY_strIngredient12 + " text," +
                KEY_strIngredient13 + " text," + KEY_strIngredient14 + " text," +
                KEY_strIngredient15 + " text," +

                KEY_strMeasure1+ " text," + KEY_strMeasure2 + " text," +
                KEY_strMeasure3+ " text," + KEY_strMeasure4 + " text," +
                KEY_strMeasure5+ " text," + KEY_strMeasure6 + " text," +
                KEY_strMeasure7+ " text," + KEY_strMeasure8 + " text," +
                KEY_strMeasure9+ " text," + KEY_strMeasure10 + " text," +
                KEY_strMeasure11 + " text," + KEY_strMeasure12 + " text," +
                KEY_strMeasure13 + " text," + KEY_strMeasure14 + " text," +
                KEY_strMeasure15 + " text" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
