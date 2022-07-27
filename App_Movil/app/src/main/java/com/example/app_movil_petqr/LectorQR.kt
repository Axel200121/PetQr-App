package com.example.app_movil_petqr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.zxing.integration.android.IntentIntegrator

class LectorQR : AppCompatActivity() {
    var btnLectorQR:Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lector_qr)
        btnLectorQR=findViewById(R.id.btnLectroQR)
        btnLectorQR?.setOnClickListener {
            iniciarScanner()
        }
    }

    fun iniciarScanner(){
        IntentIntegrator(this).initiateScan()
    }
}