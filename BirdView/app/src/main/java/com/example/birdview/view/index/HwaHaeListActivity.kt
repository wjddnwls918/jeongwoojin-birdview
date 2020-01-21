package com.example.birdview.view.index

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.birdview.R
import com.example.birdview.databinding.ActivityMainBinding

class HwaHaeListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory
    private lateinit var viewModel: HwaHaeListViewModel

    private lateinit var adapter:HwaHaeListAdapter
    private lateinit var layoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HwaHaeListViewModel::class.java)

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                viewmodel = viewModel
            }


        initKeyboard()

    }

    fun initKeyboard() {
        binding.svSearchList.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                //Toast.makeText(applicationContext, query.toString(), Toast.LENGTH_SHORT).show()
                viewModel.getList(null, null, query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    fun initRecyclerView() {
        adapter = HwaHaeListAdapter(viewModel)
        layoutManager = GridLayoutManager(applicationContext,2)

        binding.rvHwahaeList.layoutManager = layoutManager
        binding.rvHwahaeList.adapter = adapter
    }
}
