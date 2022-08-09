package com.example.app_movil_petqr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.postDelayed
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
    var duracion:Long=3000;
    var btnAlertConfirmarLogin: Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txtCorreoLogin = findViewById(R.id.txtCorreoLogin)
        txtPasswordLogin = findViewById(R.id.txtPasswordLogin)
        btnIngresarLogin = findViewById(R.id.btnIngresarLogin)
        btnRegistrarFormulario = findViewById(R.id.btnRegistrarFormulario)

    }
    fun validarUsuario(view: View) {
        if (txtCorreoLogin!!.text.isNotEmpty() && txtPasswordLogin!!.text.isNotEmpty()){
            val URL ="https://selenographic-masse.000webhostapp.com/ApiRest/usuarios/UsuarioLogin.php"
            val stringRequest: StringRequest = object : StringRequest(
                Method.POST,
                URL,
                Response.Listener { response ->
                    if(!response.isEmpty()){
                        val intent = Intent(applicationContext, Home::class.java)
                        intent.putExtra("correo", txtCorreoLogin?.text.toString())
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

        }else{
            Toast.makeText(this@Login, "Llene todos los campos por favor", Toast.LENGTH_SHORT).show()
        }
    }

    fun alertLogin(){
        val viewAlertErrror = View.inflate(this,R.layout.alert_login_error,null)
        val builder = AlertDialog.Builder(this)
        builder.setView(viewAlertErrror)
        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

    }



    /*fun clickVer(view: View){
        var intent = Intent(this,Home::class.java)
        intent.putExtra("psw", txtPasswordLogin?.text)
        startActivity(intent)
    }*/
}