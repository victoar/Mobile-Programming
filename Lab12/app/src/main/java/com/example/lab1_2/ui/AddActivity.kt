package com.example.lab1_2.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.lab1_2.R
import com.example.lab1_2.model.Product
import com.example.lab1_2.repo.Repo

class AddActivity : AppCompatActivity() {
//    val saveButton: Button = this.findViewById(R.id.saveAddProductButton)
//    val productName: EditText
//    val productQuantity: EditText
//    val productDesiredQuantity: EditText
//    val productExpirationDate: EditText
//    val productPrice: EditText
//
//    init {
//        productName = findViewById(R.id.productNameInput)
//        productQuantity = findViewById(R.id.productQuantityInput)
//        productDesiredQuantity = findViewById(R.id.productDesiredQuantityInput)
//        productExpirationDate = findViewById(R.id.productExpirationDateInput)
//        productPrice = findViewById(R.id.productPriceInput)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val saveButton: Button = this.findViewById<Button>(R.id.saveAddProductButton)
        saveButton.setOnClickListener {
            val productNameText = findViewById<TextView>(R.id.productNameInput)?.text.toString()
            val productQuantityText = findViewById<TextView>(R.id.productQuantityInput)?.text.toString()
            val productDesiredQuantityText = findViewById<TextView>(R.id.productDesiredQuantityInput)?.text.toString()
            val productMeasurementText = findViewById<TextView>(R.id.productMeasurementInput)?.text.toString()
            val productExpirationDateText = findViewById<TextView>(R.id.productExpirationDateInput)?.text.toString()
            val productPriceText = findViewById<TextView>(R.id.productPriceInput)?.text.toString()
            if(productNameText != "" && productQuantityText != "" && productDesiredQuantityText != ""
                && productExpirationDateText != "" && productMeasurementText != "" && productPriceText != "") {
                val product = Product(Repo.getNextAvailableId(), productNameText, productQuantityText.toInt(),
                    productDesiredQuantityText.toInt(), productMeasurementText, productExpirationDateText, productPriceText.toInt())
                Repo.addProduct(product)

                val intent = Intent(this@AddActivity, MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(
                    this,
                    "Added successfully",
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