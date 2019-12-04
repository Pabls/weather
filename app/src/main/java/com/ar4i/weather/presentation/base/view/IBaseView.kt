package ru.skillbranch.gameofthrones.presentation.base

interface IBaseView {
    fun showLoading()
    fun hideLoading()
    fun showMessage(message: String)
    fun showError(message: String)
    fun showMessageWithAction(message: String, actionTitle: String, action: () -> Unit)
}