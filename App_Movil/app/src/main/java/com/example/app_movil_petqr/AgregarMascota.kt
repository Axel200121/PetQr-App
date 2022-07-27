package com.example.app_movil_petqr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class AgregarMascota : AppCompatActivity(){
    var txtNombreMascota:EditText?=null
    var txtTipoMascota:EditText?=null
    var txtDescripcionMascota:EditText?=null
    var tvIdUsuarioMascota:TextView?=null
    var idUsuario:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_mascota)

        txtNombreMascota = findViewById(R.id.txtNombreMascota)
        txtTipoMascota = findViewById(R.id.txtTipoMascota)
        txtDescripcionMascota= findViewById(R.id.txtDescripcionMascota)
        tvIdUsuarioMascota=findViewById(R.id.tvIdUsuarioMascota)

        idUsuario=intent.getStringExtra("idUsuario").toString()
        tvIdUsuarioMascota?.setText(idUsuario)


    }
    fun clickBtnInsertarMascota(view: View){
        val url= "http://192.168.8.103/PetQr-App/ApiRest/mascotas/MascotaInsertar.php"
        val queue=Volley.newRequestQueue(this)
        var resultadoPostMascota = object : StringRequest(Method.POST,url,
            Response.Listener { response ->
                Toast.makeText(this,"Mascota registrada exitosamente",Toast.LENGTH_LONG).show()
            },Response.ErrorListener { error ->
                Toast.makeText(this,"Error al registra tu mascota",Toast.LENGTH_LONG).show()
            }){
            override fun getParams(): MutableMap<String, String>? {
                val parametros=HashMap<String,String>()
                parametros.put("nombre",txtNombreMascota?.text.toString())
                parametros.put("tipoMascota",txtTipoMascota?.text.toString())
                parametros.put("descripcion",txtDescripcionMascota?.text.toString())
                parametros.put("idUsuario",tvIdUsuarioMascota?.text.toString())
                return parametros
            }
        }
        queue.add(resultadoPostMascota)
    }
}