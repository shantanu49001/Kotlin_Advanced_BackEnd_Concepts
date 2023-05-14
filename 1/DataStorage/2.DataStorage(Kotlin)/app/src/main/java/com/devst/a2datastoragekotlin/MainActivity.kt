package com.devst.a2datastoragekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.devst.a2datastoragekotlin.databinding.ActivityMainBinding
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //internal storage la path
        val path = filesDir.absolutePath //holds both file reference and path ref
        binding.buttonCRAEEY.setOnClickListener {
            createfile()
        }
        binding.buttonreadfile.setOnClickListener {
            readFile()
        }
        binding.showText.text = path.toString()

    }

    private fun readFile() {
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
        binding.showText!!.text = stringBuilder
    }


    private fun createfile() {
        val data = binding.editText!!.text.toString()
        var outputStream: FileOutputStream? = null
        try {
            outputStream = openFileOutput(FILE_NAME, MODE_PRIVATE)
            outputStream.write(data.toByteArray())
            outputStream.flush()
            binding.showText!!.text = "File written "
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

    private fun FileList() {}
    private fun deleteFilee() {}

    companion object {
        private const val FILE_NAME = "myTextFile"
    }
}