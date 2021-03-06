package br.fernando.demodagger2.service


import br.fernando.demodagger2.ui.viewmodel.MainActivityViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [RetrofitModule::class])
interface RetrofitComponent {

    fun inject(mainActivityViewModel: MainActivityViewModel)
}