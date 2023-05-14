package com.devst.a1contentproviderjava

import android.os.Bundle
import android.provider.ContactsContract
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

//between app data sharing
//each app is hosted in a seperate process
//App1    and App2 have their own data
//and in some case app2 wants to acess app1's data
//for this app1 must expose it;s data in secure manner
//App 2 that wants to avess tje data makes a content reolver(rquest) to App1
//App1 exposes it' s data via Content Povider and returns a cursor (respomse ) to App2
//real world-->app1 cotats app and app2 whatsAopp
//for app2 it doesn;t matter to it how daa is stored by App1
//getContentResolver-->we also must jabe correct addresses to hit the right data soucr eand it is va;;ed ad URI
//dta is returned by coten provider in form of cursor that is like a API that failtietes a;; these operations
//start sql command to acess data from another app's db
//mprjection-->names of volums of table that we want to avess
//selection claise -->similar to where clause in sql-->where col1=
//in selection clause if we don't want to initalise where= ?   we use selction args to make it avaliable at runtime if null->whre= me selection claise wala chala jayega
//getContentReslver()[object of cotent provideer].query(<ContentProvider>.CONTENT_URI,mProjection,mSelectionClause,mSelectionArgs,mSortOrder)
//MOST OF THE contet provider uri are at anroid.provider(stdy more from docmetnations)
//after definig all these we get the curose on return by content request made
//cursoe !=0-->this checks if the cursor is returnned or not      cursor.count()>0-->checks if there are some colums returned warna table ho aur table me kuch na ho aise nhi hina chahiye
//this will get stuck if i run it on my device -->coz data huge hai and ma ui pr hoga run
//hpwever i can run this on emulator-->contacts hi nhi a
//this wasn't running onn Api level above 22 as main uo containted on haevy api
//reduce min sdk to 21
//if we add contacts and delete it will also get updated in contacts app db as welll
class MainActivity : AppCompatActivity() {
    private var textShw: TextView? = null
    private val mColumnProkection =
        arrayOf( //all the content providers like media loc etc here we are targeting the contacts app-->study more
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
            ContactsContract.Contacts.CONTACT_STATUS,
            ContactsContract.Contacts.HAS_PHONE_NUMBER
        )
    private val mSelectionClause =
        ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + "='Anil'" //creating the sql
    private val mSelectionArguments = arrayOf("Anil")
    private val mOrderby = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textShw = findViewById(R.id.show_contacts)
        val contentResolver = contentResolver //created the object of the mediator
        //thi will be rtuned by target app datab as a mode to iterrate over it' data
        val cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            mColumnProkection,
            null,
            null,
            null
        )
        if (cursor != null && cursor.count > 0) {
            //it will make sure i get alraleast one row having all relevant columns
            val stringBuilderQueryResutlt = StringBuilder("")
            while (cursor.moveToNext()) { //mves to reach non null row
                stringBuilderQueryResutlt.append(
                    """
    ${cursor.getString(0)},${cursor.getString(1)}
    
    """.trimIndent()
                )
            }
            textShw.setText(stringBuilderQueryResutlt.toString())
        } else {
            textShw.setText("No Contact in device")
        }
    }

    companion object {
        private const val TAG = "ContentProviderDemo"
    }
}