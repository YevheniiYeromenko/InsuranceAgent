package com.example.insuranceagent.business.info;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.insuranceagent.business.data.model.User;

import java.util.List;

public class InfoViewModel extends ViewModel {
    //TODO: Создать таблицу в Room для Uri фото пользователя. LiveData




    private InfoRepository infoRepository;
    private LiveData<List<User>> user;

    public InfoViewModel() {
        infoRepository = new InfoRepository();
        user = infoRepository.getUser();
    }

    public LiveData<List<User>> getUser() {
        return user;
    }

    public void saveUser(String uri){
        User user = new User();
        user.setId(0);
        user.setUserLogoUri(uri);
        infoRepository.saveUser(user);
    }
}
