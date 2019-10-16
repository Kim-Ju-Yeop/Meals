package org.techtown.project5.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import org.techtown.project5.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // SharedPreferences 속성
        SharedPreferences sf = getSharedPreferences("sFile", MODE_PRIVATE);

        // check 변수에 getInt 속성을 사용하여 SchoolActivity 클래스에서 check 라는 이름으로 저장한 값을 불러옵니다.
        int check = sf.getInt("check",0);

        // check 변수의 값이 1이라면 사용자는 이미 애플리케이션을 사용한 적이 있습니다.
        if(check == 1){

           Intent checkMeals = new Intent(MainActivity.this, MealsActivity.class);
           startActivity(checkMeals);

        }

        // check 변수의 값이 0 이라면 사용자는 애플리케이션을 이번에 처음으로 사용하는 것입니다.
        else if(check == 0){

            Intent searchSchool = new Intent(MainActivity.this, SchoolActivity.class);

            startActivity(searchSchool);
        }


    }




}
