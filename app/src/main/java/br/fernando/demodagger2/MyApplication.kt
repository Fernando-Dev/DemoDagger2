package br.fernando.demodagger2

import android.app.Application
import br.fernando.demodagger2.service.DaggerRetrofitComponent
import br.fernando.demodagger2.service.RetrofitComponent
import br.fernando.demodagger2.service.RetrofitModule

class MyApplication : Application() {

    private lateinit var retrofitComponent: RetrofitComponent

    override fun onCreate() {
        super.onCreate()

        retrofitComponent = DaggerRetrofitComponent.builder()
            .retrofitModule(RetrofitModule())
            .build()
    }

    fun getRetrofitComponent(): RetrofitComponent {
        return retrofitComponent
    }
}