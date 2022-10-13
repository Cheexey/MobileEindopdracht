package com.example.landstedeeindopdracht;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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


        Button button = (Button)findViewById(R.id.prevpage);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(fotoVerwerken.this, MainActivity.class));
            }
        });
    }
}