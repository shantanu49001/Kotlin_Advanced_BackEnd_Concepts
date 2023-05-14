package com.devst.a3datastoragekotlin

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.devst.a3datastoragekotlin.databinding.ActivityMainBinding
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {

    companion object {  //outer can aceess my items
        private const val FILE_NAME = "myTextFile"
        private const val IMgw_file_name = "images_phone"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //internal storage la path
        val path = filesDir.absolutePath //holds both file reference and path ref
       binding.buttoncreatefile.setOnClickListener {
           createfile()
       }
    }



    private fun createfile() {
        val data = bitpam
        var outputStream: FileOutputStream? = null
        try {
            outputStream = openFileOutput(IMgw_file_name, MODE_PRIVATE)
            data!!.compress(Bitmap.CompressFormat.JPEG, 50, outputStream)
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


}