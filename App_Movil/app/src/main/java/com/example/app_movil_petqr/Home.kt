package com.example.app_movil_petqr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnPersonal = findViewById<Button>(R.id.btn_panel_datosPersonales)
        btnPersonal.setOnClickListener {
            val lanzar = Intent(this,DatosPersonalesUsuario::class.java)
            startActivity(lanzar)
        }

        val btnMascotas = findViewById<Button>(R.id.btn_panel_mascotas)
        btnMascotas.setOnClickListener {
            val btnMascota = Intent(this,PanelMascota::class.java)
            startActivity(btnMascota)
        }
        val btnQr = findViewById<Button>(R.id.btn_panel_generaQr)
        btnQr.setOnClickListener {
            val qr = Intent(this,GeneracionQR::class.java)
            startActivity(qr)
        }
    }
}