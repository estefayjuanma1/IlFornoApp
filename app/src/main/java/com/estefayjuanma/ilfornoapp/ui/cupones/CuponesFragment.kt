package com.estefayjuanma.ilfornoapp.ui.cupones
import android.content.Context
import android.os.Bundle
import android.provider.Settings.Global.putInt
import android.provider.Settings.Global.putString
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.estefayjuanma.ilfornoapp.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_cupones.*


class CuponesFragment : Fragment() {

    var cuponglotreinta: String = ""
    var cuponglocincuenta: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_cupones, container, false)

        return root
    }


    override fun onResume() {
        cargarCupon()

        super.onResume()
    }


    private fun cargarCupon() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Cupones")



        myRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    var cupontreinta = p0.child("id1").child("codigo").getValue()
                    var cuponcincuenta = p0.child("id2").child("codigo").getValue()
                   // Log.w("Lista",  cupon)
                    tv_cupon1.text = cupontreinta.toString()
                    tv_cupon2.text = cuponcincuenta.toString()

                    cuponglotreinta = cupontreinta.toString()
                    cuponglocincuenta = cuponcincuenta.toString()



                }
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.w("Lista", "Failed to read values")
            }
        })
    }



}