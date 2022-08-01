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

class Home : AppCompatActivity() {
    var txtNombre1:EditText?=null
    var txtApellidoPaterno:EditText?=null
    var txtApellidoMaterno:EditText?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        txtNombre1=findViewById(R.id.txtNombre1)
        //txtApellidoPaterno=findViewById(R.id.txtApellidoPaterno1)
        //txtApellidoMaterno=findViewById(R.id.txtApellidoMaterno1)


        val psw=intent.getStringExtra("psw").toString()
        val queue=Volley.newRequestQueue(this)
        val url="http://192.168.0.11/PetQr-App/ApiRest/usuarios/UsuarioConsultar.php?psw=$psw"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,url,null,
            Response.Listener { response ->
                txtNombre1?.setText(response.getString("idUsuario"))
                //txtApellidoPaterno?.setText(response.getString("apellidoPaterno"))
                //txtApellidoMaterno?.setText(response.getString("apellidoMaterno"))
            },Response.ErrorListener { error ->
                Toast.makeText(this,error.toString(),Toast.LENGTH_LONG).show()
            }
        )
        queue.add(jsonObjectRequest)

        val btnPersonal = findViewById<Button>(R.id.btn_panel_datosPersonales)
        btnPersonal.setOnClickListener {
            val lanzar = Intent(this,DatosPersonalesUsuario::class.java)
            lanzar.putExtra("idUsuario",txtNombre1?.text.toString())
            startActivity(lanzar)
        }

        val btnMascotas = findViewById<Button>(R.id.btn_panel_mascotas)
        btnMascotas.setOnClickListener {
            val btnMascota = Intent(this,PanelMascota::class.java)
            btnMascota.putExtra("idUsuario",txtNombre1?.text.toString())
            startActivity(btnMascota)
        }
        val btnQr = findViewById<Button>(R.id.btn_panel_generaQr)
        btnQr.setOnClickListener {
            val qr = Intent(this,LectorQR::class.java)
            startActivity(qr)
        }
    }
}