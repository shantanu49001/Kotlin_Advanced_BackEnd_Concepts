package com.devst.a7kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devst.a7kotlin.databinding.ActivityMainBinding
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


const val TAG = "ExternalFiles"
const val FILE_NAME = "demo.txt"

class MainActivity : AppCompatActivity() {
    private val REQUEST_PERMISSION = 1001
    private val permissionGranted = false


    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonREADPUBLIC.setOnClickListener {
            readprivate()
        }

        binding.buttonCRPUBFILE.setOnClickListener {
            crteprivate()
        }


        //main
        //nul matlab root   Enviorn. -->specific    custom em liye null ki jagah my or
        val file = getExternalFilesDir(null) //external strage patj
        binding.SHOWTXTPublic.setText(file!!.absolutePath) //intially show public ext path
    }

    private fun crteprivate() {  //no permission nneded
        val file = File(getExternalFilesDir(null), FILE_NAME) //root me public ext name
        writetofile(file) //we made the function
    }

    private fun readprivate() { //no permission meded
        val file = File(getExternalFilesDir(null), FILE_NAME) //root me public ext name
        val a = readFromFile(file) //we mage the function
        binding.SHOWTXTPublic!!.text = a
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
        val data = binding.ET!!.text.toString()
        try {
            fos = FileOutputStream(file)
            fos.write(data.toByteArray())
        } catch (e: Exception) {
        }
        if (fos != null) {
            try {
                fos.close()
            } catch (e: Exception) { } } } }