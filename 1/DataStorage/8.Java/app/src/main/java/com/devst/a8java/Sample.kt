package com.devst.a8java

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class Sample {
    private val REQUEST_PERMISSION = 1001 //permission tupe code
    private var permissionGranted //tracks oermissos given
            = false
    private var readextprivate: Button? = null
    private var readextpub: Button? = null
    private var creatextprivate: Button? = null
    private var crateextpublic: Button? = null
    private var showTextPvt: TextView? = null
    private var showTextpublic: TextView? = null
    private var et: EditText? = null
    protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        readextprivate = findViewById(R.id.button_read_private)
        readextpub = findViewById(R.id.button_READ_PUBLIC)
        crateextpublic = findViewById(R.id.button_CR_PUB_FILE)
        creatextprivate = findViewById(R.id.button_create_private)
        showTextpublic = findViewById(R.id.SHOW_TXT_Public)
        showTextPvt = findViewById(R.id.show_text)
        et = findViewById(R.id.ET)


        //getexternal storage dr:external drives -->sd
        //getExerstoragepublic dri: external memory of devce   inbuilt
        val file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
        showTextpublic!!.text = file.absolutePath
        crateextpublic!!.setOnClickListener { view: View -> createExternlPublic(view) }
        readextpub!!.setOnClickListener { view: View -> readExternalPublic(view) }
    }

    //3.now the codes to create and read eernal public data
    private fun readExternalPublic(view: View) {
        if (permissionGranted) {
            val file: File = File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), FILE_NAME)
            val s = readFromFile(file) //normalfuction that reads and return s data to string
            showTextPvt!!.text = s
        } else {
            checkpermission()
        }
    }

    private fun createExternlPublic(view: View) {
        if (permissionGranted) { //var storing permissions
            val file: File = File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), FILE_NAME)
            writetofile(file) //nrmal function
        } else {
            checkpermission() //check and req-->i can also call this is on create agar button lci k se pehe check krna hai to
        }
    }

    //1.as soo as it starts
    private fun checkpermission(): Boolean {
        if (!isexternalStorageReadable() || !isexternalStorageWritable()) {
            Toast.makeText(
                getApplicationContext(),
                "The app workds only on permssions",
                Toast.LENGTH_LONG
            ).show()
            return false
        }
        //requ the permision-->agar hogi to nhi poochega-->is line me req nhi array for ho rha hai
        val permissionsCheck =
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (permissionsCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_PERMISSION
            ) //req code
            return false
        }
        return true
    }

    private fun isexternalStorageWritable(): Boolean {
        val state = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED == state
    }

    private fun isexternalStorageReadable(): Boolean {
        val state = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED == state || Environment.MEDIA_MOUNTED_READ_ONLY == state
    }

    //2.what to do if permission have
    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_PERMISSION -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permissionGranted = true //update te var tat we created
                Toast.makeText(getApplicationContext(), "Permissions granted", Toast.LENGTH_LONG)
                    .show()
            } else {
                Toast.makeText(
                    getApplicationContext(),
                    "You must grant permissions",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    //normal reading and artif  functions that return a string and set a steing after passing a file to them
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