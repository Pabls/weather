package com.ar4i.weather.presentation.cities.view

import com.ar4i.weather.R
import com.ar4i.weather.presentation.base.view.BaseFragment
import ru.skillbranch.gameofthrones.presentation.base.IBaseView
import ru.skillbranch.gameofthrones.presentation.base.IPresenter

class CitiesFragment : BaseFragment(), ICitiesView {

    companion object {
        fun newInstance() = CitiesFragment()
    }

    private var presenter: IPresenter<IBaseView>? = null

    override fun getLayoutId(): Int = R.layout.fragment_cities

    override fun inject() {
        getComponent().inject(citiesFragment = this)
    }

    override fun getPresenter(): IPresenter<IBaseView>? = presenter
    //if (presenter is IPresenter<*>) presenter as IPresenter<*> else null


    override fun setPresenter(presenter: IPresenter<IBaseView>) {
        this.presenter = presenter
    }
}