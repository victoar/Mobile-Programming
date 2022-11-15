package com.example.lab1_2.model

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab1_2.R
import com.example.lab1_2.ui.MainActivity

class ProductAdapter(context: Context?, private val products: ArrayList<Product>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private var myClickListener: ItemClickListener? = null

    inner class ViewHolder internal constructor(ItemsView : View) : RecyclerView.ViewHolder(ItemsView), View.OnClickListener {
        val txtProductName : TextView
        val txtProductQuantity : TextView
        val txtExpirationDate : TextView



        init {
            txtProductName = itemView.findViewById(R.id.productNameLabel)
            txtProductQuantity = itemView.findViewById(R.id.quantityLabel)
            txtExpirationDate = itemView.findViewById(R.id.expirationDateLabel)
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            if(myClickListener != null) {
                myClickListener!!.onItemClick(p0, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_layout_item, parent, false)

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products.get(position)
        holder.txtProductName.text = product.productName
        holder.txtProductQuantity.text = product.quantity.toString() + product.measurement
        if(product.quantity < product.desiredQuantity)
            holder.txtProductQuantity.setTextColor(Color.parseColor("#800000"))
        holder.txtExpirationDate.text = product.date
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun getItem(id: Int): Int {
        return products.get(id).productId
    }

    fun setClickListener(itemClickListener: MainActivity) {
        myClickListener = itemClickListener
    }

    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }
}