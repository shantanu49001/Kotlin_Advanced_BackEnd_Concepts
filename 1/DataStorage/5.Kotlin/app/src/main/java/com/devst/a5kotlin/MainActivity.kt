package com.devst.a5kotlin

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.devst.a5kotlin.databinding.ActivityMainBinding


import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity() {

   lateinit var binding:ActivityMainBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= com.devst.a5kotlin.databinding.ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val path = filesDir.absolutePath

        binding.buttoncreatefile.setOnClickListener {
            createfile()
        }


        binding.readFile.setOnClickListener {
            readFile()
        }
        binding.buttonlist.setOnClickListener {
            FileList()
        }
        binding.buttonDeleteFile.setOnClickListener {
            deleteFilee()
        }


    }

    private fun readFile() {
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

    private val bitpam: Bitmap?
        private get() {
            var image: Bitmap? = null
            try {
                val inputStream = assets.open("aa.jpg")
                image = BitmapFactory.decodeStream(inputStream)
            } catch (e: Exception) {
            }
            return image
        }

    private fun FileList() {
        val filelist = fileList() //inbuilt  intenral storage ki saari files leke aa jayega
        for (filename in filelist) {
            binding.textDataa!!.text = """
                $filename
                
                """.trimIndent()
        }
    }

    private fun deleteFilee() {
//buiting method
        val deletefilee = deleteFile(IMgw_file_name)
        Toast.makeText(this, "File delete$deletefilee", Toast.LENGTH_LONG).show()
    }

    companion object {
        private const val FILE_NAME = "myTextFile"
        private const val IMgw_file_name = "images_file"
    }
}
