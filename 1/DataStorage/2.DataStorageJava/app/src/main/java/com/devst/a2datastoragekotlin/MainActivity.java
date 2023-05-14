package com.devst.a2datastoragekotlin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

//hee we are reading a file from intetnal storage

public class MainActivity extends AppCompatActivity {

    private TextView outputText;
    private Button crateFile,readfile,filelist,deletefile;
    private EditText filecontent;

    private static final String FILE_NAME="myTextFile";

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
        outputText=findViewById(R.id.show_text);
        filecontent=findViewById(R.id.et_file_content);

        this.crateFile.setOnClickListener(this::createfile);
        this.readfile.setOnClickListener(this::readFile);
        this.filelist.setOnClickListener(this::FileList);
        this.deletefile.setOnClickListener(this::deleteFilee);


        outputText.setText(path);

    }

    private void readFile(View view){
    //read a file--->text file or non media file char by char read hogi
        //bytes
        StringBuilder stringBuilder=new StringBuilder();
        InputStream inputStream=null;  //same intialised with null so that it can be used later
        //to read file from outside
        try {
            inputStream=openFileInput(FILE_NAME);  //opens the file that is presen with name in internal storage
            int read;  //to store the integer read
            while((read=inputStream.read())!=-1){//eof
                stringBuilder.append((char)read);


            }
        }catch (Exception s){
            Log.d("FILE ERROR","ERROR");
        }


   outputText.setText(stringBuilder);


    }
    private void createfile(View view) {
        String data=filecontent.getText().toString();
        FileOutputStream outputStream=null;
        try {

            outputStream=openFileOutput(FILE_NAME,MODE_PRIVATE);
            outputStream.write(data.getBytes());
            outputStream.flush();
            outputText.setText("File written ");
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
    private void FileList(View view){

    }
    private void deleteFilee(View view){

    }
}