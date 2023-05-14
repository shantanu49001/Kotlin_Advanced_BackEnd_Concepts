package com.devst.a7java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
//extrnal storage

//external storage is everythong apart from app's public area
//it is not a pendrive or external staoarage drive

//a area in device that is sharws by all other applicati0ns

//extral storage --> private and [uvlic files
//getExternalFilesDir-->private --->deleted when app deleted
//getExternalStoragePublicdir-->  public files-->not deleted if app installed

//extranl storage isn't always avaliable -->check for avaliablity forst
//public-->images videos captiued by app
//private-->app specific data like sharedpref to store login info


//extranal storage me avalaibilty of all check
//private me read and write ki permission nhi leni hai
//external public me leni hoti hai

//external private read and write-->in this project binary khud se implment ho jayega
//

public class MainActivity extends AppCompatActivity {

     public static  final String TAG="ExternalFiles";
    public static final String FILE_NAME="demo.txt";
    private final int REQUEST_PERMISSION=1001;
    private boolean permissionGranted;

    private Button readextprivate,readextpub,creatextprivate,crateextpublic;
    private TextView showTextPvt,showTextpublic;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readextprivate=findViewById(R.id.button_read_private);
        readextpub=findViewById(R.id.button_READ_PUBLIC);
        crateextpublic=findViewById(R.id.button_CR_PUB_FILE);
        creatextprivate=findViewById(R.id.button_create_private);
        showTextpublic=findViewById(R.id.SHOW_TXT_Public);
        showTextPvt=findViewById(R.id.show_text);
        et=findViewById(R.id.ET);

        this.readextpub.setOnClickListener(this::readpub);
        this.crateextpublic.setOnClickListener(this::crtepublic);
//main
        //nul matlab root   Enviorn. -->specific    custom em liye null ki jagah my or
        File file=getExternalFilesDir(null); //external strage patj
        showTextpublic.setText(file.getAbsolutePath());//intially show public ext path



    }




    private void crtepublic(View view) {  //no permission nneded
       File file=new File(getExternalFilesDir(null),FILE_NAME);//root me public ext name
      writetofile(file);//we made the function
    }

    private void readpub(View view) {//no permission meded
        File file=new File(getExternalFilesDir(null),FILE_NAME);//root me public ext name
       String a= readFromFile(file);   //we mage the function
       showTextpublic.setText(a.toString());



    }

    private String readFromFile(File file){//normal reaing and wrting

        FileInputStream fin=null;
        int read;
        StringBuilder sb=new StringBuilder();
        try {
            fin=new FileInputStream(file);
            while ((read=fin.read())!=-1){
                sb.append((char)read);
            }
        }catch (Exception e){

        }
        finally {
            if (fin!=null){
                try {
                    fin.close();
                }catch (Exception e){

                }
            }
        }
        return  sb.toString();
    }
    private void writetofile(File file){
        FileOutputStream fos=null;
        String data=et.getText().toString();
        try {
            fos=new FileOutputStream(file);
            fos.write(data.getBytes());
        }catch (Exception e){

        }

        if (fos!=null){
            try {
                fos.close();
            }catch (Exception e){

            }
        }

    }
}