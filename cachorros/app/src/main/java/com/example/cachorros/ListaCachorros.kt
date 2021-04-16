package com.example.cachorros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaCachorros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_cachorros)

        val layoutList: LinearLayout = findViewById(R.id.layout_list)
        val etX: TextView = findViewById(R.id.tv_x)
        val etY: TextView = findViewById(R.id.tv_y)
        val etZ: TextView = findViewById(R.id.tv_z)
        val x = etX.text.toString()
        val y = etY.text.toString()
        val z = etZ.text.toString()


        val apiCachorros = ConexãoApiCachorros.criar()


        apiCachorros.get().enqueue(object : Callback<List<Cachorros>> {
            override fun onResponse(
                call: Call<List<Cachorros>>,
                response: Response<List<Cachorros>>
            ) {
                response.body()?.forEach {
                    val tvCachorros = TextView(baseContext)
                    tvCachorros.text = "Raca ${it.raca} - Preço Médio ${it.precoMedio} - Indicado para criança ${it.indicadoCriancas}"
                    etX.text = "Cachorros indicados para crianças: ${x}"

                    layoutList.addView(tvCachorros)
                }
            }

            override fun onFailure(call: Call<List<Cachorros>>, t: Throwable) {
                Toast.makeText(baseContext, "Erro na chamada: ${t.message!!}", Toast.LENGTH_SHORT)
                    .show()
            }

        })

    }

}