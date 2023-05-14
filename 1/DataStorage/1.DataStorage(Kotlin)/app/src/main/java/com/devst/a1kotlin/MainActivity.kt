package com.devst.a1kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.devst.a1kotlin.databinding.ActivityMainBinding
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

//it has 3 forms of storage->internal external and sharedPref
//when to use what?
//internal and exetrnal are teo types
//internal-->thse memeory space that is private to our app only i.e. no toher ca acess  ut
//external-->private wrt our app it is used by otehrs as weell wen need to ask permissions for thus

//internal storage files are dleted when app is unimstled-->aways avaliable-->use this when i am sure that it is only app specific
//extenal ae not delered-->not alwys avaloable-->private nd cache dleted


//working wit internal-->no specual permission needed
//can't crate sub directories in internal

//imprtant functions


// openFileOutput-->file name and mode paas -->creates the  files if not present
//write -->write the data in form of bytes to a file
//close->close the fileoituput-->hr file bits me change hoke jati hai


//MODE PRIVATE-->file private to app kr deta hai
//mode append->end me add
//create and write a private space file


//file was created sucessfully-->device file explorer -->data ->app-->search for package name-->files-->[file]
//INTERNAKK ME NO MANIFEST AND PERMISSIONS NEEDED.
const val FILE_NAME = "written_text_file_to_internal"

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val internal_path =
            filesDir.absolutePath  //is path me show hoga-->intrnal storage ka location hai
        binding.dataShow.text = internal_path.toString()

        binding.buttonCratefile.setOnClickListener {
            val data: String? = binding.Datawrite.text.toString()
            if (data != null) {  //error handle
                var outputStream: FileOutputStream? =
                    null  //null here coz clear krne pr aage bhi use ho jaye--->fileoutputstream-->agar file hai to append nhi hai to crrate and add

                try {

                    outputStream = openFileOutput(FILE_NAME, MODE_PRIVATE)//app only interal storage
                    outputStream.write(data.toByteArray())   //wrting into internal storage
                    outputStream.flush()
                    binding.dataShow.text = "FILE WRITTEN"

                } catch (e: Exception) {


                } finally {
                    if (outputStream != null) {
                        try {
                            outputStream.close()
                        } catch (e: IOException) {

                        }
                    }
                }

            } else {
                Toast.makeText(applicationContext, "Empty field", Toast.LENGTH_LONG).show() } } } }