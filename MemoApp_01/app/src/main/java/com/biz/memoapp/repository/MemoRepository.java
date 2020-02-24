package com.biz.memoapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.biz.memoapp.db.MemoDataBase;
import com.biz.memoapp.domain.MemoVO;

import java.util.List;

/*
    DB 접근할때 사용할 Service class
 */
public class MemoRepository {

    private MemoDao mDao;

    public MemoRepository(Application application) {
        MemoDataBase db = MemoDataBase.getInstance(application);
        mDao = db.getMemoDao();
    }
    public LiveData<List<MemoVO>> selectAll(){
        return mDao.selectAll();
    }
    /* thread로 insert 실행 */
    public void insert(MemoVO memoVO){
        MemoDataBase.dbWriterThread.execute(new Runnable() {
            @Override
            public void run() {
                mDao.insert(memoVO);
            }
        });
        /*
            MemoDataBase.databaseWriteExecutor.execute(()->{
          mDao.save(memoVO);
            });
         */
    }

    public void delete(MemoVO memoVO){
        mDao.delete(memoVO);
    }
}
