package com.example.cart. istener

import android.view.View
import com.example.cart.DrinkModel

interface IDrinkLoadListener {
    abstract val mainLayout: View

    fun onDrinkLoadSuccess(drinkModelList:List<DrinkModel>?)
    fun onDrinkLoadFailed(message:String?)


}