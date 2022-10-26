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
import android.widget.EditText;
import android.widget.ImageView;

import javax.security.auth.login.LoginException;

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
        Button buttonprevpage = (Button)findViewById(R.id.prevpage);
        buttonprevpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(fotoVerwerken.this, MainActivity.class));
            }
        });
        Button buttonmailen = (Button)findViewById(R.id.Mailen);
        final EditText textBeschrijving = (EditText)findViewById(R.id.Omschrijving);
        final EditText textMonteurNaam = (EditText)findViewById(R.id.NaamMonteur);
        buttonmailen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View View) {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            //give description
            i.putExtra(Intent.EXTRA_EMAIL, new String[]{textBeschrijving.getText().toString()});
            //give name
            i.putExtra(Intent.EXTRA_SUBJECT, new String[]{textMonteurNaam.getText().toString()});
            //User can choose from mail clients
                try {
                    startActivity(Intent.createChooser(i, "send mail..."));
                }
                //if there are no mail clients available
                catch (android.content.ActivityNotFoundException ex){
                    Log.i("Email Clientstatus", "Er is geen email client op het device");
                }
            }
        });
    }
}