package org.techtown.project5.FragmentAdapter;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.project5.R;

import java.util.ArrayList;

public class NoMealsAdapter extends RecyclerView.Adapter {

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public MyViewHolder(View view) {
            super(view);


        }
    }

    private ArrayList<String> meals;

    public NoMealsAdapter(ArrayList<String> meals) {
        this.meals = meals;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_no_meals, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }
}
