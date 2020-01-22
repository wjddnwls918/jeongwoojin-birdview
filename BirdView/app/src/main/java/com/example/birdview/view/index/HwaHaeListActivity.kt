package com.example.birdview.view.index

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.birdview.R
import com.example.birdview.databinding.ActivityMainBinding

class HwaHaeListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory
    private lateinit var viewModel: HwaHaeListViewModel

    //private lateinit var adapter:HwaHaeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HwaHaeListViewModel::class.java)

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                viewmodel = viewModel
            }

        initKeyboard()
        initRecyclerView()
        initObserveList()
    }


    private fun initKeyboard() {
        binding.svSearchList.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                //Toast.makeText(applicationContext, query.toString(), Toast.LENGTH_SHORT).show()
                viewModel.adapter.removeAllItems()
                viewModel.getList(null, null, query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun initRecyclerView() {
        binding.viewmodel?.let {
            binding.rvHwahaeList.adapter = viewModel.adapter
        }
    }

    private fun initObserveList() {
        viewModel.list.observe(this, Observer {
            list ->

            list?.let {
                if(list.isNotEmpty()) {
                    Log.d("checklist", "list size is : " + it.size)
                    Log.d("checklist", "list type is : " + it[0].score)
                    viewModel.adapter.addItems(list)
                }
            }
        })
    }

}
