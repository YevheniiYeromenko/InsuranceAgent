package com.example.insuranceagent.business.info;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.insuranceagent.R;
import com.example.insuranceagent.business.data.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

public class InfoFragment extends Fragment {

    private TextView tvUserName;
    private TextView tvUserEmail;
    private ImageView imUserPhoto;

    private ImageView imFilePhoto;

    private FirebaseUser firebaseUser;
    private InfoViewModel infoViewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        infoViewModel = new ViewModelProvider(this).get(InfoViewModel.class);
        infoViewModel.getUser().observe(getActivity(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (users != null)
                Log.e(TAG, "onViewCreated: " + users.get(0).getUserLogoUri());
                assert users != null;
                Glide.with(getContext())
                        .load(users.get(0).getUserLogoUri())
                        .into(imFilePhoto);
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().findViewById(R.id.nav_view).setVisibility(View.VISIBLE);
        Toolbar toolbar = getActivity().findViewById(R.id.main_toolbar);
        toolbar.setTitle("Контактна інформація");
        toolbar.getMenu().setGroupVisible(0, false);

        tvUserName = view.findViewById(R.id.tvUserName);
        tvUserEmail = view.findViewById(R.id.tvUserEmail);
        imUserPhoto = view.findViewById(R.id.imUserPhoto);

        String userName = firebaseUser.getDisplayName();
        String userPhone = firebaseUser.getPhoneNumber();
        String userEmail = firebaseUser.getEmail();
        Uri userPhotoUrl = firebaseUser.getPhotoUrl();

        Log.e(getClass().getName(), "onViewCreated: " + userPhotoUrl);

        tvUserName.setText(userName);
        tvUserEmail.setText(userEmail);
        Glide.with(getContext())
                .load(userPhotoUrl)
                .into(imUserPhoto);


        //From file
        imFilePhoto = view.findViewById(R.id.imFilePhoto);
        Button bGetFromFile = view.findViewById(R.id.bGetFromFile);
        bGetFromFile.setOnClickListener(v -> {

            //Вызываем стандартную галерею для выбора изображения с помощью Intent.ACTION_PICK:
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            //Тип получаемых объектов - image:
            photoPickerIntent.setType("image/*");
            //Запускаем переход с ожиданием обратного результата в виде информации об изображении:
            startActivityForResult(photoPickerIntent, 1);
        });



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
//                    try {

                //Получаем URI изображения, преобразуем его в Bitmap
                //объект и отображаем в элементе ImageView нашего интерфейса:
                final Uri imageUri = data.getData();
//                InfoRepository repository = new InfoRepository();
//                User user = new User();
//                user.setId(1);
//                user.setUserLogoUri(imageUri.getPath());
//                repository.saveUser(user);
                infoViewModel.saveUser(imageUri.toString());
//                        final InputStream imageStream = getContext().getContentResolver().openInputStream(imageUri);
//                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
//                        imFilePhoto.setImageBitmap(selectedImage);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
            }
        }
    }
}
