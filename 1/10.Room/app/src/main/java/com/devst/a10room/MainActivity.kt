package com.devst.a10room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.devst.a10room.databinding.ActivityMainBinding

//implemting mvvm
//video 15 mornng se
//view-->actvity fragment
//iemmodel-->vm
//model-->till repo[decieded local or api]

//diff util-->drwas diffeence betweeen two lists
//jab hum hr baar room se list fetch kr rhe hai to kuch same hai kuch nhi
//but rv dobar se poori lis kypn banae?
//ntofy db changes me saari list mil rhi hot hai

//we can use it by either on our own
//or by list adapter
//by deafult imsme  adapter me diff util lga hota hai

//next project me
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mainVm: MainVm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //vm is a data owner
        //vm requiresa repo
        //this time v is provided nt by inuilt bt by factory

        //dao acessed by db
        val dao = QuoteDb.getDataBase(applicationContext).getDao()
        val repository = QuoteRepository(dao) //isko dao chaiye
        mainVm = ViewModelProvider(this, MainVmFactory(repository)).get(MainVm::class.java)
       //amin vm has this data --->owner is the vm and observer is called here
       mainVm.getQuotes().observe(this, Observer {
           //aise data acess kya  it return the list

           binding.quotes=it.toString()
       })
        binding.btnAddQuotes.setOnClickListener {

            //thread changes bhi wahi tak hi rakho

            val quote=Quote(0,"This s a test","test")
            mainVm.insertQuote(quote)


        }
    }
}