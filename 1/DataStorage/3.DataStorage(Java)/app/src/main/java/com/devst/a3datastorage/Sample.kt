package com.devst.a3datastorage

import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
        val stringBuilder = StringBuilder()
        var inputStream: InputStream? = null
        try {
            inputStream = openFileInput(FILE_NAME)
            var read: Int
            while (inputStream.read().also { read = it } != -1) { //eof
                stringBuilder.append(read.toChar())
            }
        } catch (s: Exception) {
            Log.d("FILE ERROR", "ERROR")
        }
        outputText!!.text = stringBuilder
    }

    private fun createfile(view: View) {
        val data = bitpam
        var outputStream: FileOutputStream? = null
        try {
            outputStream = openFileOutput(IMgw_file_name, MODE_PRIVATE)
            data!!.compress(Bitmap.CompressFormat.JPEG, 50, outputStream)
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

    //for all media files
    private val bitpam: Bitmap?
        private get() {
            var image: Bitmap? = null //for all media files
            try {
                val inputStream = assets.open("testimage.webp")
                image = BitmapFactory.decodeStream(inputStream)
            } catch (e: Exception) {
            }
            return image
        }

    private fun FileList(view: View) {}
    private fun deleteFilee(view: View) {}

    companion object {
        private const val FILE_NAME = "myTextFile"
        private const val IMgw_file_name = "images"
    }
}