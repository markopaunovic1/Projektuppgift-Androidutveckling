package com.example.projektuppgift_androidutveckling

object CartManager {
    val cartList = mutableListOf<Dish>()

    fun totalSum(): Double {
        var totalPrice = 0.0
        var shipmentCost = 99.0
        for (dish in cartList) {
            if (dish.dishPrice != null) {
                totalPrice += dish.dishPrice.toDouble()
            }

        }
        if (totalPrice == 0.0){
            return totalPrice
        }
        return totalPrice + shipmentCost
    }
}