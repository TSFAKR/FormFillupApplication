package com.tsfapps.myapplication.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.annotation.IdRes
import androidx.gridlayout.widget.GridLayout

class GridRadioGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : GridLayout(context, attrs, defStyleAttr), View.OnClickListener {

    @IdRes
    var selectedRadioButtonId: Int? = null
        get() = getSelectedRadioButton()?.id
        private set

    private fun getSelectedRadioButton(): RadioButton? {
        for (index in 0 until childCount) {
            val radioButton = getChildAt(index) as RadioButton
            if (radioButton.isChecked) return radioButton
        }
        return null
    }

    override fun onClick(view: View) {
        for (index in 0 until childCount) {
            val radioButton = getChildAt(index) as RadioButton
            radioButton.isChecked = false
        }
        val radioButton = view as RadioButton
        radioButton.isChecked = true
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        super.addView(child, index, params)
        child?.setOnClickListener(this)
    }

}