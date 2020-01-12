package com.anncode.offersandcoupons.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CouponRepositoryImpl(): CouponRepository {

    private var coupons = MutableLiveData<List<Coupon>>()
    //Sujeto MutableLiveData
    //Observadores - Lista de cupones

    override fun getCoupons(): MutableLiveData<List<Coupon>> {
        return  coupons
    }

    //TODA LA LÓGICA DE CONEXIÓN
    override fun callCouponsAPI() {
        var couponsList: ArrayList<Coupon>? = ArrayList<Coupon>()
        var apiAdapter: ApiAdapter = ApiAdapter()
        val apiService = apiAdapter.getClientService()
        val call = apiService.getCoupons()
        call.enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.e("ERROR: ", t.message)
                t.stackTrace
            }
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val offersJsonArray = response.body()?.getAsJsonArray("offers")
                offersJsonArray?.forEach { jsonElement: JsonElement ->
                    var jsonObject = jsonElement.asJsonObject
                    var coupon = Coupon(jsonObject)
                    couponsList?.add(coupon)
                }
                coupons.value = couponsList
            }
        })
    }

}