package com.estefayjuanma.ilfornoapp.ui.inicio

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.estefayjuanma.ilfornoapp.MenuTabActivity
import com.estefayjuanma.ilfornoapp.PagerView
import com.estefayjuanma.ilfornoapp.R
import com.estefayjuanma.ilfornoapp.ui.cupones.CuponesFragment
import com.estefayjuanma.ilfornoapp.ui.recoger.RecogerActivity
import com.estefayjuanma.ilfornoapp.ui.restaurantes.RestaurantesFragment
import kotlinx.android.synthetic.main.fragment_inicio.view.*
import kotlinx.android.synthetic.main.fragment_pedidos.view.*
import java.util.*


class InicioFragment: Fragment() {

    lateinit var dotsLayout: LinearLayout
    lateinit var mPager: ViewPager
    lateinit var path: IntArray
    lateinit var dots:Array<ImageView>
    lateinit var adapter: PagerView
    var currentPage: Int=0
    lateinit var timer: Timer
    val DELAY_MS: Long = 5000
    val PERIOD_MS: Long = 5000

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_inicio, container, false)

        //////////////////////////////////////////////////////////////////////////////////
 /*       onBitMapLoaded1()
        path= intArrayOf(R.drawable.imaginicio1,R.drawable.imaginicio2,R.drawable.imaginicio3)
        mpager = root.findViewById(R.id.pager) as ViewPager
        adapter = PagerView(activity!!.applicationContext,path)
        mPager.adapter =adapter
        dotsLayout = root.findViewById(R.id.dotsLaout)
        createDots(0)
        updatePage()
        mPager.addOnAdapterChangeListener(object : ViewPager.OnPageChangeListener{
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


   */     /////////////////////////////////////////////////////////////////////////////////////

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
}