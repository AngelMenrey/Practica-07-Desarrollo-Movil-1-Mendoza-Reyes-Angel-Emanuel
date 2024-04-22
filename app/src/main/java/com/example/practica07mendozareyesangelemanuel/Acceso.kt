package com.example.practica07mendozareyesangelemanuel

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import layout.Disfraz
import layout.Usuario

class Acceso : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var disfraz: Disfraz
    private lateinit var guardar: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acceso)

        email = findViewById(R.id.editCorreo)
        password = findViewById(R.id.editContrasena)
        guardar = findViewById(R.id.btnIngresar)

        disfraz = Disfraz()
    }

    fun onClick(view: View?){
        when(view?.id){
            R.id.btnIngresar -> {
                Ingresar()
                Limpiar()
            }
            R.id.btnSalir -> Salir()
        }
    }

    fun Ingresar(){
        if(email.text.isNotEmpty() && email.text.isNotBlank() &&
            password.text.isNotEmpty() && password.text.isNotBlank()){
        val usr = Usuario(email.text.toString(), password.text.toString(), true)
            guardar.setOnClickListener{
                guardarPreferencias(usr)
            }
            val intent = Intent(applicationContext, Menu::class.java)
            intent.putExtra("nombre",disfraz.nombre)
            intent.putExtra("domicilio",disfraz.domicilio)
            intent.putExtra("producto",disfraz.producto)
            intent.putExtra("talla",disfraz.talla)
            intent.putExtra("telefono",disfraz.telefono)
            startActivity(intent)
        }else{
            Toast.makeText(this,"Capturar informacion",Toast.LENGTH_LONG).show()
        }
    }

    fun Salir(){
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

    fun Limpiar(){
        email.text.clear()
        password.text.clear()
        email.requestFocus()
    }

    private fun guardarPreferencias(user: Usuario){
        val preferences: SharedPreferences = getSharedPreferences("preferenciasUsuario",
            MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString("email",user.nombre)
        editor.putString("password",user.contrasena)
        editor.putBoolean("guardado",user.guardado)

        editor.apply()
    }

}