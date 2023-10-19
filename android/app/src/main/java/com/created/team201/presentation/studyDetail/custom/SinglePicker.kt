package com.created.team201.presentation.studyDetail.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.created.team201.R
import com.created.team201.databinding.SinglePickerBinding

class SinglePicker @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleArr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleArr) {
    private val binding: SinglePickerBinding by lazy {
        SinglePickerBinding.inflate(LayoutInflater.from(context), this, true)
    }

    val value: Int
        get() = binding.npSinglePicker.value

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.SinglePicker, 0, 0).apply {
            runCatching {
                binding.npSinglePicker.value =
                    getInteger(R.styleable.SinglePicker_value, DEFAULT_VALUE)
                binding.npSinglePicker.minValue =
                    getInteger(R.styleable.SinglePicker_minValue, DEFAULT_VALUE)
                binding.npSinglePicker.maxValue =
                    getInteger(R.styleable.SinglePicker_maxValue, DEFAULT_VALUE)
                binding.tvSinglePicker.text = getString(R.styleable.SinglePicker_android_title)
            }.also {
                recycle()
            }
        }
    }

    fun setValue(value: Int) {
        binding.npSinglePicker.value =
            if (value in (binding.npSinglePicker.minValue..binding.npSinglePicker.maxValue))
                value
            else binding.npSinglePicker.minValue
    }

    companion object {
        private const val DEFAULT_VALUE = -1
    }
}
