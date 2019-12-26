package com.project.meals.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.meals.view.search.SearchAdapter
import com.project.meals.widget.SingleLiveEvent

class SearchViewModel : ViewModel() {

    val schoolName = MutableLiveData<String>()
}