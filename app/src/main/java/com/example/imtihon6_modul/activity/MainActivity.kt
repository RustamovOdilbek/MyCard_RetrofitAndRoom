package com.example.imtihon6_modul.activity

import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imtihon6_modul.R
import com.example.imtihon6_modul.adapter.CardAdapter
import com.example.imtihon6_modul.database.CardRepository
import com.example.imtihon6_modul.modul.Card
import com.example.imtihon6_modul.modul.CardSer
import com.example.imtihon6_modul.netWorking.RetrofitHttp
import com.example.networkingdatabase.helper.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var iv_open_app: ImageView
    lateinit var list: ArrayList<Card>
    lateinit var recyclerView: RecyclerView
    lateinit var listServer: ArrayList<CardSer>
    lateinit var adapter: CardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iv_open_app = findViewById(R.id.iv_open_app)
        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = GridLayoutManager(this, 1)

        initViews()
        list = ArrayList()
        listServer = ArrayList()



        if (isInternetAvailable()) {

            onlane()
            Logger.d("$#$#$#", "online")
            Log.d("$#$#$#", "online")
            inspection()
        }else{
            initViews()
            Logger.d("$#$#$#", "ofline")
            Log.d("$#$#$#", "online")
        }

        iv_open_app.setOnClickListener {
            openActivity()
        }
    }

    private fun onlane() {
        RetrofitHttp.cardServer.getAllCards().enqueue(object : Callback<ArrayList<CardSer>>{
            override fun onResponse(
                call: Call<ArrayList<CardSer>>,
                response: Response<ArrayList<CardSer>>
            ) {
                Logger.d("server", response.body().toString())
                list.clear()
                listServer.addAll(response.body()!!)
                for (seaver in listServer){
                    list.add(Card(seaver.id, seaver.card_id, seaver.cvv, seaver.is_boolean, seaver.data, seaver.name))
                }
                refreshAdapter(list)
            }

            override fun onFailure(call: Call<ArrayList<CardSer>>, t: Throwable) {

            }

        })
    }

    private fun inspection() {
        val repository = CardRepository(application)
        val cards = repository.getCards() as ArrayList<Card>
        for (card in cards){
            if (card.is_boolean){
                var cardSer = CardSer(card.card_id, card.cvv, card.data, card.id!!, card.is_boolean, card.name)

                RetrofitHttp.cardServer.createCard(cardSer).enqueue(object : Callback<CardSer>{
                    override fun onResponse(call: Call<CardSer>, response: Response<CardSer>) {

                    }

                    override fun onFailure(call: Call<CardSer>, t: Throwable) {

                    }

                })
                repository.updateCard(card.id, false)
            }

            Logger.d("data", repository.getCards().toString())
        }
    }

    private fun initViews() {
        val repository = CardRepository(application)
        list = repository.getCards() as ArrayList<Card>

        refreshAdapter(list)
    }

    private fun refreshAdapter(list: ArrayList<Card>) {
        adapter = CardAdapter(this, list)
        recyclerView.adapter = adapter
    }

    private fun openActivity() {
        val intent = Intent(this, AddNewCard::class.java)
        startActivity(intent)
    }

    private fun isInternetAvailable(): Boolean {
        val manager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val infoMobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        val infoWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        return infoMobile!!.isConnected || infoWifi!!.isConnected
    }
}