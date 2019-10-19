package org.techtown.project5.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import org.techtown.project5.Fragment.Breakfast;
import org.techtown.project5.Fragment.Dinner;
import org.techtown.project5.Fragment.Lunch;
import org.techtown.project5.FragmentAdapter.Fragment_Adapter;
import org.techtown.project5.Handler.BackPressCloseHandler;
import org.techtown.project5.Network.Data;
import org.techtown.project5.Network.NetRetrofit;
import org.techtown.project5.Network.response.Response;
import org.techtown.project5.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;


public class MealsActivity extends AppCompatActivity {

    // SchoolActivity 에서 SharedPreferences 형태로 부가 데이터를 전달한 값을 받을 변수
    String schoolName;

    long mNow;
    Date mDate;

    // 24시간대 형식으로 진행하려면 kk 를 사용하면 됩니다.
    SimpleDateFormat mFormat = new SimpleDateFormat("kk : mm : ss", Locale.KOREA);
    SimpleDateFormat date = new SimpleDateFormat("HH", Locale.KOREA);
    Date today = new Date();
    private CountDownTimer _timer;
    public static int check = 0;

    // View
    TextView time_textView;
    Toolbar toolbar;
    TextView school_name;
    TabLayout tabLayout;
    ViewPager viewPager;

    // Adapter
    Fragment_Adapter adapter;
    private BackPressCloseHandler backPressCloseHandler;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals_activitiy);

        // View
        time_textView = findViewById(R.id.time_textView);
        toolbar = findViewById(R.id.toolbar);
        school_name = findViewById(R.id.school_name);
        tabLayout = findViewById(R.id.layout_tab);
        viewPager = findViewById(R.id.pager);

        SharedPreferences sf = getSharedPreferences("sFile", MODE_PRIVATE);

        // SharedPreferences 로 부터 전달 받은 부가 데이터
        schoolName = sf.getString("schoolName","");

        _timer = new CountDownTimer(10 * 1000, 1000) {   //_timer 객체에 10*1000 밀리초(10초) 가 1000밀리초마다 1씩달게한다.

            public void onTick(long millisUntilFinished) {

                time_textView.setText(getTime());

            }

            public void onFinish() {

                _timer.cancel();
                _timer.start(); // 끝났을때 재설정을 통한 무한 반복문 실행

            }

        };
        _timer.start();

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        school_name.setText(schoolName);

        tabLayout.addTab(tabLayout.newTab().setText("아침"));
        tabLayout.addTab(tabLayout.newTab().setText("점심"));
        tabLayout.addTab(tabLayout.newTab().setText("저녁"));

        adapter = new Fragment_Adapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        backPressCloseHandler = new BackPressCloseHandler(this);

        String test = date.format(today);

        if(Integer.parseInt(test) < Integer.parseInt("20")){

        }

    }

    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }

    @Override
    public void onBackPressed() {

        backPressCloseHandler.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.actionbar_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.search_school:

                Intent intent = new Intent(MealsActivity.this, SchoolActivity.class);
                startActivity(intent);

                check = 1;
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;

            case R.id.developer_introduce:

                Snackbar.make(getWindow().getDecorView().getRootView(), "개발자 소개 파트는 아직 준비 중입니다.", Snackbar.LENGTH_LONG).show();
                break;

            case R.id.bug_report:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://forms.gle/rD8MAjQgVZfQ4yVv9")));
                break;

        }

        return true;
    }

}
