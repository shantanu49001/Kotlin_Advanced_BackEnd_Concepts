package com.devst.a3service

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//it can me used to monitor many br
class MyBroadcastReciever:BroadcastReceiver() {
    private var pendingIntent:PendingResult?=null  //nullable -->event might not happen

    /*  override fun onReceive(context: Context?, intent: Intent?) { //inbuilr
          //inside main thread pe
          //onreciev -->when observed event happends
          //send the intent to the compnets minitoring t

          //chekcing type of br
          if (intent?.action==Intent.ACTION_APPLICATION_LOCALE_CHANGED){

              //runs on min ui-->
              Toast.makeText(context,Intent.ACTION_APPLICATION_LOCALE_CHANGED,Toast.LENGTH_LONG).show()
              //once competed it finshes the br class-->if we are doind some task inside br in bg-->ye to br send krke band ho jayega
              //we have to somehow rok ke rakhna hai isko


          }

      }
      */
    override fun onReceive(context: Context, intent: Intent?) {

        //running on main threa d

        if (intent?.action==Intent.ACTION_LOCALE_CHANGED){
            //heavy work requered to do
            //this co r runs throguhout
            GlobalScope.launch {
                showtoast(context)//bg work---->updation
            }
        }
        pendingIntent=goAsync()  //makes the br not to end the onreive after trigerring so that heavy work can be completed
    }

    //called in cootuine
    private suspend fun showtoast(context: Context) {
       //functionwas inside non main
        withContext(Dispatchers.Main){
            Toast.makeText(context,Intent.ACTION_APPLICATION_LOCALE_CHANGED,Toast.LENGTH_LONG).show()

        }

        pendingIntent?.finish()
    }


}