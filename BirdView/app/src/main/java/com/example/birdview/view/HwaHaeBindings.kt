package com.example.birdview.view

import android.os.Build
import android.text.Html
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.birdview.model.dto.HwaHaeListItem
import com.example.birdview.view.index.HwaHaeListAdapter
import java.util.*
import kotlin.collections.ArrayList

object HwaHaeBindings {

    @JvmStatic
    @BindingAdapter("bind_adapter")
    fun setBindAdapter(view: RecyclerView, adapter: HwaHaeListAdapter?) {
        adapter?.let {
            view.adapter = it
        }
    }

    @JvmStatic
    @BindingAdapter("bind_items")
    fun setBindItems(view : RecyclerView, items : ArrayList<HwaHaeListItem>?) {
        items?.let {
            val adapter = view.adapter as HwaHaeListAdapter
            adapter.addItems(items)
        }
    }

    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(imageView: ImageView, thumbnail_image: String) {
        Glide.with(imageView.context).load(thumbnail_image).into(imageView)
    }

    @JvmStatic
    @BindingAdapter("transPrice")
    fun transPrice(textView: TextView, price: String) {

        var trans = ""
        var count = 0
        val stk = Stack<Char>()
        for (i in price.length - 1 downTo 0) {
            count += 1
            stk.push(price[i])
            if (count == 3 && i != 0) {
                count = 0
                stk.push(',')
            }
        }

        while (!stk.isEmpty()) {
            trans += stk.pop()
        }

        trans += "원"

        textView.text = trans
    }

    @JvmStatic
    @BindingAdapter("setDescription")
    fun setDescription(textView: TextView, description: String) {
        val trans = description.split("\\n")
        var result = ""
        for (i in 0..trans.size - 1) {
            result += (trans[i] + '\n')
        }
        textView.text = result

    }

}