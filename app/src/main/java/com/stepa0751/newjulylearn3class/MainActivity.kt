package com.stepa0751.newjulylearn3class

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.stepa0751.newjulylearn3class.databinding.ActivityMainBinding
import com.stepa0751.newjulylearn3class.models.DataModel
import com.stepa0751.newjulylearn3class.models.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var mViewModel: MainViewModel
    private var id1 = false
    private var id2 = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setView()
        setOnClicks()

    }

    fun setView() {
        mViewModel.dataLiveData.observe(this, Observer {
            id1 = it.one
            id2 = it.two



        })

    }


    fun setOnClicks() = with(binding) {
        val listener = onClicks()
    }

    private fun onClicks(): View.OnClickListener {
        return View.OnClickListener {
            when (it.id) {


            }
        }
    }

    override fun onPause() {
        super.onPause()


        val items = DataModel(id1, id2)
        mViewModel.dataLiveData.value = items
    }

}