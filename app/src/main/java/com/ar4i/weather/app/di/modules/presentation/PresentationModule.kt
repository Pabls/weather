package com.ar4i.weather.app.di.modules.presentation

import android.content.Context
import com.ar4i.weather.presentation.cities.presenter.CitiesPresenter
import ru.skillbranch.gameofthrones.presentation.base.IBaseView
import ru.skillbranch.gameofthrones.presentation.base.IPresenter

class PresentationModule(private val context: Context)  {
    fun provideCitiesPresenter(): IPresenter<IBaseView> = CitiesPresenter() as IPresenter<IBaseView>
}