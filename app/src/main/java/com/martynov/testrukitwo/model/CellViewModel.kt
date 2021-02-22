package com.martynov.testrukitwo.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CellViewModel: ViewModel() {
    var cellList : MutableLiveData<List<Cell>> = MutableLiveData()

    fun getListCell() = cellList

    fun updateListCell(list : ArrayList<Cell>) {
        cellList.value = list

    }
}