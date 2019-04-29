package com.haejung.template.details

import com.haejung.template.details.domain.GetDroneDetails

class DetailsPresenter(
    private val droneName: String,
    private val getDroneDetails: GetDroneDetails, // UseCase
    private val detailsView: DetailsContract.View
) : DetailsContract.Presenter {

    init {
        detailsView.presenter = this
    }

    override fun start() {
        detailsView.setLoadingIndicator(true)
        getDroneDetails.execute(
            onSuccess = {
                if (detailsView.isActive) {
                    detailsView.setLoadingIndicator(false)
                    detailsView.showDroneDetails(it)
                }
            },
            onError = {
                if (detailsView.isActive) {
                    detailsView.setLoadingIndicator(false)
                    detailsView.showError()
                }
            },
            params = GetDroneDetails.Params(droneName)
        )
    }

    override fun result(requestCode: Int, resultCode: Int) {
        TODO("Not implemented yet")
    }

}