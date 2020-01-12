package com.anncode.offersandcoupons.model

import androidx.lifecycle.MutableLiveData

interface CouponRepository {
    fun getCoupons(): MutableLiveData<List<Coupon>>
    fun callCouponsAPI()
}