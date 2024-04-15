package com.deepak.demo

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var prodId: TextView
    private lateinit var prodName: TextView
    private lateinit var prodDesc: TextView
    private lateinit var prodThumbnail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        findViewByIds()
        setDataFromIntent()
    }

    private fun findViewByIds() {
        prodId = findViewById(R.id.prod_id)
        prodName = findViewById(R.id.prod_name)
        prodDesc = findViewById(R.id.prod_desc)
        prodThumbnail = findViewById(R.id.prod_thumbnail)
    }

    private fun setDataFromIntent(){
        val idSpan = SpannableString("Id: "+intent.getStringExtra("id"))
        idSpan.setSpan(
            ForegroundColorSpan(Color.RED),
            0,
            3,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        idSpan.setSpan(StyleSpan(Typeface.BOLD), 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        prodId.text = idSpan

        val nameSpan = SpannableString("Name: "+intent.getStringExtra("name"))
        nameSpan.setSpan(
            ForegroundColorSpan(Color.RED),
            0,
            5,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        nameSpan.setSpan(StyleSpan(Typeface.BOLD), 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        prodName.text = nameSpan

        val descSpan = SpannableString("Description: "+intent.getStringExtra("desc"))
        descSpan.setSpan(
            ForegroundColorSpan(Color.RED),
            0,
            12,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        descSpan.setSpan(StyleSpan(Typeface.BOLD), 0, 12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        prodDesc.text = descSpan

        prodThumbnail.text = intent.getStringExtra("thumbnail")
    }
}