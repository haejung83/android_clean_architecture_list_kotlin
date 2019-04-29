package com.haejung.template.details

import com.haejung.template.base.BasePresenter
import com.haejung.template.base.BaseView
import com.haejung.template.details.domain.DroneDetails

interface DetailsContract {

    interface View : BaseView<Presenter> {

        var isActive: Boolean

        fun setLoadingIndicator(active: Boolean)

        fun showDroneDetails(drone: DroneDetails)

        fun showNoDrones()

        fun showError()

    }

    interface Presenter : BasePresenter {

        fun result(requestCode: Int, resultCode: Int)

    }

}