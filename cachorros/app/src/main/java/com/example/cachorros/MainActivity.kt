package com.example.cachorros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun cadastrarCachorro(view: View) {
        val cadastroCachorros = Intent(this, CadastroCachorros::class.java)
        startActivity(cadastroCachorros)
    }

    fun listarCachorro(view: View) {

    }
}