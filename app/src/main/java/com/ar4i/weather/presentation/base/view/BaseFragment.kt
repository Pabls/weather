package com.ar4i.weather.presentation.base.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.ar4i.weather.R
import com.ar4i.weather.app.WeatherApp
import com.ar4i.weather.app.di.components.IApplicationComponent
import com.google.android.material.snackbar.Snackbar
import ru.skillbranch.gameofthrones.presentation.base.IBaseView
import ru.skillbranch.gameofthrones.presentation.base.IPresenter

abstract class BaseFragment : Fragment(), IBaseView {

    private var rootLayout: FrameLayout? = null
    private var presenter: IPresenter<IBaseView>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inject()
        rootLayout = activity?.findViewById(R.id.fl_container)
        getPresenter()?.attachView(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        getPresenter()?.detachView()
    }

    override fun showMessage(message: String) {
        createAndShowSnackbar(message = message, bgColor = R.color.blue)
    }

    override fun showError(message: String) {
        createAndShowSnackbar(message = message, bgColor = R.color.red)
    }

    override fun showMessageWithAction(message: String, actionTitle: String, action: () -> Unit) {
        createAndShowSnackbar(message = message, actionTitle = actionTitle, action = action)
    }

    override fun showLoading() {
        if (rootLayout != null)
            View.inflate(activity, R.layout.view_progress_bar, rootLayout)
    }

    override fun hideLoading() {
        if (rootLayout != null)
            rootLayout?.removeViewAt(rootLayout!!.childCount - 1)
    }

    fun getPresenter(): IPresenter<IBaseView>? = presenter

    fun setPresenter(presenter: IPresenter<IBaseView>) {
        this.presenter = presenter
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun inject()

    protected fun getComponent(): IApplicationComponent = WeatherApp.applicationComponent

    private fun createAndShowSnackbar(
        message: String,
        bgColor: Int? = null,
        actionTitle: String? = null,
        action: (() -> Unit)? = null
    ) {
        if (rootLayout != null) {
            val snackbar = getSnackbar(message)

            if (bgColor != null) {
                snackbar.view.setBackgroundColor(ContextCompat.getColor(rootLayout!!.context, bgColor))
            }

            if (actionTitle != null && action != null) {
                snackbar.setAction(actionTitle) { action.invoke() }
                snackbar.setActionTextColor(ContextCompat.getColor(rootLayout!!.context, R.color.light_gray))
            }

            snackbar.show()
        }
    }

    private fun getSnackbar(message: String): Snackbar = Snackbar.make(rootLayout!!, message, Snackbar.LENGTH_LONG)
}