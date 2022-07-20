package com.example.app_movil_petqr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class DatosPersonalesUsuario : AppCompatActivity() {
    var txtNombre: EditText?=null;
    var txtApellidoPaterno: EditText?=null;
    var txtApellidoMaterno: EditText?=null;
    var txtTelefono: EditText?=null;
    var txtDireccion: EditText?=null;
    var txtCorreo: EditText?=null;
    var txtPassword: EditText?=null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_personales_usuario)
        txtNombre = findViewById(R.id.txtNombre)
        txtApellidoPaterno= findViewById(R.id.txtApellidoPaterno);
        txtApellidoMaterno = findViewById(R.id.txtApellidoMaterno);
        txtTelefono = findViewById(R.id.txtTelefono)
        txtDireccion = findViewById(R.id.txtDireccion)
        txtCorreo = findViewById(R.id.txtCorreo)
        txtPassword = findViewById(R.id.txtPassword)

        val psw= intent.getStringExtra("psw").toString()
        val queue =Volley.newRequestQueue(this)
        val url ="http://192.168.0.11/PetQr-App/ApiRest/usuarios/UsuarioConsultar.php?psw=$psw"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,url,null,
            Response.Listener { response ->
                txtNombre?.setText(response.getString("nombre"))
                txtApellidoPaterno?.setText(response.getString("apellidoPaterno"))
                txtApellidoMaterno?.setText(response.getString("apellidoMaterno"))
                txtTelefono?.setText(response.getString("telefono"))
                txtDireccion?.setText(response.getString("direccion"))
                txtCorreo?.setText(response.getString("correo"))
                txtPassword?.setText(response.getString("psw"))
            },Response.ErrorListener { error ->
                Toast.makeText(this,error.toString(),Toast.LENGTH_LONG).show()
            }
        )
        queue.add(jsonObjectRequest)
    }
}