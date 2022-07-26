package com.example.app_movil_petqr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class PanelMascota : AppCompatActivity() {
    var txtidUsuarioPaMascota:EditText?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panel_mascota)
        txtidUsuarioPaMascota=findViewById(R.id.txtidUsuarioPaMascota)

        val idUsuario=intent.getStringExtra("idUsuario").toString()
        val queue= Volley.newRequestQueue(this)
        val url="http://192.168.8.101/PetQr-App/ApiRest/usuarios/UsuarioConsultarID.php?idUsuario=$idUsuario"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,url,null,
            Response.Listener { response ->
                txtidUsuarioPaMascota?.setText(response.getString("idUsuario"))
            }, Response.ErrorListener { error ->
                Toast.makeText(this,error.toString(), Toast.LENGTH_LONG).show()
            }
        )
        queue.add(jsonObjectRequest)

        val btnAgregarMascota = findViewById<Button>(R.id.btn_agrega_Mascota)
        btnAgregarMascota.setOnClickListener {
            val agregaMascota = Intent(this,AgregarMascota::class.java)
            agregaMascota.putExtra("idUsuario",txtidUsuarioPaMascota?.text.toString())
            startActivity(agregaMascota)
        }

        val btnCrudMascota = findViewById<Button>(R.id.btn_crud_Mascota)
        btnCrudMascota.setOnClickListener {
            val crudMascota = Intent(this,MostrarMascotas::class.java)
            crudMascota.putExtra("idUsuario",txtidUsuarioPaMascota?.text.toString())
            startActivity(crudMascota)
        }
    }
}