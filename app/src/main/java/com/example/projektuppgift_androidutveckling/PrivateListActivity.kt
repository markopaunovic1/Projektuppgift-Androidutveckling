package com.example.projektuppgift_androidutveckling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PrivateListActivity : AppCompatActivity() {
    lateinit var adapter: RecyclerAdapterRestaurante
    lateinit var recycler : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.private_activity_list)
        recycler = findViewById(R.id.RestauranteRecyclerView)

        val restaurants = arrayListOf(Restaurante("SuperBurgerKing","ABCDV"),Restaurante("SuperSausageKing","ABCDV"))


        adapter = RecyclerAdapterRestaurante(this,restaurants)
        recycler.adapter = adapter;
        recycler.layoutManager = LinearLayoutManager(this)


    }
}