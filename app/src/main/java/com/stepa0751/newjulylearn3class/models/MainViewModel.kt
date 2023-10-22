package com.stepa0751.newjulylearn3class.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    val dataLiveData = MutableLiveData<DataModel>()
}