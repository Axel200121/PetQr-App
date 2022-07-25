package com.example.app_movil_petqr

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class AgregaUsuario : AppCompatActivity() {
    var txtNombre:EditText?=null;
    var txtApellidoPaterno:EditText?=null;
    var txtApellidoMaterno:EditText?=null;
    var txtTelefono:EditText?=null;
    var txtDireccion:EditText?=null;
    var txtCorreo:EditText?=null;
    var txtPassword:EditText?=null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_usuario)

        txtNombre = findViewById(R.id.txtNombre)
        txtApellidoPaterno= findViewById(R.id.txtApellidoPaterno);
        txtApellidoMaterno = findViewById(R.id.txtApellidoMaterno);
        txtTelefono = findViewById(R.id.txtTelefono)
        txtDireccion = findViewById(R.id.txtDireccion)
        txtCorreo = findViewById(R.id.txtCorreo)
        txtPassword = findViewById(R.id.txtPassword)
    }
    fun clickBtnInsertarUsuario(view : View){
        val url= "http://192.168.8.101/PetQr-App/ApiRest/usuarios/UsuarioInsertar.php"
        val queue=Volley.newRequestQueue(this)
        var resultadoPost = object : StringRequest(Request.Method.POST,url,
            Response.Listener<String> { response ->
                Toast.makeText(this,"Usuario insertado exitosamente",Toast.LENGTH_LONG).show()
            },Response.ErrorListener { error ->
                Toast.makeText(this,"Error $error ",Toast.LENGTH_LONG).show()
            }){
            override fun getParams(): MutableMap<String, String> {
                val parametros=HashMap<String,String>()
                parametros.put("nombre",txtNombre?.text.toString())
                parametros.put("apellidoPaterno",txtApellidoPaterno?.text.toString())
                parametros.put("apellidoMaterno",txtApellidoMaterno?.text.toString())
                parametros.put("telefono",txtTelefono?.text.toString())
                parametros.put("direccion",txtDireccion?.text.toString())
                parametros.put("correo",txtCorreo?.text.toString())
                parametros.put("psw",txtPassword?.text.toString())
                return parametros
            }
        }
        queue.add(resultadoPost)
    }
}