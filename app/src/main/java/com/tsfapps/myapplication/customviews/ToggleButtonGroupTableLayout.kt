package com.tsfapps.myapplication.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RadioButton
import android.widget.TableLayout
import android.widget.TableRow

class ToggleButtonGroupTableLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : TableLayout(context, attrs), View.OnClickListener {

    companion object {
        private const val TAG = "ToggleButtonGroupTableLayout"
    }

    private var activeRadioButton: RadioButton? = null

    init {
        // Empty constructor body
    }

    override fun onClick(v: View) {
        val rb = v as RadioButton
        activeRadioButton?.isChecked = false
        rb.isChecked = true
        activeRadioButton = rb
    }

    override fun addView(child: View, index: Int, params: android.view.ViewGroup.LayoutParams) {
        super.addView(child, index, params)
        setChildrenOnClickListener(child as TableRow)
    }

    override fun addView(child: View, params: android.view.ViewGroup.LayoutParams) {
        super.addView(child, params)
        setChildrenOnClickListener(child as TableRow)
    }

    private fun setChildrenOnClickListener(tr: TableRow) {
        val childCount = tr.childCount
        for (i in 0 until childCount) {
            val v = tr.getChildAt(i)
            if (v is RadioButton) {
                v.setOnClickListener(this)
            }
        }
    }

    fun getCheckedRadioButtonId(): Int {
        return activeRadioButton?.id ?: -1
    }
}
