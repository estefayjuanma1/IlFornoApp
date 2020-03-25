package com.estefayjuanma.ilfornoapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_olvidecontra.*
import java.util.regex.Matcher
import java.util.regex.Pattern


class OlvidecontraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_olvidecontra)

        bt_recuperar.setOnClickListener() {
            val auth = FirebaseAuth.getInstance()
            var correovalido = true
            if(et_recuperar.text.toString().isEmpty()){
                Toast.makeText(this, "No ha ingresado correo", Toast.LENGTH_SHORT).show()
            }else{

            correovalido=isEmailValid(et_recuperar.text.toString())
            if(correovalido==true) {
                auth.setLanguageCode("es")
                auth.sendPasswordResetEmail(et_recuperar.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Log.d("OlvidecontraActivity", "signInWithEmail:success")
                        } else {
                            Log.w("OlvidecontraActivity", "signInWithEmail:failure", task.exception)
                        }
                    }
            }else{
                Toast.makeText(this, "correo invalido", Toast.LENGTH_SHORT).show()
            }
        }
    }
    }

    fun isEmailValid(email: String?): Boolean {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher: Matcher = pattern.matcher(email)
        return matcher.matches()
    }
}
