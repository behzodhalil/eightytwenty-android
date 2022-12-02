package uz.behzod.eightytwenty.utils.constants

import uz.behzod.eightytwenty.R

enum class Water(val quantity: Long, val image: Int) {
    HOT_CUP(200,R.drawable.hot_cup),
    MUG(400, R.drawable.mug),
    BOTTLE(600,R.drawable.water_bottle),
    MINERAL_WATER(800,R.drawable.mineral_water),
    GLASS(1000,R.drawable.water_glass)
}
