package com.devst.a6java;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    //next time me 6 ko kotlin me change and 6 ke aage se start

    private ImageView outputImage;
    private Button crateFile,readfile,filelist,deletefile,createdir,read_data_custome;
    private EditText filecontent;
    private TextView outputtext;
    private static final String FILE_NAME="myTextFile";
    private static final String IMgw_file_name="images_file";

    //custom dir
    private static final String CUSTOM_DIR="my_dir";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //internal storage la path
        String path= getFilesDir().getAbsolutePath() ; //holds both file reference and path ref
        crateFile=findViewById(R.id.buttoncreatefile);
        readfile=findViewById(R.id.read_file);
        filelist=findViewById(R.id.buttonlist);
        deletefile=findViewById(R.id.button_delete_file);
        outputImage =findViewById(R.id.imageView);
        filecontent=findViewById(R.id.et_file_content);
        outputtext=findViewById(R.id.text_dataa);
        createdir=findViewById(R.id.button_create_dir);
        read_data_custome=findViewById(R.id.button_customdataread);

        this.crateFile.setOnClickListener(this::createfile);
        this.readfile.setOnClickListener(this::readFile);
        this.filelist.setOnClickListener(this::FileList);
        this.deletefile.setOnClickListener(this::deleteFilee);
        this.createdir.setOnClickListener(this::createdir);
        this.read_data_custome.setOnClickListener(this::readcustomdata);
        outputtext.setText(path);
       





    }

    private void readcustomdata(View view) {
      File path=getDir(CUSTOM_DIR,MODE_PRIVATE);
      File file=new File(path,"abc.txt");
      if (file.exists()){
          Toast.makeText(getApplicationContext(),"File exists in "+path,Toast.LENGTH_LONG).show();

      }
      else{
          Toast.makeText(getApplicationContext(),"Couldmn't find fi;e in custom dir",Toast.LENGTH_LONG).show();
      }
    }

    private void createdir(View view) {
        File path=getDir(CUSTOM_DIR,MODE_PRIVATE);//ony t this app
        //if fir extsts  then use else it wi;; create-->root dir me custom dir createes

        File file=new File(path,"abc.txt");
         String data="This is the custom dir";
        try {
            FileOutputStream output=new FileOutputStream(file);
            output.write(data.getBytes());
        }catch (Exception e){

        }






    }

    private void readFile(View view){//pehle crate hui then we are reading it
        Bitmap bitmap=null;
        InputStream inputStream=null;

        try {
            inputStream=openFileInput(IMgw_file_name);
            bitmap= BitmapFactory.decodeStream(inputStream);


        }catch (Exception s){
            Log.d("FILE ERROR","ERROR");
        }

        outputImage.setImageBitmap(bitmap);



    }
    private void createfile(View view) {
        Bitmap data=getBitpam();
        FileOutputStream outputStream=null;
        try {

            outputStream=openFileOutput(IMgw_file_name,MODE_PRIVATE);
            data.compress(Bitmap.CompressFormat.JPEG,50,outputStream);
            outputStream.flush();

        }catch (  FileNotFoundException e){

        }
        catch (IOException e){

        }
        finally {
            if (outputStream!=null){
                try {
                    outputStream.close();

                }catch (IOException e){

                }
            }
        }


    }

    private Bitmap getBitpam(){

        Bitmap image=null;  //for all media files
        try {
            InputStream inputStream=getAssets().open("aa.jpg");
            image= BitmapFactory.decodeStream(inputStream);
        }catch (Exception e){

        }
        return image;
    }

    private void FileList(View view){

    }
    private void deleteFilee(View view){

    }
}