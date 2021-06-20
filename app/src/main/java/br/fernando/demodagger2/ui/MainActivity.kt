package br.fernando.demodagger2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.fernando.demodagger2.R
import br.fernando.demodagger2.model.RecyclerList
import br.fernando.demodagger2.ui.adapter.RecyclerViewAdapter
import br.fernando.demodagger2.ui.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recyclerViewAdapter = RecyclerViewAdapter()
        recycler_view.adapter = recyclerViewAdapter
    }

    private fun initViewModel() {
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        mainActivityViewModel.getLiveDataObserver().observe(this, object : Observer<RecyclerList> {
            override fun onChanged(t: RecyclerList?) {
                if (t != null) {
                    recyclerViewAdapter.setUpdatedData(t.items)
                    recyclerViewAdapter.notifyDataSetChanged()

                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "error in getting the date",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        })

        mainActivityViewModel.makeApiCall()
    }


}
