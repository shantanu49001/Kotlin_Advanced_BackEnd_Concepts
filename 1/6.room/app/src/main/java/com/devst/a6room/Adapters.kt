package com.devst.a6room

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageFromUrl")
fun ImageView.imageFromUrl(url:String){
    //we are just adding a extnson function to the image View class
    Glide.with(this.context).load(url).into(this)

}