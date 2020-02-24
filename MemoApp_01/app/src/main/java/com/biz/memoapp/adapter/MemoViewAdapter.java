package com.biz.memoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.biz.memoapp.R;
import com.biz.memoapp.domain.MemoVO;

import java.util.List;

public class MemoViewAdapter extends RecyclerView.Adapter {

    private Context context = null;
    private List<MemoVO> memoList = null;
    private LayoutInflater layoutInflater = null;

    /*
     MainActivity에서  MemoViewAdapter를 만들때 context와 memoList를 주입할 생성자
     */
    public MemoViewAdapter(Context context, List<MemoVO> memoList) {
        this.context = context;
        this.memoList = memoList;
    }

    public MemoViewAdapter(Context context) {
        this.context = context;
    }

    public void setMemoList(List<MemoVO> memoList){
        // 외부에서 list를 주입받고
        // recycleView에 세팅
        this.memoList = memoList;
        // recycleView에게 알람
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        /*
            memo_item.xml파일을 가져와서 view객체로 생성하기
         */
//
//        View view = LayoutInflater
//                .from(context)
//                .inflate(R.layout.memo_item, parent, false);

        View view = layoutInflater.inflate(R.layout.memo_item,parent,false);
        MemoHolder holder = new MemoHolder(view);

        return holder;
    }

    /*
        memo_item에 설정한 여러가지 view들을 사용할 수 있도록
        초기화하는 과정
     */
    class MemoHolder extends RecyclerView.ViewHolder {

        public TextView m_time;
        public TextView m_date;
        public TextView m_text;

        public MemoHolder(@NonNull View itemView) {
            super(itemView);
            m_time = itemView.findViewById(R.id.m_time);
            m_date = itemView.findViewById(R.id.m_date);
            m_text = itemView.findViewById(R.id.m_text);
        }
    }

    /*
        MemoList의 개수만 생성되어 화면에 표시될때 반복문으로 표출하는 method
        반복문이 호출되면서 몇번째 데이터인가를 position 변수에 주입
     */

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        /*
            가형성
            RecyclerView.ViewHolder를 MemoHolder로 형변환하여
            MemoHolder에 직접 접근할 수 있도록 한다
         */

        MemoHolder memoHolder = (MemoHolder) holder;

        /*
            memoList의 각 아이템 요소를 한개씩 읽ㅇ서
             TextView setText() method를 이용해서 문자열을 채워 넣는다
         */
        memoHolder.m_date.setText(memoList.get(position).getM_date());
        memoHolder.m_time.setText(memoList.get(position).getM_time());
        memoHolder.m_text.setText(memoList.get(position).getM_text());
    }

    @Override
    public int getItemCount() {

        return memoList != null ? memoList.size() : 0;
    }


}
