package com.devst.a6java

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.*

class Sample : AppCompatActivity() {
    //next time me 6 ko kotlin me change and 6 ke aage se start
    private var outputImage: ImageView? = null
    private var crateFile: Button? = null
    private var readfile: Button? = null
    private var filelist: Button? = null
    private var deletefile: Button? = null
    private var createdir: Button? = null
    private var read_data_custome: Button? = null
    private var filecontent: EditText? = null
    private var outputtext: TextView? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //internal storage la path
        val path = filesDir.absolutePath //holds both file reference and path ref
        crateFile = findViewById(R.id.buttoncreatefile)
        readfile = findViewById(R.id.read_file)
        filelist = findViewById(R.id.buttonlist)
        deletefile = findViewById(R.id.button_delete_file)
        outputImage = findViewById(R.id.imageView)
        filecontent = findViewById(R.id.et_file_content)
        outputtext = findViewById(R.id.text_dataa)
        createdir = findViewById(R.id.button_create_dir)
        read_data_custome = findViewById(R.id.button_customdataread)
        crateFile.setOnClickListener(View.OnClickListener { view: View -> createfile(view) })
        readfile.setOnClickListener(View.OnClickListener { view: View -> readFile(view) })
        filelist.setOnClickListener(View.OnClickListener { view: View -> FileList(view) })
        deletefile.setOnClickListener(View.OnClickListener { view: View -> deleteFilee(view) })
        createdir.setOnClickListener(View.OnClickListener { view: View -> createdir(view) })
        read_data_custome.setOnClickListener(View.OnClickListener { view: View ->
            readcustomdata(
                view
            )
        })
        outputtext.setText(path)
    }

    private fun readcustomdata(view: View) {
        val path = getDir(CUSTOM_DIR, MODE_PRIVATE)
        val file = File(path, "abc.txt")
        if (file.exists()) {
            Toast.makeText(applicationContext, "File exists in $path", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(
                applicationContext,
                "Couldmn't find fi;e in custom dir",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun createdir(view: View) {
        val path = getDir(CUSTOM_DIR, MODE_PRIVATE) //ony t this app
        //if fir extsts  then use else it wi;; create-->root dir me custom dir createes
        val file = File(path, "abc.txt")
        val data = "This is the custom dir"
        try {
            val output = FileOutputStream(file)
            output.write(data.toByteArray())
        } catch (e: Exception) {
        }
    }

    private fun readFile(view: View) { //pehle crate hui then we are reading it
        var bitmap: Bitmap? = null
        var inputStream: InputStream? = null
        try {
            inputStream = openFileInput(IMgw_file_name)
            bitmap = BitmapFactory.decodeStream(inputStream)
        } catch (s: Exception) {
            Log.d("FILE ERROR", "ERROR")
        }
        outputImage!!.setImageBitmap(bitmap)
    }

    private fun createfile(view: View) {
        val data = bitpam
        var outputStream: FileOutputStream? = null
        try {
            outputStream = openFileOutput(IMgw_file_name, MODE_PRIVATE)
            data!!.compress(Bitmap.CompressFormat.JPEG, 50, outputStream)
            outputStream.flush()
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
                val inputStream = assets.open("aa.jpg")
                image = BitmapFactory.decodeStream(inputStream)
            } catch (e: Exception) {
            }
            return image
        }

    private fun FileList(view: View) {}
    private fun deleteFilee(view: View) {}

    companion object {
        private const val FILE_NAME = "myTextFile"
        private const val IMgw_file_name = "images_file"

        //custom dir
        private const val CUSTOM_DIR = "my_dir"
    }
}