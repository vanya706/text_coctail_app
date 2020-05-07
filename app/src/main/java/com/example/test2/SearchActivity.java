package com.example.test2;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SearchActivity extends AppCompatActivity {

    private TextView textViewResult;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<String> mNames = new ArrayList<>();
    ArrayList<String> mImageUrls = new ArrayList<>();
    ArrayList<String> mInstructions = new ArrayList<>();
    ArrayList<String> mAlcoholic = new ArrayList<>();
    ArrayList<String> mGlass = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_search);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerv_view);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        SearchView simpleSearchView = (SearchView) findViewById(R.id.searchView);
        simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Toast.makeText(SearchActivity.this, query.toString(), Toast.LENGTH_SHORT).show();
                textViewResult = (TextView) findViewById(R.id.textView2);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                JsonCoctailApp jsonCoctailApp = retrofit.create(JsonCoctailApp.class);

                Call<PostList> call = jsonCoctailApp.getPosts(query.toString());

                call.enqueue(new Callback<PostList>() {
                    @Override
                    public void onResponse(Call<PostList> call, Response<PostList> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(SearchActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (response.body().getDrinks() == null) {
                            //Toast.makeText(SearchActivity.this, "NULLDRInkssssssss!!!!", Toast.LENGTH_SHORT).show();
                            textViewResult.setText("No cocktails found");
                            return;
                        }

                        List<Drink> drinks = response.body().getDrinks();
                        for (Drink d : drinks) {
                            //Toast.makeText(SearchActivity.this, d.getStrDrink().toString(), Toast.LENGTH_SHORT).show();
                            mImageUrls.add(d.getStrDrinkThumb());
                            mNames.add(d.getStrDrink());
                            mAlcoholic.add(d.getStrAlcoholic());
                            mGlass.add(d.getStrGlass());
                            mInstructions.add(d.getStrInstructions());
                        }

                        initRecyclerView();

                        textViewResult.setText("");

                    }

                    @Override
                    public void onFailure(Call<PostList> call, Throwable t) {
                        Toast.makeText(SearchActivity.this, "FEILLLLDDDDD!!!!!", Toast.LENGTH_SHORT).show();
                        textViewResult.setText(t.getMessage());
                    }
                });

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageUrls, mAlcoholic, mGlass, mInstructions);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
