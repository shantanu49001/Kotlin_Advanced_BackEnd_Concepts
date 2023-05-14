package com.devst.a16room

import android.widget.ListAdapter

//fdon't mind the error i have just addd the main code

//diff util is adapter prop -->aur kahin bhi iska koi lena dena nhi
class Adapter : ListAdapter<ProgramingIten, android.widget.Adapter.ViewHolder>(DiffUtil()) {

    //oncr function
    //onBind
    //view h

    //notofu data set changes ka better version
    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<ProgrammingItem>() {
        override fun areItemsTheSame(oldItem: ProgrammingItem, newItem: ProgrammingItem): Boolean {
//progr item is a data class

            //list item me id prop thi
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(
            oldItem: ProgrammingItem,
            newItem: ProgrammingItem
        ): Boolean {
            //list item ke contet saare same hai ya nhi
            return oldItem == newItem
        }

    }

}