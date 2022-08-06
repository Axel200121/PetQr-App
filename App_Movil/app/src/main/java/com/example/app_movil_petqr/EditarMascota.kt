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
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.util.concurrent.Executor

class EditarMascota : AppCompatActivity() {
    var txtNombreMascotaEditar: EditText?=null
    var txtTipoMascotaEditar: EditText?=null
    var txtDescripcionMascotaEditar: EditText?=null
    var tvIdMascotaEditar: TextView?=null
    var idMascota:String?=null
    var idUsuario:String?=null

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_mascota)
        txtNombreMascotaEditar = findViewById(R.id.txtNombreMascotaEditar)
        txtTipoMascotaEditar = findViewById(R.id.txtTipoMascotaEditar)
        txtDescripcionMascotaEditar= findViewById(R.id.txtDescripcionMascotaEditar)
        tvIdMascotaEditar=findViewById(R.id.tvIdMascotaEditar)

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
            .setTitle("Se modificara la información de tu mascota")
            .setSubtitle("Ingrese sus datos biometricos")
            .setNegativeButtonText("cancelar")
            .build()

        idMascota=intent.getStringExtra("idMascota").toString()
        tvIdMascotaEditar?.setText(idMascota)

        idUsuario=intent.getStringExtra("idUsuario").toString()

        val queue= Volley.newRequestQueue(this)
        val url="http://192.168.0.14/PetQr-App/ApiRest/mascotas/MascotaConsultar.php?idMascota=$idMascota"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,url,null,
            Response.Listener { response ->
                txtNombreMascotaEditar?.setText(response.getString("nombre"))
                txtTipoMascotaEditar?.setText(response.getString("tipoMascota"))
                txtDescripcionMascotaEditar?.setText(response.getString("descripcion"))
            }, Response.ErrorListener { error ->
                Toast.makeText(this,error.toString(), Toast.LENGTH_LONG).show()
            }
        )
        queue.add(jsonObjectRequest)
    }
    fun editarFinalMascota(view:View){
        val url="http://192.168.0.14/PetQr-App/ApiRest/mascotas/MascotaEditar.php"
        val queue=Volley.newRequestQueue(this)
        biometricPrompt.authenticate(promptInfo)
        val resultadoPost = object : StringRequest(Request.Method.POST,url,
            Response.Listener { response ->
                Toast.makeText(this,"Datos de la mascota actualizados",Toast.LENGTH_LONG).show()

            },Response.ErrorListener { error ->
                Toast.makeText(this,"Error al modificar datos de la mascota $error",Toast.LENGTH_LONG).show()
            }
        ){
            override fun getParams(): MutableMap<String, String>? {
                val parametros = HashMap<String,String>()
                parametros.put("idMascota",idMascota!!)
                parametros.put("nombre",txtNombreMascotaEditar?.text.toString())
                parametros.put("tipoMascota",txtTipoMascotaEditar?.text.toString())
                parametros.put("descripcion",txtDescripcionMascotaEditar?.text.toString())
                parametros.put("idUsuario",idUsuario!!)
                return parametros
            }
        }
        queue.add(resultadoPost)
    }
}