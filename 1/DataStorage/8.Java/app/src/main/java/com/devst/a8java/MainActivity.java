package com.devst.a8java;

import static android.os.Environment.DIRECTORY_DOCUMENTS;
import static android.os.Environment.getExternalStorageDirectory;
import static android.os.Environment.getExternalStoragePublicDirectory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ConcurrentModificationException;

//public eternal requires oermissions
//herw ww woll also dee howwe check and request perisniiions pripwly

public class MainActivity extends AppCompatActivity {


    public static final String TAG = "ExternalFiles";
    public static final String FILE_NAME = "demo.txt";
    private final int REQUEST_PERMISSION = 1001;   //permission tupe code
    private boolean permissionGranted;//tracks oermissos given

    private Button readextprivate, readextpub, creatextprivate, crateextpublic;
    private TextView showTextPvt, showTextpublic;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readextprivate = findViewById(R.id.button_read_private);
        readextpub = findViewById(R.id.button_READ_PUBLIC);
        crateextpublic = findViewById(R.id.button_CR_PUB_FILE);
        creatextprivate = findViewById(R.id.button_create_private);
        showTextpublic = findViewById(R.id.SHOW_TXT_Public);
        showTextPvt = findViewById(R.id.show_text);
        et = findViewById(R.id.ET);


        //getexternal storage dr:external drives -->sd
        //getExerstoragepublic dri: external memory of devce   inbuilt
        File file = getExternalStoragePublicDirectory(DIRECTORY_DOCUMENTS);
        showTextpublic.setText(file.getAbsolutePath());

        this.crateextpublic.setOnClickListener(this::createExternlPublic);
        this.readextpub.setOnClickListener(this::readExternalPublic);
    }


    //3.now the codes to create and read eernal public data
    private void readExternalPublic(View view) {
        if (permissionGranted) {
            File file = new File(getExternalFilesDir(DIRECTORY_DOCUMENTS), FILE_NAME);
            String s = readFromFile(file);  //normalfuction that reads and return s data to string

            showTextPvt.setText(s);
        } else {
            checkpermission();
        }
    }

    private void createExternlPublic(View view) {
        if (permissionGranted) {//var storing permissions
            File file = new File(getExternalFilesDir(DIRECTORY_DOCUMENTS), FILE_NAME);
            writetofile(file);//nrmal function

        } else {
            checkpermission();  //check and req-->i can also call this is on create agar button lci k se pehe check krna hai to
        }
    }


    //1.as soo as it starts
    private boolean checkpermission() {
        if (!isexternalStorageReadable() || !isexternalStorageWritable()) {
            Toast.makeText(getApplicationContext(), "The app workds only on permssions", Toast.LENGTH_LONG).show();
            return false;
        }
        //requ the permision-->agar hogi to nhi poochega-->is line me req nhi array for ho rha hai
        int permissionsCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionsCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION); //req code
            return false;
        }
        return true;
    }

    private boolean isexternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);

    }

    private boolean isexternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state));
    }


    //2.what to do if permission have

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            //ou permission code
            case REQUEST_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permissionGranted = true;  //update te var tat we created
                    Toast.makeText(getApplicationContext(), "Permissions granted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "You must grant permissions", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }


    //normal reading and artif  functions that return a string and set a steing after passing a file to them

    private String readFromFile(File file) {//normal reaing and wrting

        FileInputStream fin = null;
        int read;
        StringBuilder sb = new StringBuilder();
        try {
            fin = new FileInputStream(file);
            while ((read = fin.read()) != -1) {
                sb.append((char) read);
            }
        } catch (Exception e) {

        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (Exception e) {

                }
            }
        }
        return sb.toString();
    }

    private void writetofile(File file) {
        FileOutputStream fos = null;
        String data = et.getText().toString();
        try {
            fos = new FileOutputStream(file);
            fos.write(data.getBytes());
        } catch (Exception e) {

        }

        if (fos != null) {
            try {
                fos.close();
            } catch (Exception e) {

            }
        }

    }
}
