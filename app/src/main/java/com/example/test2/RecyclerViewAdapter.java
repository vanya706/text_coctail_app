package com.example.test2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

@SuppressLint("Registered")
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<String> mInstructions = new ArrayList<>();
    private ArrayList<String> mAlcoholic = new ArrayList<>();
    private ArrayList<String> mGlass = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context mContext, ArrayList<String> mImageNames, ArrayList<String> mImages,ArrayList<String> mAlcoholic, ArrayList<String> mGlass, ArrayList<String> mInstructions) {
        this.mImageNames = mImageNames;
        this.mImages = mImages;
        this.mContext = mContext;
        this.mAlcoholic = mAlcoholic;
        this.mGlass = mGlass;
        this.mInstructions = mInstructions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d(TAG, "OnBindViewHolder: Called.");

        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.imageView);

        holder.textView.setText(mImageNames.get(position));

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, DetailCoctail.class);
                holder.imageView.buildDrawingCache();
                Bitmap image = holder.imageView.getDrawingCache();

                Bundle extras = new Bundle();
                extras.putParcelable("imageBitMap", image);
                intent.putExtras(extras);
                intent.putExtra("DrinkName", mImageNames.get(position));
                intent.putExtra("Alcoholic", mAlcoholic.get(position));
                intent.putExtra("Glass", mGlass.get(position));
                intent.putExtra("Instructions", mInstructions.get(position));

                mContext.startActivity(intent);
                //Log.d(TAG, "onClick Click on: " + mImageNames.get(position));
                //Toast.makeText(mContext, mImageNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {

        return mImageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView imageView;
        TextView textView;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_coctoil);
            textView = itemView.findViewById(R.id.image_name_text);
            relativeLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}