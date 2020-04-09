package com.estefayjuanma.ilfornoapp.ui.pedidos
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.estefayjuanma.ilfornoapp.R
import com.estefayjuanma.ilfornoapp.ui.recoger.RecogerFragment
import kotlinx.android.synthetic.main.fragment_pedidos.view.*


class PedidosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_pedidos, container, false)
        root.bt_recoger_tienda.setOnClickListener {
            var fragment: Fragment? = null

//            val transaction2: FragmentTransaction = childFragmentManager.beginTransaction()

            val transaction = fragmentManager?.beginTransaction()?.addToBackStack(null)

            transaction?.replace(R.id.nav_host_fragment, RecogerFragment())
            transaction?.commit()

            ////nav_host_fragment es el id del del frame layout en la actividad que invoca los fragmentos
        }

        return root
    }
}