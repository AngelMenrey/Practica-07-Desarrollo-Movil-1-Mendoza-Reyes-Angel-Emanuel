package com.example.practica07mendozareyesangelemanuel

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import layout.Disfraz

class Menu : AppCompatActivity() {

    private lateinit var disfraz: Disfraz

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        disfraz = Disfraz()

        val toolbar: Toolbar = findViewById(R.id.barra)
        setSupportActionBar(toolbar)
        var infoRecibida = intent.extras
        if(infoRecibida != null){
            disfraz.nombre = infoRecibida?.getString("nombre")!!
            disfraz.domicilio = infoRecibida?.getString("domicilio")!!
            disfraz.producto = infoRecibida?.getString("producto")!!
            disfraz.talla = infoRecibida?.getString("talla")!!
            disfraz.telefono = infoRecibida?.getInt("telefono")!!
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_overflow, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent: Intent?
        when(item.itemId){
            R.id.itmPedido ->{
                intent = Intent(applicationContext, Pedido::class.java)
                startActivity(intent)
            }
            R.id.itmProductos -> {
                intent = Intent(applicationContext, Productos::class.java)
                startActivity(intent)
            }
            R.id.itmCompras -> {
                intent = Intent(applicationContext, MisCompras::class.java)
                intent.putExtra("nombre",disfraz.nombre)
                intent.putExtra("domicilio",disfraz.domicilio)
                intent.putExtra("producto",disfraz.producto)
                intent.putExtra("talla",disfraz.talla)
                intent.putExtra("telefono",disfraz.telefono)
                startActivity(intent)
            }
            R.id.itmNosotros -> {
                intent = Intent(applicationContext, Nosotros::class.java)
                startActivity(intent)
            }
            R.id.itmCerrar -> { cerrarSecccion() }
        }
        return super.onOptionsItemSelected(item)
    }

    fun cerrarSecccion(){
        val preferences: SharedPreferences = getSharedPreferences("preferenciasUsuario",
            MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.clear()
        editor.apply()
        val intent = Intent(applicationContext, Acceso::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP; Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}