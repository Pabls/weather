package com.ar4i.weather.presentation.base

import ru.skillbranch.gameofthrones.presentation.base.IBaseView
import ru.skillbranch.gameofthrones.presentation.base.IPresenter

open class BasePresenter<V : IBaseView>: IPresenter<V> {
    private var view: V? = null

    override fun attachView(view: V?) { this.view = view }

    override fun detachView() { view = null }

    override fun getView(): V? = view
}