package com.example.app_movil_petqr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class EditarMascota : AppCompatActivity() {
    var txtNombreMascotaEditar: EditText?=null
    var txtTipoMascotaEditar: EditText?=null
    var txtDescripcionMascotaEditar: EditText?=null
    var tvIdMascotaEditar: TextView?=null
    var idUsuario:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_mascota)
        txtNombreMascotaEditar = findViewById(R.id.txtNombreMascotaEditar)
        txtTipoMascotaEditar = findViewById(R.id.txtTipoMascotaEditar)
        txtDescripcionMascotaEditar= findViewById(R.id.txtDescripcionMascotaEditar)
        tvIdMascotaEditar=findViewById(R.id.tvIdMascotaEditar)

        idUsuario=intent.getStringExtra("idMascota").toString()
        tvIdMascotaEditar?.setText(idUsuario)
    }
}