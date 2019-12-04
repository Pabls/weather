package ru.skillbranch.gameofthrones.presentation.base

interface IPresenter<V : IBaseView> {
    fun attachView(view: V?)
    fun detachView()
    fun getView(): V?
}