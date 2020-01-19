package org.techtown.project5.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.techtown.project5.FragmentAdapter.MealsAdapter;
import org.techtown.project5.FragmentAdapter.NoMealsAdapter;
import org.techtown.project5.Network.Data;
import org.techtown.project5.Network.NetRetrofit;
import org.techtown.project5.Network.response.Response;
import org.techtown.project5.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;

import static android.content.Context.MODE_PRIVATE;

public class Dinner extends Fragment {

    // SharedPreferences
    String school_id;
    String office_id;

    // Retrofit
    String[] dinnerList;

    // ArrayList
    ArrayList<String> three = new ArrayList<>();

    // View
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
    TextView date;

    // Date
    Date today = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_dinner, container, false);
        SharedPreferences sf = this.getActivity().getSharedPreferences("sFile", MODE_PRIVATE);

        // SharedPreferences 로 부터 전달 받은 부가 데이터
        school_id = sf.getString("schoolCode", "");
        office_id = sf.getString("officeCode", "");

        // View
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        date = rootView.findViewById(R.id.date);

        date.setText(simpleDateFormat.format(today));

        // recyclerView 설정
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        showDinner();

        return rootView;
    }

    public void showDinner(){

        Call<Response<Data>> res = NetRetrofit.getInstance().getMeals().GetMeals(school_id, office_id);
        res.enqueue(new Callback<Response<Data>>() {
            @Override
            public void onResponse(Call<Response<Data>> call, retrofit2.Response<Response<Data>> response) {

                Log.e("[dinner 서버 통신]", "dinner 서버 통신");

                String dinner;

                try{

                    dinner = response.body().getData().getMeals().getDinnerList();
                } catch(NullPointerException e){

                    dinner = null;
                }

                if(dinner != null){

                    dinnerList = dinner.split("<br/>");

                    for(int i = 0; i < dinnerList.length; i++){

                        three.add(dinnerList[i]);
                    }

                    MealsAdapter adapter = new MealsAdapter(three);
                    recyclerView.setAdapter(adapter);
                } else{

                    three.add(null);
                    NoMealsAdapter adapter = new NoMealsAdapter(three);
                    recyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<Response<Data>> call, Throwable t) {
                Log.e("[서버 통신 X]", "서버 통신이 안됩니다.");
                Toast.makeText(getView().getContext(), "서버통신안됨", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
