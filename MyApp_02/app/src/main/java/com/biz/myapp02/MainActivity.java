package com.biz.myapp02;

import android.content.Intent;
import android.os.Bundle;

import com.biz.myapp02.ui.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txt1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txt1 = findViewById(R.id.txt1);
        registerForContextMenu(txt1);
        txt1.setOnClickListener(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        registerForContextMenu(fab);
        fab.setOnClickListener(this);
    }// create end

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        /*
            option 메뉴중에 터치된 메뉴와 id값을 추출
         */
        int m_id = item.getItemId();
        if (m_id == R.id.m_login) {

            // Intent는 현재 Activity에서 다른 Activity로 전환하기 위한 클래스
            // 생성자의 현재 Activity의 새로열릴 클래스를 매개변수로 전달
            Intent loginintent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginintent);

            return true;
        } else if (m_id == R.id.m_settings) {
            Intent setIntent = new Intent(MainActivity.this, Main3Activity.class);
            startActivity(setIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
        화면에 표시된 view에 context menu를 설정하기 위한 method
        설정된 view에 따라 반응형 contextMenu 적용이 가능

        contextMenu 설정 순서
        1. menu.xml 리소스를 작성
        2. Activity에서 onCreateCotnextMenu()를 정의한 후
            menu.xml 리소를 Inflator하여 설정
        3. onCreate(0 mehtod에서 contextMenu를 적용할 view에 regiterForContext() 설정
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        int id = v.getId();
        if (id == R.id.txt1) {
            getMenuInflater().inflate(R.menu.menu_hello_context, menu);
        } else if (id == R.id.fab) {
            getMenuInflater().inflate(R.menu.hello_fab_context, menu);
        }


    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        // ContextMenu.ContextMenuInfo m_info = item.getMenuInfo();
        int m_id = item.getItemId();
        if (m_id == R.id.m_fab_con_settings) {
            Intent setIntent = new Intent(MainActivity.this, Main3Activity.class);
            startActivity(setIntent);
            return true;
        }
        return super.onContextItemSelected(item);
    }


    /*
        menu 리소스를 한체 activity와 AppBar에 표시하기 위해서
        메뉴를 설정하는 method
    */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // inflater 확장하다 리소스를 가져와서 코드에 부착하라
        // 코드에 부착하여 사용할 수 있도록 만들어라
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // 메뉴를 activity에 부착할때 제일 먼저 수정할 코드
        return true;

    }

    @Override
    public void onClick(View v) {

        int v_id = v.getId();

        String msg = "반갑습니다";
        if(v_id == R.id.txt1){
            msg += "\n나는 Hello 버튼";
        }else if(v_id == R.id.fab){
            msg += "\n나는 공중에 떠있다";
        }
        Snackbar.make(v,msg,Snackbar.LENGTH_SHORT).show();

    }
}
