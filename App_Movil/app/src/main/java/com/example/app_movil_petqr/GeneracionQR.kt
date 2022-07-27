package com.example.app_movil_petqr

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import java.lang.Exception

class GeneracionQR : AppCompatActivity() {
    var idMascota:String?=null
    //var txtDescripcionQR:EditText?=null
    var imgQR:ImageView?=null
    var btnGenerarQr:Button?=null
    var tvNombreUsuario:TextView?=null
    var tvTelefono:TextView?=null
    var tvDireccion:TextView?=null
    var tvNombreMascota:TextView?=null
    var tvTipoMascota:TextView?=null
    var tvDescripcion:TextView?=null
    var apellidoPaterno:String?=null
    var apellidoMaterno:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generacion_qr)
        //txtDescripcionQR=findViewById(R.id.txtDescripcionQR)
        imgQR=findViewById(R.id.imgQR)
        btnGenerarQr=findViewById(R.id.btnGenerarQr)
        tvNombreUsuario=findViewById(R.id.tvNombreUsuario)
        tvNombreMascota=findViewById(R.id.tvNombreMascota)
        tvTelefono=findViewById(R.id.tvTelefono)
        tvDireccion=findViewById(R.id.tvDireccion)
        tvTipoMascota=findViewById(R.id.tvTipoMascota)
        tvDescripcion=findViewById(R.id.tvDescripcion)

        idMascota=intent.getStringExtra("idMascota").toString()
        //txtDescripcionQR?.setText(idMascota)

        val queue= Volley.newRequestQueue(this)
        val url="http://192.168.8.101/PetQr-App/ApiRest/mascotas/MascotaConsultarQR.php?idMascota=$idMascota"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,url,null,
            Response.Listener { response ->
                tvNombreUsuario?.setText(response.getString("nombre"))
                tvTelefono?.setText(response.getString("telefono"))
                tvDireccion?.setText(response.getString("direccion"))
                tvTipoMascota?.setText(response.getString("tipoMascota"))
                tvDescripcion?.setText(response.getString("descripcion"))
            }, Response.ErrorListener { error ->
                Toast.makeText(this,error.toString(), Toast.LENGTH_LONG).show()
            }
        )
        queue.add(jsonObjectRequest)

        btnGenerarQr?.setOnClickListener(View.OnClickListener {
            try {
                var barcodeEncoder:BarcodeEncoder= BarcodeEncoder()
                var bitmap:Bitmap=barcodeEncoder.encodeBitmap(
                    txtDescripcionQR?.text.toString(),
                    BarcodeFormat.QR_CODE,
                    750,
                    750
                )
                imgQR?.setImageBitmap(bitmap)
            }catch (e: Exception){
                e.printStackTrace()
            }
        })



    }
}