package com.devst.a7java

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

//extrnal storage
//external storage is everythong apart from app's public area
//it is not a pendrive or external staoarage drive
//a area in device that is sharws by all other applicati0ns
//extral storage --> private and [uvlic files
//getExternalFilesDir-->private --->deleted when app deleted
//getExternalStoragePublicdir-->  public files-->not deleted if app installed
//extranl storage isn't always avaliable -->check for avaliablity forst
//public-->images videos captiued by app
//private-->app specific data like sharedpref to store login info
//extranal storage me avalaibilty of all check
//private me read and write ki permission nhi leni hai
//external public me leni hoti hai
//external private read and write-->in this project binary khud se implment ho jayega
//
class MainActivity : AppCompatActivity() {
    private val REQUEST_PERMISSION = 1001
    private val permissionGranted = false
    private var readextprivate: Button? = null
    private var readextpub: Button? = null
    private var creatextprivate: Button? = null
    private var crateextpublic: Button? = null
    private var showTextPvt: TextView? = null
    private var showTextpublic: TextView? = null
    private var et: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        readextprivate = findViewById(R.id.button_read_private)
        readextpub = findViewById(R.id.button_READ_PUBLIC)
        crateextpublic = findViewById(R.id.button_CR_PUB_FILE)
        creatextprivate = findViewById(R.id.button_create_private)
        showTextpublic = findViewById(R.id.SHOW_TXT_Public)
        showTextPvt = findViewById(R.id.show_text)
        et = findViewById(R.id.ET)
        readextpub.setOnClickListener(View.OnClickListener { view: View -> readpub(view) })
        crateextpublic.setOnClickListener(View.OnClickListener { view: View -> crtepublic(view) })
        //main
        //nul matlab root   Enviorn. -->specific    custom em liye null ki jagah my or
        val file = getExternalFilesDir(null) //external strage patj
        showTextpublic.setText(file!!.absolutePath) //intially show public ext path
    }

    private fun crtepublic(view: View) {  //no permission nneded
        val file = File(getExternalFilesDir(null), FILE_NAME) //root me public ext name
        writetofile(file) //we made the function
    }

    private fun readpub(view: View) { //no permission meded
        val file = File(getExternalFilesDir(null), FILE_NAME) //root me public ext name
        val a = readFromFile(file) //we mage the function
        showTextpublic!!.text = a
    }

    private fun readFromFile(file: File): String { //normal reaing and wrting
        var fin: FileInputStream? = null
        var read: Int
        val sb = StringBuilder()
        try {
            fin = FileInputStream(file)
            while (fin.read().also { read = it } != -1) {
                sb.append(read.toChar())
            }
        } catch (e: Exception) {
        } finally {
            if (fin != null) {
                try {
                    fin.close()
                } catch (e: Exception) {
                }
            }
        }
        return sb.toString()
    }

    private fun writetofile(file: File) {
        var fos: FileOutputStream? = null
        val data = et!!.text.toString()
        try {
            fos = FileOutputStream(file)
            fos.write(data.toByteArray())
        } catch (e: Exception) {
        }
        if (fos != null) {
            try {
                fos.close()
            } catch (e: Exception) {
            }
        }
    }

    companion object {
        const val TAG = "ExternalFiles"
        const val FILE_NAME = "demo.txt"
    }
}