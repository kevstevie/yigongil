package com.created.team201.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.created.team201.presentation.home.HomeViewModel.*
import com.created.team201.presentation.home.HomeViewModel.UserStudyState.*

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("isVisible")
    fun isVisible(view: View, isVisible: Boolean) {
        when (isVisible) {
            true -> view.visibility = View.VISIBLE
            false -> view.visibility = View.INVISIBLE
        }
    }

    @JvmStatic
    @BindingAdapter("isGone")
    fun isGone(view: View, isGone: Boolean) {
        when (isGone) {
            true -> view.visibility = View.GONE
            false -> view.visibility = View.VISIBLE
        }
    }

    @JvmStatic
    @BindingAdapter("glideSrc")
    fun glideSrc(imageview: ImageView, image: Int) {
        Glide.with(imageview.context)
            .load(image)
            .into(imageview)
    }

    @JvmStatic
    @BindingAdapter("isEnabled")
    fun isEnabled(view: View, isEnabled: Boolean) {
        view.isEnabled = isEnabled
    }

    @JvmStatic
    @BindingAdapter("glideSrcUrl")
    fun glideSrcUrl(imageview: ImageView, imageUrl: String?) {
        imageUrl?.let {
            Glide.with(imageview.context)
                .load(it)
                .into(imageview)
        }
    }

    @JvmStatic
    @BindingAdapter("hasStudy")
    fun setVisibleOfHome(view: View, userStudyState: UserStudyState?) {
        when (userStudyState is UserStudyState.Nothing) {
            true -> view.visibility = View.VISIBLE
            false -> view.visibility = View.GONE
        }
    }

    @JvmStatic
    @BindingAdapter("isMaster")
    fun setVisibleOfHomeStudy(view: View, isMaster: Boolean) {
        when (isMaster) {
            true -> view.visibility = View.VISIBLE
            false -> view.visibility = View.GONE
        }
    }
}
