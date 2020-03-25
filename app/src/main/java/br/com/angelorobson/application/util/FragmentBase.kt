package br.com.angelorobson.application.util

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import br.com.angelorobson.application.util.listener.OnConfirmDialogListener
import kotlinx.android.synthetic.main.host_navigation_activity.*


open class FragmentBase : Fragment() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun setUpBehavorBottomNavigation() {
        val bottomNavigationView = activity?.bottomNavigation
        val layoutParams = bottomNavigationView?.layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.behavior = BottomNavigationBehavior()
    }

    fun showAlertError(message: String) {
        val builder = AlertDialog.Builder(requireContext())

        builder
            .setMessage(message.toInt())
            .setCancelable(false)
            .setPositiveButton("OK") { _, _ -> }

        val alert = builder.create()
        alert.show()
    }

    fun showConfirmDialog(title: String, message: String, dialogListener: OnConfirmDialogListener) {
        val builder = AlertDialog.Builder(requireContext())

        builder
            .setMessage(message.toInt())
            .setCancelable(false)
            .setPositiveButton("OK") { dialog, id ->
                dialogListener.onPressPositiveButton(dialog, id)
            }
        builder.setNegativeButton("Fechar") { dialog, id ->
            dialogListener.onPressNegativeButton(dialog, id)
        }

        val alert = builder.create()
        alert.show()
    }

    fun showConfirmDialogWithMessageString(
        title: String,
        message: String,
        dialogListener: OnConfirmDialogListener
    ) {
        val builder = AlertDialog.Builder(requireContext())

        builder
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton("OK") { dialog, id ->
                dialogListener.onPressPositiveButton(dialog, id)
            }
        builder.setNegativeButton("Fechar") { dialog, id ->
            dialogListener.onPressNegativeButton(dialog, id)
        }

        val alert = builder.create()
        alert.show()
    }

    fun showConfirmDialogWithCallback(
        title: String,
        message: String,
        dialogListener: OnConfirmDialogListener
    ) {
        val builder = AlertDialog.Builder(requireContext())

        builder
            .setMessage(message)
            .setPositiveButton("OK") { dialog, id ->
                dialogListener.onPressPositiveButton(dialog, id)
            }

        val alert = builder.create()
        alert.show()
    }

    fun hideBottomNavigation() {
        activity?.bottomNavigation?.visibility = View.GONE
    }

    fun showBottomNavigation() {
        activity?.bottomNavigation?.visibility = View.VISIBLE
    }

    fun hideToolbar() {
        activity?.toolbar?.visibility = View.GONE
    }


    fun showToolbarWithDisplayArrowBack(title: String) {
        val toolbar = activity?.toolbar
        toolbar?.visibility = View.VISIBLE
        toolbar?.title = title

        val appCompatActivity = activity as AppCompatActivity?

        appCompatActivity?.setSupportActionBar(toolbar)
        appCompatActivity?.supportActionBar?.setDisplayShowTitleEnabled(true)
        appCompatActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun showToolbarWithoutDisplayArrowBack(title: String = "") {
        val toolbar = activity?.toolbar
        toolbar?.visibility = View.VISIBLE
        toolbar?.title = title

        val appCompatActivity = activity as AppCompatActivity?

        appCompatActivity?.setSupportActionBar(toolbar)
        appCompatActivity?.supportActionBar?.setDisplayShowTitleEnabled(true)
        appCompatActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }


}