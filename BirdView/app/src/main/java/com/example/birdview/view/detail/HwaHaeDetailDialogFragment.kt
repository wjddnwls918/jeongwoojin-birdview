package com.example.birdview.view.detail

import android.app.Dialog
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.birdview.R
import com.example.birdview.common.EventObserver
import com.example.birdview.databinding.HwahaeDetailDialogBinding

class HwaHaeDetailDialogFragment : DialogFragment() {

    private lateinit var binding: HwahaeDetailDialogBinding

    private lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory
    private lateinit var viewModel: HwaHaeDetailViewModel

    companion object {
        val DIALOG_TAG = "dialog"
        fun getInstance(): HwaHaeDetailDialogFragment {
            return HwaHaeDetailDialogFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dialog?.window?.apply {
            setGravity(Gravity.BOTTOM)
            attributes.windowAnimations = R.style.DialogAnimation
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        viewModelFactory =
            ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HwaHaeDetailViewModel::class.java)

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.hwahae_detail_dialog,
            container,
            false
        )
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val mArgs = arguments
        viewModel.getDetail(mArgs?.getString("id")?.toInt())

        initNavigation()
    }

    override fun onResume() {

        val display = dialog?.window?.windowManager?.defaultDisplay
        val size = Point()
        display?.getSize(size)

        val window = dialog?.window

        val x = size.x
        val y = (size.y * 0.9).toInt()

        window?.setLayout(x, y)

        super.onResume()
    }

    private fun initNavigation() {
        viewModel.closeDetailEvent.observe(viewLifecycleOwner, EventObserver {
            dismiss()
        })
    }

}