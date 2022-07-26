package com.example.app_movil_petqr

import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class MostrarMascotas : AppCompatActivity() {
    var tableMascotas:TableLayout?=null
    var idUsuario:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_mascotas)
        tableMascotas=findViewById(R.id.tableMascotas)
        tableMascotas?.removeAllViews()
        idUsuario=intent.getStringExtra("idUsuario").toString()
        var queue=Volley.newRequestQueue(this)
        var url ="http://192.168.8.101/PetQr-App/ApiRest/mascotas/MascotaConsultarPrueba.php?idUsuario=$idUsuario"
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
                        colNombreMascota.text=jsonObject.getString("nombre")
                        coltipoMascota.text=jsonObject.getString("tipoMascota")
                        btnEditarMascota.id=jsonObject.getInt("idMascota")
                        btnBorrarMascota.id=jsonObject.getInt("idMascota")
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
        Toast.makeText(this,view.id.toString(),Toast.LENGTH_LONG).show()
    }
    fun tableBorrar(view: View){
        Toast.makeText(this,view.id.toString(),Toast.LENGTH_LONG).show()
    }
}