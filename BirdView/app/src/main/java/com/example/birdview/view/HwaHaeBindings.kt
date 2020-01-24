package com.example.birdview.view

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.util.*

object HwaHaeBindings {

    /*@JvmStatic
    @BindingAdapter("items")
    fun setItems(listView: RecyclerView, items: ArrayList<HwaHaeListItem>) {

        Log.d("checklist","binding adapter item size : " + items.size.toString())

        var adapter: HwaHaeListAdapter

        when (listView.adapter) {
            null -> {
                adapter = HwaHaeListAdapter(arrayListOf())
                listView.adapter= adapter
                adapter.updateItems(items)
            }
            else -> {
                adapter = listView.adapter as HwaHaeListAdapter
                adapter.updateItems(items)
            }
        }

    }*/

    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(imageView: ImageView, thumbnail_image: String) {
        Glide.with(imageView.context).load(thumbnail_image).into(imageView)
    }

    @JvmStatic
    @BindingAdapter("transPrice")
    fun transPrice(textView: TextView, price:String) {

        var trans = ""
        var count = 0
        val stk = Stack<Char>()
        for( i in price.length-1 downTo 0) {
            count+=1
            stk.push(price[i])
            if(count ==3 && i!=0) {
                count = 0
                stk.push(',')
            }
        }

        while(!stk.isEmpty()) {
            trans += stk.pop()
        }

        trans += "원"

        textView.text = trans
    }

    @JvmStatic
    @BindingAdapter("setDescription")
    fun setDescription(textView: TextView, description: String) {
        val trans = description.split("\\\\n")
        var result =""
        for(i in 0..trans.size-1) {
            result+= (trans[i] +"\n")
        }
        Log.d("checkstring" , result)
        textView.text = result
    }

}