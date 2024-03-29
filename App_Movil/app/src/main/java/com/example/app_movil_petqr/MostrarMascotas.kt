package com.example.app_movil_petqr

import android.app.DownloadManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import java.util.concurrent.Executor

class MostrarMascotas : AppCompatActivity() {
    var tableMascotas:TableLayout?=null
    var idUsuario:String?=null
    var idGlobal:String?=null
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_mascotas)
        tableMascotas=findViewById(R.id.tableMascotas)
        idUsuario=intent.getStringExtra("idUsuario").toString()
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
            .setTitle("Se eliminara tu mascota")
            .setSubtitle("Ingrese sus datos biometricos")
            .setNegativeButtonText("cancelar")
            .build()
        tablaMascotas()

    }
    fun tablaMascotas(){
        tablaMascotas()
        tableMascotas?.removeAllViews()
        var queue=Volley.newRequestQueue(this)
        var url ="https://selenographic-masse.000webhostapp.com/ApiRest/mascotas/MascotaConsultarPrueba.php?idUsuario=$idUsuario"
        var jsonObjectRequest=JsonObjectRequest(Request.Method.GET,url,null,
            Response.Listener { response ->
                try {
                    var jsonArray=response.getJSONArray("data")
                    for(i in 0 until jsonArray.length() ){
                        var jsonObject=jsonArray.getJSONObject(i)
                        val  registro=LayoutInflater.from(this).inflate(R.layout.table_row_mascotas,null,false)
                        val colNombreMascota=registro.findViewById<View>(R.id.colNombreMascota) as TextView
                        val coltipoMascota=registro.findViewById<View>(R.id.coltipoMascota) as TextView
                        val btnEditarMascota=registro.findViewById<View>(R.id.btnEditarMascota)
                        val btnBorrarMascota=registro.findViewById<View>(R.id.btnBorrarMascota)
                        val btnScanner=registro.findViewById<View>(R.id.btnScanner)
                        colNombreMascota.text=jsonObject.getString("nombre")
                        coltipoMascota.text=jsonObject.getString("tipoMascota")
                        btnEditarMascota.id=jsonObject.getInt("idMascota")
                        btnBorrarMascota.id=jsonObject.getInt("idMascota")
                        btnScanner.id=jsonObject.getInt("idMascota")
                        tableMascotas?.addView(registro)
                    }
                }catch (e: JSONException){
                    e.printStackTrace()
                }
            },Response.ErrorListener { error ->
                Toast.makeText(this,error.toString(),Toast.LENGTH_LONG).show()
            }
        )
        queue.add(jsonObjectRequest)

    }

    fun tableEditar(view: View){
        idGlobal=view.id.toString()
        val intent = Intent(applicationContext, EditarMascota::class.java)
        intent.putExtra("idMascota",idGlobal)
        intent.putExtra("idUsuario",idUsuario)
        startActivity(intent)

    }
    fun tableScanner(view: View){
        idGlobal=view.id.toString()
        val intent=Intent(applicationContext,GeneracionQR::class.java)
        intent.putExtra("idMascota",idGlobal)
        startActivity(intent)
    }

    fun tableBorrar(view: View){
        var url ="https://selenographic-masse.000webhostapp.com/ApiRest/mascotas/MascotaBorrar.php"
        val queue=Volley.newRequestQueue(this)
        var resultadoEliminar =object:StringRequest(Request.Method.POST,url,
        Response.Listener { response ->
            Toast.makeText(this,"Mascota Eliminada",Toast.LENGTH_LONG).show()
            tablaMascotas()
        },Response.ErrorListener {
                Toast.makeText(this,"Error al eliminar tu mascota",Toast.LENGTH_LONG).show()

            }
        ){
            override fun getParams(): MutableMap<String, String>? {
                val parametros =HashMap<String, String>()
                parametros.put("idMascota",view.id.toString())
                return parametros
            }
        }
        queue.add(resultadoEliminar)

        //Toast.makeText(this,view.id.toString(),Toast.LENGTH_LONG).show()
    }
}