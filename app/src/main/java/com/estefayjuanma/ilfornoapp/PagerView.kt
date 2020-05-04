package com.estefayjuanma.ilfornoapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.viewpager.widget.PagerAdapter
import com.squareup.picasso.Picasso

class PagerView : PagerAdapter{
    var con:Context
    var path= arrayOf<String>()
   // var path:IntArray //IntArray
    lateinit var inflater: LayoutInflater


    constructor(con: Context, path: Array<String>): super(){//IntArray): super(){
        this.con = con
        this.path = path
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view==`object` as RelativeLayout
    }

    override fun getCount(): Int {
        return path.size
         //To change body of created functions use File | Settings | File Templates.
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var img: ImageView
        inflater = con.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var rv: View = inflater.inflate(R.layout.swipe_fragment,container,false)
        img = rv.findViewById(R.id.img) as ImageView
        Picasso.get().load(path[position]).into(img) //
        //img.setImageResource(path[position])
        container!!.addView(rv)
        return rv
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container!!.removeView(`object` as RelativeLayout)
    }
}