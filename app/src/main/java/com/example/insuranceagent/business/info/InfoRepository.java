package com.example.insuranceagent.business.info;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.example.insuranceagent.App;
import com.example.insuranceagent.business.data.database.room.UserDao;
import com.example.insuranceagent.business.data.model.User;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class InfoRepository {

    private static final String TAG = "TAG";
    private UserDao userDao;
    private LiveData<List<User>> user;

    public InfoRepository() {
        userDao = App.getInstance().getInsuranceDatabase().userDao();
        user = userDao.getUser();
    }

    public LiveData<List<User>> getUser() {
        return user;
    }

    public void saveUser(User user){
        userDao.saveUser(user)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.e("onComplete", "onComplete: SUCCESS" );
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("onError", "onError: INSERT");
                    }
                });
    }
}
