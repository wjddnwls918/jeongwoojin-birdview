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
import com.example.birdview.common.EndlessRecyclerViewScrollListener
import com.example.birdview.common.EventObserver
import com.example.birdview.databinding.HwahaeListActivityBinding
import com.example.birdview.view.detail.HwaHaeDetailDialogFragment

class HwaHaeListActivity : AppCompatActivity() {

    private lateinit var binding: HwahaeListActivityBinding

    private lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory
    private lateinit var viewModel: HwaHaeListViewModel

    private lateinit var scrollerListener: EndlessRecyclerViewScrollListener

    private val DETAIL_TAG = "detail"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HwaHaeListViewModel::class.java)

        binding = DataBindingUtil.setContentView<HwahaeListActivityBinding>(
            this,
            R.layout.hwahae_list_activity
        )
            .apply {
                viewmodel = viewModel
            }

        initKeyboard()
        initRecyclerView()
        initObserveList()
        initNavigation()
    }


    private fun initKeyboard() {
        binding.svSearchList.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel._queryString.value = query
                viewModel.searchList(query)
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

        scrollerListener = object :
            EndlessRecyclerViewScrollListener(binding.rvHwahaeList.layoutManager as GridLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                Log.d("pagecheck", (page + 1).toString())

                viewModel.getList(null, page + 1)
            }
        }

        binding.rvHwahaeList.addOnScrollListener(scrollerListener)

    }

    private fun initObserveList() {
        viewModel.list.observe(this, Observer { list ->

            list?.let {
                if (list.isNotEmpty()) {
                    viewModel.addList(list)
                }
            }
        })
    }

    private fun initNavigation() {
        viewModel.openDetailEvent.observe(this, EventObserver {
            openDetail(it)
        })
    }

    fun openDetail(id: Int) {
        val args = Bundle()
        args.putString("id", id.toString())
        HwaHaeDetailDialogFragment.getInstance().apply {
            arguments = args
        }.show(supportFragmentManager, HwaHaeDetailDialogFragment.DIALOG_TAG)

    }

}
