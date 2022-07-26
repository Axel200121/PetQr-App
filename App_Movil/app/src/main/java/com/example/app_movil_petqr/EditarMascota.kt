package com.example.app_movil_petqr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class EditarMascota : AppCompatActivity() {
    var txtNombreMascotaEditar: EditText?=null
    var txtTipoMascotaEditar: EditText?=null
    var txtDescripcionMascotaEditar: EditText?=null
    var tvIdMascotaEditar: TextView?=null
    var idMascota:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_mascota)
        txtNombreMascotaEditar = findViewById(R.id.txtNombreMascotaEditar)
        txtTipoMascotaEditar = findViewById(R.id.txtTipoMascotaEditar)
        txtDescripcionMascotaEditar= findViewById(R.id.txtDescripcionMascotaEditar)
        tvIdMascotaEditar=findViewById(R.id.tvIdMascotaEditar)

        idMascota=intent.getStringExtra("idMascota").toString()
        tvIdMascotaEditar?.setText(idMascota)

        val queue= Volley.newRequestQueue(this)
        val url="http://192.168.8.101/PetQr-App/ApiRest/mascotas/MascotaConsultar.php?idMascota=$idMascota"
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
}