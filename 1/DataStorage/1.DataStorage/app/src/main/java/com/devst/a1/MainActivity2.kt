package com.devst.a1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class MainActivity2 : AppCompatActivity() {
    private var outputText: TextView? = null
    private var crateFile: Button? = null
    private var readfile: Button? = null
    private var filelist: Button? = null
    private var deletefile: Button? = null
    private var filecontent: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //internal storage la path
        val path = filesDir.absolutePath //holds both file reference and path ref
        crateFile = findViewById(R.id.button_CREATEFILE)
        readfile = findViewById(R.id.button_read_file)
        filelist = findViewById(R.id.button_FILE_LIST)
        deletefile = findViewById(R.id.button_delete_file)
        outputText = findViewById(R.id.show_text)
        filecontent = findViewById(R.id.et_file_content)
        crateFile.setOnClickListener {

        }
        readfile.setOnClickListener(View.OnClickListener { view: View -> readFile(view) })
        filelist.setOnClickListener(View.OnClickListener { view: View -> FileList(view) })
        deletefile.setOnClickListener(View.OnClickListener { view: View -> deleteFilee(view) })
        outputText.setText(path)
    }

    private fun readFile(view: View) {}
     fun createefile() {
        val data = filecontent!!.text.toString() //this char needs to get written
        var outputStream: FileOutputStream? =
            null //declared here co jab clode kare to nayoi value il jaye
        try {
            //internal sotage ,e file write lrni hai mode jiska private hai
            outputStream = openFileOutput(
                FILE_NAME,
                MODE_PRIVATE
            ) //creat3s the file if not [tesent-->app specific
            outputStream.write(data.toByteArray()) //wrte duntion
            outputStream.flush()
            outputText!!.text = "File written "
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

    private fun FileList(view: View) {}
    private fun deleteFilee(view: View) {}

    companion object {
        private const val FILE_NAME = "myTextFile"
    }
}