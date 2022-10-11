package com.example.landstedeeindopdracht;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class fotoVerwerken extends AppCompatActivity {
    public Bitmap Foto;


    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_verwerken);
        Log.d("henk", "onActivityResult: henk2");
        ImageView imageView = (ImageView)findViewById(R.id.imageView2);
//            Bundle extras = data.getExtras();
            Intent intent = getIntent();
            Foto = (Bitmap) intent.getParcelableExtra("BitmapImage");
            Log.d("henk", "onActivityResult: henk");
            imageView.setImageBitmap(Foto);
    }
}