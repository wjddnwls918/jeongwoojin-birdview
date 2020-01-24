package com.example.birdview.view.index

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HwaHaeListViewModel::class.java)

        binding = DataBindingUtil.setContentView<HwahaeListActivityBinding>(
            this,
            R.layout.hwahae_list_activity
        ).apply {
                viewmodel = viewModel
        }

        binding.lifecycleOwner = this

        initKeyboard()
        initRecyclerView()
        initNavigation()
        initSpinner()
    }


    private fun initKeyboard() {
        binding.svSearchList.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel._queryString.value = query
                viewModel.searchList()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun initRecyclerView() {
        scrollerListener = object :
            EndlessRecyclerViewScrollListener(binding.rvHwahaeList.layoutManager as GridLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                viewModel.getList(page + 1)
            }
        }

        binding.rvHwahaeList.addOnScrollListener(scrollerListener)

    }

    private fun initNavigation() {
        viewModel.openDetailEvent.observe(this, EventObserver {
            openDetail(it)
        })
    }

    private fun initSpinner() {
        val items = resources.getStringArray(R.array.skin_type)
        val spinnerAdapter =
            ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, items)

        binding.listSpinner.adapter = spinnerAdapter

        binding.listSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {

                    0 -> {
                        viewModel._skinType.value = 0
                    }
                    1 -> {
                        viewModel._skinType.value = 1
                    }
                    2 -> {
                        viewModel._skinType.value = 2
                    }
                    else -> {
                        viewModel._skinType.value = 3
                    }

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }

    }

    fun openDetail(id: Int) {
        val args = Bundle()
        args.putString("id", id.toString())
        HwaHaeDetailDialogFragment.getInstance().apply {
            arguments = args
        }.show(supportFragmentManager, HwaHaeDetailDialogFragment.DIALOG_TAG)

    }

}
