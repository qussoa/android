package com.biz.img;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.biz.img.domain.Address;
import com.biz.img.utils.InputFieldName;
import com.biz.img.utils.RequestCode;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.provider.MediaStore;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(ActivityCompat.checkSelfPermission(MainActivity.this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            String[] strReq = new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    "갤러리 접근 권한 필요"
            };
            ActivityCompat.requestPermissions(MainActivity.this,strReq,100);
        }



        imageView = findViewById(R.id.image_view);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                    Intent : 안드로이드에서 보여지는 화면
                    다른 화면을 보이고자 할때는
                    Intent를 만들고 startActivity() method를 사용하여
                    Intent를 열어 준다
                */
                // 새로운 창을 열어 값을 입력받고 입력받은 값을 다시 되돌려 받기 위한 코드
                //  Intent intent = new Intent(MainActivity.this, InputActivity.class);
                // startActivity(intent);
                // requestCode : 열리는 Acitivity와 열린 Activity가
                // 서로 데이터를 교환할때 사용할 ID값
                //  startActivityForResult(intent, RequestCode.REQUEST_INPUT_CODE);

                // 폰에 저장된 이미지를 불러와서
                // content.xml ImageView에 표시를 하려고 한다

                // 폰에 저장된 이미지 정보를 보기위한 시스템 내장 Intent를 호출
                Intent imgIntent = new Intent(Intent.ACTION_PICK);

                // 지금부터 이미지 저장 폴더에 접근을 하겠다라는 설정
                // 내장메모리, 외장 SD카드가 있는데 모두 접근 가능
                imgIntent.setType(MediaStore.Images.Media.CONTENT_TYPE);

                //이미지 저장하는 공간
                imgIntent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(imgIntent,RequestCode.REQUEST_IMAGE_CODE);


            }
        });
    }// end onCreate()

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 이미지 갤러리가 보내는 데이터를 받아서 ImageView에 이미지 표시
        if (requestCode == RequestCode.REQUEST_IMAGE_CODE) {
            try {
                InputStream is = getContentResolver().openInputStream(data.getData());
                Bitmap img = BitmapFactory.decodeStream(is);

                is.close();

                imageView.setImageBitmap(img);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == RequestCode.REQUEST_INPUT_CODE) {
            if (resultCode == RequestCode.RESULT_OK) {

                String strName = data.getStringExtra(InputFieldName.TXT_NAME);
                String strTel = data.getStringExtra(InputFieldName.TXT_TEL);
                String strAddr = data.getStringExtra(InputFieldName.TXT_ADDR);

                Address addr = (Address) data.getSerializableExtra("ADDR");

                strName = addr.getA_name();
                strTel = addr.getA_tel();
                strAddr = addr.getA_addr();

                String msg = String.format("이름 : %s\n" + "전화번호 : %s\n" + "주소 : %s\n", strName, strTel, strAddr);

                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
            } else if (resultCode == RequestCode.RESULT_ERROR) {
                Toast.makeText(MainActivity.this, "입력 오류", Toast.LENGTH_SHORT).show();
            }

        }
    }

    /*
        현재 Activity에서 새로운 Activity를 열었을때
        새로운 Activity가 어떤 값을 되도렬 주면
        그 값을 수신할 method
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
