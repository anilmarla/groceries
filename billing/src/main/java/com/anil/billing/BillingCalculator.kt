package com.anil.billing

class BillingCalculator {
    public fun calculateGST(amount: Int) : Int{
        // calculate gst
        return (amount * 18 / 100)
    }
}