package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase1

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.lukaslechner.coroutineusecasesonandroid.base.BaseActivity
import com.lukaslechner.coroutineusecasesonandroid.base.useCase1Description
import com.lukaslechner.coroutineusecasesonandroid.databinding.ActivityPerformsinglenetworkrequestBinding
import com.lukaslechner.coroutineusecasesonandroid.utils.fromHtml
import com.lukaslechner.coroutineusecasesonandroid.utils.setGone
import com.lukaslechner.coroutineusecasesonandroid.utils.setVisible
import com.lukaslechner.coroutineusecasesonandroid.utils.toast
//two parts : coroutines and flows
//uses cases of coruitnes : a.network requests sequentially and concurrently
//b.time out ans retires
//c.bg tasks
//d.live stock trading appilication[flows ]

//coroutines: suspend functions/coroutine builders/coroutine scopes/blocking vs suspending/context and dispatchers/concurrency/jobs and supervisiors/cooperative cancellation/reactive programming/
//flows:builders and operators/hot and cold flows/shared flows and stateflows/channels

//to clone the repositiry to go vcs-->get from version control-->pate the url--->after cloning -->swich to proper branch -->3 branches are avaliable-->comlete[all complte -->refer this for codes ] empty excerise[exercise not complete]   empty[exercses and uses cases are not complete]
//the empty app with all empty use cases must be able to run on emultor now

//don't borther much on main actvty codes: just to fix the ui they are implmented

//exported true-->app open krne pr starting se nhi exceute hoga wo activty direct khulegi
//emulator pe activty direct slect krke ab wo direct khulegi run krne pr

//basecase package + mainactivty and Coroutineusecase file->they are jyst ui element codes-->don't bother much about them
//mock-->is packahe me ek sample api create hai jisme delay hai data hai-->not much concern
//playgrounf.utils==>just time calculate krke return -->not much concern

//main concern--> usecases and utils
//each usecase is independent
//corouies are best way to do async programming

//the async task wi;; be done in three ways  1. Normal callbacks
//2.RxJava
//3.Corouines--->best

//why is aynchrous programming used for ui framework like android system?
//whenevr we call a netwrok api or db or any time consuming  taks-->we are actually calligna async function
//these function that return resukt after a time autm come under async tasks
//hence we need to serate them from the main ui



//in tis use case 1: we are sgowing list of most recent android applications vesions via single netwrok req to our mock api
//2 functions on mock api define two end points

//use case 1 me bas yahi tak ki vm and activty ui stae ye sab kaise work krega aage
//for actual implmentaion->use case 2 me jjao

class PerformSingleNetworkRequestActivity : BaseActivity() {

    override fun getToolbarTitle() = useCase1Description

    private val binding by lazy { ActivityPerformsinglenetworkrequestBinding.inflate(layoutInflater) }
    private val viewModel: PerformSingleNetworkRequestViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.uiState().observe(this, Observer { uiState ->
            if (uiState != null) {
                render(uiState)
            }
        })

        //main code-->activty and vm communication

        binding.btnPerformSingleNetworkRequest.setOnClickListener {
           //vm metod called
            viewModel.performSingleNetworkRequest()
        }
    }

    private fun render(uiState: UiState) {
        when (uiState) {
            is UiState.Loading -> {
                onLoad()
            }
            is UiState.Success -> {
                onSuccess(uiState)
            }
            is UiState.Error -> {
                onError(uiState)
            }
        }
    }

    private fun onLoad() = with(binding) {
        progressBar.setVisible()
        textViewResult.text = ""
        btnPerformSingleNetworkRequest.isEnabled = false
    }

    private fun onSuccess(uiState: UiState.Success) = with(binding) {
        progressBar.setGone()
        btnPerformSingleNetworkRequest.isEnabled = true
        val readableVersions = uiState.recentVersions.map { "API ${it.apiLevel}: ${it.name}" }
        textViewResult.text = fromHtml(
            "<b>Recent Android Versions</b><br>${readableVersions.joinToString(separator = "<br>")}"
        )
    }

    private fun onError(uiState: UiState.Error) = with(binding) {
        progressBar.setGone()
        btnPerformSingleNetworkRequest.isEnabled = true
        toast(uiState.message)
    }
}