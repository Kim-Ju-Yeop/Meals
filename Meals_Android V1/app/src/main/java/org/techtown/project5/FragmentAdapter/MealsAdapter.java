package org.techtown.project5.FragmentAdapter;

import android.os.Build;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.project5.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MealsAdapter extends RecyclerView.Adapter {

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView meals_textView;

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public MyViewHolder(View view) {
            super(view);

            meals_textView = view.findViewById(R.id.meals_textView);
        }
    }

    private ArrayList<String> meals;

    public MealsAdapter(ArrayList<String> meals) {
        this.meals = meals;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meals, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MyViewHolder myViewHolder = (MyViewHolder) holder;

        myViewHolder.meals_textView.setText(meals.get(position));
    }

    @Override
    public int getItemCount() {

        return meals.size();
    }
}
