package com.example.app_movil_petqr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.util.concurrent.Executor

class DatosPersonalesUsuario : AppCompatActivity() {
    var txtNombreEditar: EditText?=null;
    var txtApellidoPaternoEditar: EditText?=null;
    var txtApellidoMaternoEditar: EditText?=null;
    var txtTelefonoEditar: EditText?=null;
    var txtDireccionEditar: EditText?=null;
    var txtCorreoEditar: EditText?=null;
    var txtPasswordEditar: EditText?=null;
    var tvIdUsuario:TextView?=null
    var idUsuario:String?=null

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_personales_usuario)
        txtNombreEditar = findViewById<EditText?>(R.id.txtNombreEditar)
        txtApellidoPaternoEditar= findViewById(R.id.txtApellidoPaternoEditar)
        txtApellidoMaternoEditar = findViewById(R.id.txtApellidoMaternoEditar)
        txtTelefonoEditar = findViewById(R.id.txtTelefonoEditar)
        txtDireccionEditar = findViewById(R.id.txtDireccionEditar)
        txtCorreoEditar = findViewById(R.id.txtCorreoEditar)
        txtPasswordEditar = findViewById(R.id.txtPasswordEditar)
        tvIdUsuario=findViewById(R.id.tvIdUsuario)
        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(applicationContext, "Authentication succeeded!", Toast.LENGTH_SHORT).show()
                }
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(applicationContext, "Authentication error: $errString", Toast.LENGTH_SHORT).show()
                }
                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(applicationContext, "Authentication failed", Toast.LENGTH_SHORT).show()
                }
            })
        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Se modificara tu información")
            .setSubtitle("Ingrese sus datos biometricos")
            .setNegativeButtonText("cancelar")
            .build()


        idUsuario=intent.getStringExtra("idUsuario").toString()
        tvIdUsuario?.setText(idUsuario)
        val queue=Volley.newRequestQueue(this)
        val url="http://192.168.0.11/PetQr-App/ApiRest/usuarios/UsuarioConsultarID.php?idUsuario=$idUsuario"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,url,null,
            Response.Listener { response ->
                txtNombreEditar?.setText(response.getString("nombre"))
                txtApellidoPaternoEditar?.setText(response.getString("apellidoPaterno"))
                txtApellidoMaternoEditar?.setText(response.getString("apellidoMaterno"))
                txtTelefonoEditar?.setText(response.getString("telefono"))
                txtDireccionEditar?.setText(response.getString("direccion"))
                txtCorreoEditar?.setText(response.getString("correo"))
                txtPasswordEditar?.setText(response.getString("psw"))
            },Response.ErrorListener { error ->
                Toast.makeText(this,error.toString(),Toast.LENGTH_LONG).show()
            }
        )
        queue.add(jsonObjectRequest)
    }
    fun editarUsuario(view: View){
        val url="http://192.168.0.11/PetQr-App/ApiRest/usuarios/UsuarioEditar.php"
        val queue=Volley.newRequestQueue(this)
        biometricPrompt.authenticate(promptInfo)
        val resultadoPost = object : StringRequest(Request.Method.POST,url,
        Response.Listener { response ->
            Toast.makeText(this,"Datos personales actualizados",Toast.LENGTH_LONG).show()

        },Response.ErrorListener { error ->
                Toast.makeText(this,"Error al modificar datos personales $error",Toast.LENGTH_LONG).show()
            }
        ){
            override fun getParams(): MutableMap<String, String>? {
                val parametros = HashMap<String,String>()
                parametros.put("idUsuario",idUsuario!!)
                parametros.put("nombre",txtNombreEditar?.text.toString())
                parametros.put("apellidoPaterno",txtApellidoPaternoEditar?.text.toString())
                parametros.put("apellidoMaterno",txtApellidoMaternoEditar?.text.toString())
                parametros.put("telefono",txtTelefonoEditar?.text.toString())
                parametros.put("direccion",txtDireccionEditar?.text.toString())
                parametros.put("correo",txtCorreoEditar?.text.toString())
                parametros.put("psw",txtPasswordEditar?.text.toString())
                return parametros
            }
        }
        queue.add(resultadoPost)
    }

    fun activarCajas(view: View){
        txtNombreEditar?.isEnabled=true
        txtApellidoPaternoEditar?.isEnabled=true
        txtApellidoMaternoEditar?.isEnabled=true
        txtTelefonoEditar?.isEnabled=true
        txtDireccionEditar?.isEnabled=true
        txtCorreoEditar?.isEnabled=true
        txtPasswordEditar?.isEnabled=true


    }
}