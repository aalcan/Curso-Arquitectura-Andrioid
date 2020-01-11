package com.anncode.offersandcoupons.view

import com.anncode.offersandcoupons.model.Coupon

interface CouponView {
    fun getCoupons()

    fun showCoupons(coupons: ArrayList<Coupon>?)
}