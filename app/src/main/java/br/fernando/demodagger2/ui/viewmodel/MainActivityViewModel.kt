package br.fernando.demodagger2.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.fernando.demodagger2.MyApplication
import br.fernando.demodagger2.model.RecyclerList
import br.fernando.demodagger2.service.RetrofitServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {


    @Inject
    lateinit var mService: RetrofitServiceInterface

    private lateinit var liveDataList: MutableLiveData<RecyclerList>


    init {
        /*aqui nos necessitamos para iniciar a aplicacao*/
        (application as MyApplication).getRetrofitComponent().inject(this)
        liveDataList = MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<RecyclerList> {
        return liveDataList
    }

    fun makeApiCall() {

        val call: Call<RecyclerList> = mService.getDataFromAPi("atl")
        call.enqueue(object : Callback<RecyclerList> {
            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
                liveDataList.postValue(null)
            }

            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {
                if (response.isSuccessful) {
                    liveDataList.postValue(response.body())
                } else {
                    liveDataList.postValue(null)
                }
            }
        })
    }


}