package com.example.cachorros

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroCachorros : AppCompatActivity() {

    lateinit var swCrianca: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_cachorros)

    }

    fun cadastrar(view: View) {
        val apiCachorros = ConexãoApiCachorros.criar()


        val etRaca: EditText = findViewById(R.id.et_raca)
        val etPrecoMedio: EditText = findViewById(R.id.et_preco)
        swCrianca = findViewById(R.id.sw_criança)
        val raca = etRaca.text.toString()
        val preco = etPrecoMedio.text.toString().toInt()
        val indicadoFalse = swCrianca.isChecked.toString().toBoolean()
        val cachorroCadastrado = Cachorros(raca, preco, indicadoFalse)

        val tvCachorro: TextView = findViewById(R.id.tv_cachorro_cadastrado)
        val imageCachorro: ImageView = findViewById(R.id.imagem_cachorro)

        apiCachorros.post(cachorroCadastrado).enqueue(object : Callback<Cachorros> {
            override fun onResponse(call: Call<Cachorros>, response: Response<Cachorros>) {
                println(response)

                if (response.code() == 201) {
                    val cachorroCriado = response.body()
                    tvCachorro.text = "Cachorro da ${cachorroCriado?.raca} cadastrado com sucesso"
                    imageCachorro.setImageResource(R.drawable.cachorro_feliz)
                } else {
                    tvCachorro.text = "Falha ao criar o cachorro ${response.errorBody()!!}"
                }
            }

            override fun onFailure(call: Call<Cachorros>, t: Throwable) {
                Toast.makeText(baseContext, "Erro na chamada: ${t.message!!}", Toast.LENGTH_SHORT)
                    .show()
            }

        })


    }
}