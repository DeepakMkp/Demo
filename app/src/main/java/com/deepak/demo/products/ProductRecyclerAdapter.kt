package com.deepak.demo.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.deepak.demo.R
import com.deepak.demo.interfaces.RecyclerViewClickInterface

class ProductRecyclerAdapter(
    private val productsList: List<Product>,
    private val recyclerViewClickInterface: RecyclerViewClickInterface
) : RecyclerView.Adapter<ProductRecyclerAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_product_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val data = productsList[position]
        holder.bind(data,position)
    }

    override fun getItemCount() = productsList.size

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Product, position: Int) {
            itemView.findViewById<CardView>(R.id.card_view).setOnClickListener {
                recyclerViewClickInterface.onProductClick(position)
            }
            itemView.findViewById<TextView>(R.id.id_tv).text = item.id.toString()
            itemView.findViewById<TextView>(R.id.product_name_tv).text = item.title

        }
    }
}