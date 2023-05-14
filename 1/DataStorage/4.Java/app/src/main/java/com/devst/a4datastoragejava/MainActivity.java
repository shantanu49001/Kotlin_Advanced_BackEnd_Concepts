package com.devst.a4datastoragejava;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
//next time me 6 ko kotlin me change and 6 ke aage se start

    private ImageView outputText;
    private Button crateFile,readfile,filelist,deletefile;
    private EditText filecontent;

    private static final String FILE_NAME="myTextFile";
    private static final String IMgw_file_name="images_file";

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
        outputText=findViewById(R.id.imageView);
        filecontent=findViewById(R.id.et_file_content);

        this.crateFile.setOnClickListener(this::createfile);
        this.readfile.setOnClickListener(this::readFile);
        this.filelist.setOnClickListener(this::FileList);
        this.deletefile.setOnClickListener(this::deleteFilee);




    }

    private void readFile(View view){//pehle crate hui then we are reading it
       Bitmap bitmap=null;
        InputStream inputStream=null;

        try {
            inputStream=openFileInput(IMgw_file_name);
            bitmap=BitmapFactory.decodeStream(inputStream);


        }catch (Exception s){
            Log.d("FILE ERROR","ERROR");
        }

   outputText.setImageBitmap(bitmap);



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