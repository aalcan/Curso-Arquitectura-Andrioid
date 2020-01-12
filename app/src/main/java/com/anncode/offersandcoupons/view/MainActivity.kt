package com.anncode.offersandcoupons.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.anncode.offersandcoupons.R
import com.anncode.offersandcoupons.databinding.ActivityMainBinding
import com.anncode.offersandcoupons.model.Coupon
import com.anncode.offersandcoupons.viewmodel.CouponViewModel

class MainActivity : AppCompatActivity(){

    private var couponViewModel: CouponViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        setBindings(savedInstanceState)

        //CALL COUPONS

        //getCoupons - Lista de copunes

    }

    fun setBindings(savedInstanceState: Bundle?){
        var activityMainBinding : ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        couponViewModel = ViewModelProviders.of(this).get(CouponViewModel::class.java)

        activityMainBinding.setModel(couponViewModel)
        setupListUpdate()
    }

    fun setupListUpdate(){
        couponViewModel?.callCoupons()
        couponViewModel?.getCoupons()?.observe(this, Observer {
                coupon: List<Coupon> -> couponViewModel?.setCouponsInRecycleyAdapter(coupon)
        })
        setupListClick()
    }

    fun setupListClick() {
        couponViewModel?.getCouponSelected()?.observe(this,
            Observer {coupon: Coupon ->
                Log.i("CLICK", coupon.title)

            })
    }

}
