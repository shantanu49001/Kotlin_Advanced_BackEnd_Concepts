package com.devst.a1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;








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
        crateFile=findViewById(R.id.button_CREATEFILE);
        readfile=findViewById(R.id.button_read_file);
        filelist=findViewById(R.id.button_FILE_LIST);
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

    }
    private void createfile(View view) {
      String data=filecontent.getText().toString();    //this char needs to get written
      FileOutputStream outputStream=null;//declared here co jab clode kare to nayoi value il jaye
        try {
          //internal sotage ,e file write lrni hai mode jiska private hai
             outputStream=openFileOutput(FILE_NAME,MODE_PRIVATE); //creat3s the file if not [tesent-->app specific
                outputStream.write(data.getBytes()); //wrte duntion
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