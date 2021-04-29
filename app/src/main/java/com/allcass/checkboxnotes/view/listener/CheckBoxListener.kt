package com.allcass.checkboxnotes.view.listener

import android.widget.CompoundButton
import com.allcass.checkboxnotes.service.model.CheckBoxModel

interface CheckBoxListener {
    fun onCheckChanged(
        checkboxModel: CheckBoxModel, newStatus: Boolean
    )
    fun onClickImageButton(checkBox: CheckBoxModel)
}