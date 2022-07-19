package com.example.app_movil_petqr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PanelMascota : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panel_mascota)

        val btnAgregarMascota = findViewById<Button>(R.id.btn_agrega_Mascota)
        btnAgregarMascota.setOnClickListener {
            val agregaMascota = Intent(this,AgregarMascota::class.java)
            startActivity(agregaMascota)
        }
    }
}