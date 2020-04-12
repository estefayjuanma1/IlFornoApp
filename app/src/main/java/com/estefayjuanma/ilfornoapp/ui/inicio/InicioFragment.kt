package com.estefayjuanma.ilfornoapp.ui.inicio

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.estefayjuanma.ilfornoapp.MenuTabActivity
import com.estefayjuanma.ilfornoapp.PagerView
import com.estefayjuanma.ilfornoapp.R
import com.estefayjuanma.ilfornoapp.ui.cupones.CuponesFragment
import com.estefayjuanma.ilfornoapp.ui.restaurantes.RestaurantesFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_inicio.view.*
import java.util.*


class InicioFragment: Fragment() {

    lateinit var dotsLayout: LinearLayout
    lateinit var mPager: ViewPager
    lateinit var path: IntArray
    lateinit var dots:Array<ImageView>
    lateinit var adapter: PagerView
    var currentPage: Int=0
    lateinit var timer: Timer
    val DELAY_MS: Long = 2000
    val PERIOD_MS: Long = 2000

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_inicio, container, false)

        //////////////////////////////////////////////////////////////////////////////////
        Picasso.get().load(R.drawable.hola1).resize(160,700)
        onBitMapLoaded1()
        path= intArrayOf(R.drawable.sliderrr,R.drawable.fotoinicio2,R.drawable.hola1)
        mPager = root.findViewById(R.id.pager) as ViewPager
        adapter = PagerView(activity!!.applicationContext,path)
        mPager.adapter =adapter
        dotsLayout = root.findViewById(R.id.dotsLayout) as LinearLayout
        createDots(0)
        updatePage()
        mPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

                }

            override fun onPageSelected(position: Int) {
                currentPage = position
                createDots(position)
            }

        })


       /////////////////////////////////////////////////////////////////////////////////////

        root.bt_restaurantes.setOnClickListener() {
            val transaction = fragmentManager?.beginTransaction()?.addToBackStack(null)
                transaction?.replace(R.id.nav_host_fragment, RestaurantesFragment())

            transaction?.commit()
            //nav_host_fragment es el id del del frame layout en la actividad que invoca los fragmentos
        }

        root.bt_menu.setOnClickListener {
            var intent = Intent (activity!!.applicationContext, MenuTabActivity::class.java)
            startActivity(intent)
        }

        root.bt_cupones.setOnClickListener(){
            val transaction = fragmentManager?.beginTransaction()?.addToBackStack(null)
            transaction?.replace(R.id.nav_host_fragment, CuponesFragment())

            transaction?.commit()
        }
        return root
    }

    private fun onBitMapLoaded1() {

    }

    fun updatePage() {

        var handler = Handler()
        val Update: Runnable = Runnable{
            if(currentPage == path.size)
            {
                currentPage = 0
            }
            mPager.setCurrentItem(currentPage++,true)
        }
        timer = Timer()
        timer.schedule(object : TimerTask(){
            override fun run() {
                handler.post(Update)
            }
        },DELAY_MS,PERIOD_MS)
    }

    fun createDots(position: Int) {

        if (dotsLayout!=null){
            dotsLayout.removeAllViews()
        }
        dots = Array(path.size,{i -> ImageView(activity!!.applicationContext) })

        for ( i in 0..path.size -1){
            dots[i] = ImageView(activity!!.applicationContext)
            if(i == position){
                dots[i].setImageDrawable(ContextCompat.getDrawable(activity!!.applicationContext,R.drawable.active_dots))
            }
            else
            {
                dots[i].setImageDrawable(ContextCompat.getDrawable(activity!!.applicationContext,R.drawable.inactive_dots))
            }
            var params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            params.setMargins(4,0,4,0)
            dotsLayout.addView(dots[i],params)
        }
    }
}