package com.example.app_movil_petqr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import kotlin.math.log

class Login : AppCompatActivity() {
    var txtCorreoLogin: EditText?=null;
    var txtPasswordLogin: EditText?=null;
    var btnIngresarLogin: Button?=null;
    var btnRegistrarFormulario: Button?=null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txtCorreoLogin = findViewById(R.id.txtCorreoLogin)
        txtPasswordLogin = findViewById(R.id.txtPasswordLogin)
        btnIngresarLogin = findViewById(R.id.btnIngresarLogin)
        btnRegistrarFormulario = findViewById(R.id.btnRegistrarFormulario)

        btnRegistrarFormulario!!.setOnClickListener {
            val lanzar = Intent(this,AgregaUsuario::class.java)
            startActivity(lanzar)
        }

        btnIngresarLogin!!.setOnClickListener {
            validarUsuario("http://192.168.8.101/PetQr-App/ApiRest/usuarios/UsuarioLogin.php")
        }
    }
    private fun validarUsuario(URL: String) {
        val stringRequest: StringRequest = object : StringRequest(
            Method.POST,
            URL,
            Response.Listener { response ->
                if(!response.isEmpty()){
                    val intent = Intent(applicationContext, Home::class.java)
                    intent.putExtra("psw", txtPasswordLogin?.text.toString())
                    startActivity(intent)
                } else {
                    Toast.makeText(this@Login, "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener { error ->
                Toast.makeText(this@Login, error.toString(), Toast.LENGTH_SHORT).show()
            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val parametros: MutableMap<String, String> = HashMap()
                parametros.put("correo",txtCorreoLogin?.text.toString())
                parametros.put("psw",txtPasswordLogin?.text.toString())
                return parametros
            }
        }
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }

    /*fun clickVer(view: View){
        var intent = Intent(this,Home::class.java)
        intent.putExtra("psw", txtPasswordLogin?.text)
        startActivity(intent)
    }*/
}