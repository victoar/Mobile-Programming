package com.example.lab1_2.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab1_2.R
import com.example.lab1_2.model.ProductAdapter
import com.example.lab1_2.repo.Repo

class MainActivity : AppCompatActivity(), ProductAdapter.ItemClickListener {
    lateinit var addButton: Button
    lateinit var recyclerView: RecyclerView
    lateinit var productTextInput: EditText

    var adapter: ProductAdapter? = null
    //    lateinit var productList: ArrayList<Product>
//    lateinit var products : Repo

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView1)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            layoutManager.orientation
        )
        recyclerView.addItemDecoration(dividerItemDecoration)
        adapter = ProductAdapter(this, Repo.productList)
        recyclerView.adapter = adapter
        adapter!!.setClickListener(this@MainActivity)

        addButton = findViewById(R.id.addButton)
        addButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onItemClick(view: View?, position: Int) {
        val intent = Intent(this@MainActivity, ModifyActivity::class.java).apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra("id", adapter!!.getItem(position).toString())
        }
        startActivity(intent)
    }
}