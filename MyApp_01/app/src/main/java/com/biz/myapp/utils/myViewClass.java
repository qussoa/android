package com.biz.myapp.utils;

import android.view.View;
import android.widget.TextView;

import com.biz.myapp.R;
import com.google.android.material.snackbar.Snackbar;

public class myViewClass implements View.OnClickListener {

    /*
        view class 이야기
        android에서 눈에 보이는 모든 것 (layout button  textview texteit)
        모두 view 클래스를 상속받아서 만들어진 component

        이벤트나 액션을 지정할 떄
        기존의 클래스나 인터페이스를 상속 또는 implement 해야 하는데
        그때 자기 자신의 클래스를 사용하지 않고 View 클래스의 요소들을 상속받아서
        클래스를 작성하는 것이 원칙
     */
    @Override
    public void onClick(View v) {

        /*
            이벤트가 발생하면 onclick() method가 호출될 것이고
            누가, 호출했는가를 알고 싶을때
            v.getId() method를 사용하면 호출한 view(comp)의 id값을 얻어 올 수 있음
         */

        String msg = "반갑습니다";
        if(v.getId() == R.id.btn1){
            msg += "\n 난 버튼";
        }else if(v.getId()  == R.id.txt1){
            /*
                이벤트가 발생한 viw(comp)로부터 어떤 값을 얻어 오고자하면
                해당 view로 형변환(type cast)를 한번 수행해서 객체를 만들고
                만든 객체에서 각 view의 고유한 method를 호출하면된다
             */
            TextView t = (TextView)v;
            msg += "\n" + t.getText();
        }else if(v.getId() == R.id.txt2){
            TextView t = (TextView)v;
            msg += "\n" + t.getText();
        }

        /*
            현재 onclick method btn1 button을 클릭했을 때 호출되는 method
            btn1을 클릭하면 클릭된 button의 모든 요소가 view인 v 객체 변수에 담겨
            on click() method에 전달된다
         */
        Snackbar.make(v, msg, Snackbar.LENGTH_SHORT).show();
    }
}
