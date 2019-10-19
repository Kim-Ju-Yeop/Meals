package org.techtown.project5.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import org.techtown.project5.Adapter.Adapter;
import org.techtown.project5.Handler.BackPressCloseHandler;
import org.techtown.project5.Model.DTO;
import org.techtown.project5.Network.Data;
import org.techtown.project5.Network.NetRetrofit;
import org.techtown.project5.Network.response.Response;
import org.techtown.project5.Network.retrofit.interfaces.School;
import org.techtown.project5.R;

import retrofit2.Call;
import retrofit2.Callback;


import static org.techtown.project5.Activity.MealsActivity.check;


public class SchoolActivity extends AppCompatActivity {

    // View
    EditText editText;
    ImageButton imageButton;
    ListView listView;
    Adapter adapter;
    TextView textView;
    LinearLayout question_layout;
    EditText searchSchool;
    LinearLayout noSchool_layout;

    private BackPressCloseHandler backPressCloseHandler;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_school);

        // View
        editText = (EditText) findViewById(R.id.searchSchool_editText);
        imageButton = (ImageButton) findViewById(R.id.searchSchool_imageButton);
        listView = (ListView) findViewById(R.id.school_list);
        textView = (TextView) findViewById(R.id.meals);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        question_layout = (LinearLayout) findViewById(R.id.question_layout);
        searchSchool = (EditText) findViewById(R.id.searchSchool_editText);
        noSchool_layout = (LinearLayout) findViewById(R.id.noSchool_layout);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        adapter = new Adapter();
        listView.setAdapter(adapter);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onSearch();
            }
        });

        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        // 키보드 확인 버튼 클릭 시 검색
        editText.setImeOptions(EditorInfo.IME_ACTION_DONE);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(actionId == EditorInfo.IME_ACTION_DONE){

                    onSearch();
                }

                return false;
            }
        });

        backPressCloseHandler = new BackPressCloseHandler(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_school, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.info:
                Intent intent = new Intent(SchoolActivity.this, IntroduceActivity.class);
                startActivity(intent);

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }

        return true;
    }

    // 서버와 통신을 할 매서드입니다.
    public void onSearch(){

        // editName 변수에 사용자가 입력한 학교이름을 저장하고있습니다.
        String editName = editText.getText().toString();
        question_layout.setVisibility(View.INVISIBLE);

        // Call 클래스를 Response, Data 클래스와 함께 호출하여 res 변수를 생성하였습니다.
        // res 변수에 NetRetrofit 속성을 사용하여 사용자가 입력한 값을 저장해둔 editName 변수를 전달합니다.
        final Call<Response<Data>> res = NetRetrofit.getInstance().getSchool().searchSchool(editName);
        res.enqueue(new Callback<Response<Data>>() {

            // 서버 통신이 정확히 되었을 때 발생하는 매서드
            @Override
            public void onResponse(Call<Response<Data>> call, retrofit2.Response<Response<Data>> response) {

                // response 변수의 body() 값이 null 이 아닐 때 조건이 성립합니다.
                if (response.body() != null) {

                    // String 배열을 사용하여 contents 배열과 titles 배열을 선언하였습니다.
                    // 각각 사용자가 입력한 학교의 크기를 불러와 저장합니다.
                    // response.body().getData().getSchoolInfo().size() 의 값에는 중복된 이름을 가진 학교의 수가 나옵니다.
                    // 예를 들면 중복된 이름의 학교가 3개이면 3개가 출력됩니다.
                    String[] contents = new String[response.body().getData().getSchoolList().size()];
                    String[] titles = new String[response.body().getData().getSchoolList().size()];

                    // SchoolName, SchoolLocal 변수를 선언 및 초기화합니다.
                    String SchoolName = "";
                    String SchoolLocal = "";


                    // response 변수의 body() 값에 데이터가 null 이 아닐 때 조건이 성립합니다.
                    if (response.body().getData() != null) {

                        // adapter 인스턴스를 모두 초기화 시킵니다.
                        adapter.clear();

                        // 변수 A 를 사용자가 입력한 학교의 크기 보다 클 때 까지 반복문을 형성합니다.
                        // 즉 중복된 학교가 3개이면 3개의 학교 정보를 모두 입력받아 저장합니다.
                        for (int A = 0; A < response.body().getData().getSchoolList().size(); A++) {

                            // DTO 클래스를 참조해서 dto 인스턴스를 생성합니다.
                            DTO dto = new DTO();

                            // 앞에서 선언한 SchoolName, SchoolLocal 변수에 각각의 학교 이름, 위치를 저장합니다.
                            SchoolName = response.body().getData().getSchoolList().get(A).getSchool_name();
                            SchoolLocal = response.body().getData().getSchoolList().get(A).getSchool_locate();

                            // 앞에서 선언한 contents[], titles[] 배열에 저장한 값들을 저장합니다.
                            // contents 에는 학교의 위치, titles 에는 학교의 이름을 저장합니다.
                            contents[A] = SchoolLocal;
                            titles[A] = SchoolName;

                            // dto 인스턴스의 제목을 titles[] 배열의 각각의 인덱스에 포함된 이름을 저장합니다.
                            dto.setTitle(titles[A]);

                            // dto 인스턴스의 내용에 contents[] 배열의 각각의 인덱스에 포함된 위치를 저장합니다.
                            dto.setContent(contents[A]);

                            // adapter 의 속성인 addItem 을 사용하여 dto 인스턴스를 추가합니다.
                            adapter.addItem(dto);
                        }
                    }

                    // 그 후 listView 를 보이게합니다.
                    listView.setVisibility(View.VISIBLE);


                    Log.e("[Okay]", "학교 검색을 정상적으로 수행하였습니다.");
                    question_layout.setVisibility(View.INVISIBLE);
                    noSchool_layout.setVisibility(View.INVISIBLE);

                } else {

                    // 만약 Data 의 값이 옳게 없다면 listView 를 보이지 않게 합니다.
                    listView.setVisibility(View.GONE);

                    Log.e("[Error]", "학교 검색 기능을 정상적으로 수행하지 못하였습니다.");
                    //Toast.makeText(SchoolActivity.this, "학교 검색을 기능을 정상적으로 수행하지 못하였습니다.", Toast.LENGTH_SHORT).show();

                    noSchool_layout.setVisibility(View.VISIBLE);
                }


            }

            // 서버 통신이 실패하였을 때 발생하는 매서드
            @Override
            public void onFailure(Call<Response<Data>> call, Throwable t) {

                Log.e("[Error]", "네트워크 연결에 오류가 존재합니다.");
                //Toast.makeText(SchoolActivity.this, "네트워크 연결에 오류가 존재합니다.", Toast.LENGTH_SHORT).show();
            }
        });



        // listView 에서 각각의 아이템을 클릭 시 발생하는 클릭 이벤트에 대한 매서드입니다.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView < ? > parent, View view, final int position, long id){

                // SchoolActivity 변수에 사용자가 입력한 학교의 이름을 저장합니다.
                final String editName = (editText.getText().toString());

                // 위에서 한 방식과 동일합니다.
                final Call<Response<Data>> res = NetRetrofit.getInstance().getSchool().searchSchool(editName);
                res.enqueue(new Callback<Response<Data>>() {

                    // 서버 통신이 올바르게 되었을 때 발생하는 이벤트
                    @Override
                    public void onResponse(Call<Response<Data>> call, retrofit2.Response<Response<Data>> response) {

                        Log.e("[Okay]", "학교 선택 기능을 정상적으로 수행하였습니다.");

                        // SharedPreferences 속성
                        SharedPreferences sharedPreferences = getSharedPreferences("sFile", MODE_PRIVATE);

                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        // check 변수에 값 1을 저장합니다.
                        // 정수 1의 의미는 애플리케이션을 사용한 적이 있다는 것을 의미합니다.
                        int check = 1;

                        // check 이름으로 check 변수를 저장합니다.
                        editor.putInt("check",check);

                        // schoolName 으로 사용자가 선택한 학교의 이름을 저장합니다.
                        editor.putString("schoolName", response.body().getData().getSchoolList().get(position).getSchool_name());

                        // officeCode
                        editor.putString("officeCode", response.body().getData().getSchoolList().get(position).getOffice_code());

                        // schoolCode
                        editor.putString("schoolCode", response.body().getData().getSchoolList().get(position).getSchool_code());

                        editor.commit();

                        // 그 후 사용자가 선택한 학교의 급식 정보를 확인할 수있도록 화면을 전환합니다.
                        Intent intent = new Intent(SchoolActivity.this, MealsActivity.class);

                        startActivity(intent);

                        // 화면전환 애니메이션 모션을 추가합니다.
                        overridePendingTransition(R.anim.loadfadein, R.anim.loadfadeout);
                    }

                    // 서버 통신이 올바르게 되지 못하였을 때 발생하는 이벤트
                    @Override
                    public void onFailure(Call<Response<Data>> call, Throwable t) {

                        Log.e("[Error]", "학교 선택 기능을 수행하던 중 오류가 발생하였습니다.");
                        //Toast.makeText(SchoolActivity.this, "학교 선택 기능을 수행하던 중 오류가 발생하였습니다.", Toast.LENGTH_SHORT).show();
                    }

                }
            );}
        });
    }

    @Override
    public void onBackPressed(){

        if(check==1){

            super.onBackPressed();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        } else{
            backPressCloseHandler.onBackPressed();
        }

    }

}
