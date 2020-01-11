package com.anncode.offersandcoupons.presenter

import com.anncode.offersandcoupons.model.Coupon

interface CouponPresenter {
    //VIEW
    fun getCoupons()

    //PRESENTER
    fun showCoupons(coupons: ArrayList<Coupon>?)
}