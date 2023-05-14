package com.devst.a6kotlin

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.devst.a6kotlin.databinding.ActivityMainBinding
import java.io.*
private const val FILE_NAME = "myTextFile"
private const val IMgw_file_name = "images_file"

//custom dir
private const val CUSTOM_DIR = "my_dir"
class MainActivity : AppCompatActivity() {


    private lateinit var binding:ActivityMainBinding;

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);


        //internal storage la path
        val path = filesDir.absolutePath //holds both file reference and path ref

        binding.textDataa.setText(path)

        binding.buttonCreateDir.setOnClickListener {
            createdir();
        }
        binding.buttonCustomdataread.setOnClickListener {
            readcustomdata();
        }

    }

    private fun readcustomdata() {
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

    private fun createdir() {
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

    private fun readFile() { //pehle crate hui then we are reading it
        var bitmap: Bitmap? = null
        var inputStream: InputStream? = null
        try {
            inputStream = openFileInput(IMgw_file_name)
            bitmap = BitmapFactory.decodeStream(inputStream)
        } catch (s: Exception) {
            Log.d("FILE ERROR", "ERROR")
        }
        binding.imageView!!.setImageBitmap(bitmap)
    }

    private fun createfile() {
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



}