package com.haejung.template.drones

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.haejung.template.R
import com.haejung.template.data.injectRepository
import com.haejung.template.drones.domain.GetDroneSummaries
import com.haejung.template.drones.domain.SetRefreshDrones
import com.haejung.template.util.replaceFragmentInActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DronesActivity : AppCompatActivity() {

    private lateinit var dronesPresenter: DronesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drones)

        // Create Fragment (View)
        val dronesFragment = supportFragmentManager.findFragmentById(R.id.content)
                as DronesFragment? ?: DronesFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.content)
        }

        val repository = injectRepository(applicationContext)
        val subscribeScheduler = Schedulers.io()
        val observeScheduler = AndroidSchedulers.mainThread()

        // Create Presenter (Presenter)
        dronesPresenter = DronesPresenter(
            SetRefreshDrones(repository, subscribeScheduler, observeScheduler),
            GetDroneSummaries(repository, subscribeScheduler, observeScheduler),
            dronesFragment
        )
    }

}
