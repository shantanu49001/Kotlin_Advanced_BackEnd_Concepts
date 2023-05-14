package com.devst.a2datastoragekotlin

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

class Sample : AppCompatActivity() {
    private var outputText: TextView? = null
    private var crateFile: Button? = null
    private var readfile: Button? = null
    private var filelist: Button? = null
    private var deletefile: Button? = null
    private var filecontent: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //internal storage la path
        val path = filesDir.absolutePath //holds both file reference and path ref
        crateFile = findViewById(R.id.buttoncreatefile)
        readfile = findViewById(R.id.read_file)
        filelist = findViewById(R.id.buttonlist)
        deletefile = findViewById(R.id.button_delete_file)
        outputText = findViewById(R.id.show_text)
        filecontent = findViewById(R.id.et_file_content)
        crateFile.setOnClickListener(View.OnClickListener { view: View -> createfile(view) })
        readfile.setOnClickListener(View.OnClickListener { view: View -> readFile(view) })
        filelist.setOnClickListener(View.OnClickListener { view: View -> FileList(view) })
        deletefile.setOnClickListener(View.OnClickListener { view: View -> deleteFilee(view) })
        outputText.setText(path)
    }

    private fun readFile(view: View) {
        //read a file--->text file or non media file char by char read hogi
        //bytes
        val stringBuilder = StringBuilder()
        var inputStream: InputStream? =
            null //same intialised with null so that it can be used later
        //to read file from outside
        try {
            inputStream =
                openFileInput(FILE_NAME) //opens the file that is presen with name in internal storage
            var read: Int //to store the integer read
            while (inputStream.read().also { read = it } != -1) { //eof
                stringBuilder.append(read.toChar())
            }
        } catch (s: Exception) {
            Log.d("FILE ERROR", "ERROR")
        }
        outputText!!.text = stringBuilder
    }

    private fun createfile(view: View) {
        val data = filecontent!!.text.toString()
        var outputStream: FileOutputStream? = null
        try {
            outputStream = openFileOutput(FILE_NAME, MODE_PRIVATE)
            outputStream.write(data.toByteArray())
            outputStream.flush()
            outputText!!.text = "File written "
        } catch (e: FileNotFoundException) {
        } catch (e: IOException) {
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close()
                } catch (e: IOException) {
                }
            }
        }
    }

    private fun FileList(view: View) {}
    private fun deleteFilee(view: View) {}

    companion object {
        private const val FILE_NAME = "myTextFile"
    }
}