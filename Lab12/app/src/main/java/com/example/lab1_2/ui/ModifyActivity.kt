package com.example.lab1_2.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.lab1_2.R
import com.example.lab1_2.model.Product
import com.example.lab1_2.repo.Repo

class ModifyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify)

        val bundle :Bundle ?=intent.extras
        val productId = bundle!!.getString("id")
        if (productId != null) {
            Log.d("Tag", productId.toString())
        }

        val deleteButton = findViewById<Button>(R.id.deleteProductButton)
        deleteButton.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("Delete Account")
            builder.setMessage("Are you sure you want to delete this product?")
            // add a button

            builder.setPositiveButton("Yes") { _: DialogInterface?, _: Int ->
                if (productId != null) {
                    Repo.delete(productId.toInt())
                    val intent = Intent(this@ModifyActivity, MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Product deleted successfully!", Toast.LENGTH_SHORT).show()
                }
            }
            builder.setNegativeButton("No", null)
            // create and show the alert dialog
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        val saveButton: Button = this.findViewById<Button>(R.id.saveEditProductButton)
        saveButton.setOnClickListener {
            val productQuantityText = findViewById<TextView>(R.id.productQuantityInput)?.text.toString()
            val productDesiredQuantityText = findViewById<TextView>(R.id.productDesiredQuantityInput)?.text.toString()
            val productExpirationDateText = findViewById<TextView>(R.id.productExpirationDateInput)?.text.toString()
            val productPriceText = findViewById<TextView>(R.id.productPriceInput)?.text.toString()
            if(productId != null && productQuantityText != "" && productDesiredQuantityText != ""
                && productExpirationDateText != "" && productPriceText != "") {
                Repo.update(productId.toInt(), productQuantityText,
                    productDesiredQuantityText, productExpirationDateText, productPriceText)

                val intent = Intent(this@ModifyActivity, MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(
                    this,
                    "Updated successfully",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Data was not completed correctly",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}