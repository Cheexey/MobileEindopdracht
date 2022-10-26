package com.example.landstedeeindopdracht;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;

import android.content.Intent;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;

import javax.security.auth.login.LoginException;


public class MainActivity extends AppCompatActivity {
    //presets
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public Bitmap Foto;
    public static final int REQUEST_CODE_PERMISSION = 2;

    //Create TakePicture Intent
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        String[] mPermission = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        int check = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if(check != 0){
            ActivityCompat.requestPermissions(MainActivity.this, mPermission, REQUEST_CODE_PERMISSION);
        }
        //Open camera button
        Button button = (Button)findViewById(R.id.StartFotoEvent);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If the device does have a camera
                if(getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
                    Log.i("camera", "Device has camera");
                    dispatchTakePictureIntent();
                }
                else{
                    //If the device does not have a camera
                    Log.i("camera", "Device does not have camera");
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Foto = imageBitmap;
            Intent NewActivity = new Intent(MainActivity.this, fotoVerwerken.class);
            //Puts the picture in the new activity
            NewActivity.putExtra("BitmapImage", Foto);
            //Start new activity
            startActivity(NewActivity);

            try {
                String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
                String filename = "myFile.png";
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), filename);
                FileOutputStream out = new FileOutputStream(file); //HET GAAT HIER FOUT
                Log.i("Success", "File is written to external storage.");
                imageBitmap.compress(Bitmap.CompressFormat.PNG, 100,out);
                out.flush();
                out.close();
            }catch (IOException e){
                Log.i("Error", "File failed to be written to external storage " + e.getMessage());
            }
        }
    }
}