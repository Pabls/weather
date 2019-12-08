package com.ar4i.weather.app.di.modules.presentation

import com.ar4i.weather.app.di.modules.repositories.RepositoriesModule
import com.ar4i.weather.presentation.cities.presenter.CitiesPresenter
import com.ar4i.weather.presentation.weather.presenter.WeatherPresenter
import ru.skillbranch.gameofthrones.presentation.base.IBaseView
import ru.skillbranch.gameofthrones.presentation.base.IPresenter

class PresentationModule {
    fun provideCitiesPresenter(): IPresenter<IBaseView> =
        CitiesPresenter(
            RepositoriesModule.provideCitiesRepository(),
            RepositoriesModule.provideWeatherRepository(),
            RepositoriesModule.provideResourcesRepository()
        ) as IPresenter<IBaseView>

    fun provideWeatherPresenter(): IPresenter<IBaseView> =
        WeatherPresenter(
            RepositoriesModule.provideWeatherRepository(),
            RepositoriesModule.provideResourcesRepository(),
            RepositoriesModule.provideCitiesRepository()
        ) as IPresenter<IBaseView>
}