package com.example.imtihon6_modul.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.imtihon6_modul.R
import com.example.imtihon6_modul.database.CardRepository
import com.example.imtihon6_modul.modul.Card
import com.example.networkingdatabase.helper.Logger


class AddNewCard : AppCompatActivity() {
    lateinit var et_card_number: EditText
    lateinit var et_card_day: EditText
    lateinit var et_card_month: EditText
    lateinit var et_card_cvv: EditText
    lateinit var et_card_name: EditText
    lateinit var btn_add: Button
    lateinit var iv_close: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_card)

        initViews()
    }

    private fun initViews() {
        et_card_number = findViewById(R.id.et_card_number)
        et_card_day = findViewById(R.id.et_card_day)
        et_card_month = findViewById(R.id.et_card_month)
        et_card_cvv = findViewById(R.id.et_card_cvv)
        et_card_name = findViewById(R.id.et_card_name)
        et_card_number = findViewById(R.id.et_card_number)
        btn_add = findViewById(R.id.btn_add)
        iv_close = findViewById(R.id.iv_close)

        btn_add.setOnClickListener {
            val repository = CardRepository(application)
            val card = Card(
                card_id = et_card_number.text.toString(),
                cvv = et_card_cvv.text.toString(),
                data = "${et_card_day.text}/${et_card_month.text}",
                name = et_card_name.text.toString(),
                is_boolean = true,
            )
            repository.saveCard(card)

            finish()
        }

        val repository = CardRepository(application)
        Logger.d("@@@@", repository.getCards().toString())

        iv_close.setOnClickListener {
            finish()
        }
    }
}