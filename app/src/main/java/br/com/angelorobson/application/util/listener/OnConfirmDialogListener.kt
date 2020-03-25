package br.com.angelorobson.application.util.listener

import android.content.DialogInterface

interface OnConfirmDialogListener {

    fun onPressPositiveButton(dialog: DialogInterface, id: Int)
    fun onPressNegativeButton(dialog: DialogInterface, id: Int)
}