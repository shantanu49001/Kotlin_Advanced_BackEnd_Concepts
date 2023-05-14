package com.devst.a4kotlin

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.devst.a4kotlin.databinding.ActivityMainBinding
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    //next time me 6 ko kotlin me change and 6 ke aage se start

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //internal storage la path
        val path = filesDir.absolutePath //holds both file reference and path ref


        binding.buttoncreatefile.setOnClickListener {
            createfile()
        }

        binding.readFile.setOnClickListener {
            readFile()
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

    private fun FileList(view: View) {}
    private fun deleteFilee(view: View) {}

    companion object {
        private const val FILE_NAME = "myTextFile"
        private const val IMgw_file_name = "images_file"
    }
}