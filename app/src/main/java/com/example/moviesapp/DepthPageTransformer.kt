package com.example.moviesapp

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class DepthPageTransformer : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {

        val alpha: Float
        val scaleFactor: Float

        if (position < -1) {
            // Trang nằm bên trái của trang hiện tại
            alpha = 0f
            scaleFactor = 0f
        } else if (position <= 0) {
            // Trang hiện tại và trang bên trái của nó
            alpha = 1 + position
            scaleFactor = (1 + position)
        } else if (position <= 1) {
            // Trang bên phải và trang tiếp theo
            alpha = 1 - position
            scaleFactor = 1 - position
        } else {
            // Trang nằm bên phải của trang hiện tại
            alpha = 0f
            scaleFactor = 0f
        }

        page.alpha = alpha
        page.scaleX = scaleFactor
        page.scaleY = scaleFactor
    }
}
