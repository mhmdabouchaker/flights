package com.mac.flights.widgets

import android.content.Context
import android.util.AttributeSet
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.mac.flights.R

class LoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LottieAnimationView(context, attrs, defStyleAttr) {

    init {
        setAnimation(R.raw.lottie_anim_loader)
        playAnimation()
        repeatCount = LottieDrawable.INFINITE
    }
}