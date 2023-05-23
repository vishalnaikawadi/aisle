package com.vmn.aisle.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vmn.aisle.home.model.NotesBindingModel
import com.vmn.domain.entity.NotesEntity
import com.vmn.domain.usecase.NotesUseCase
import com.vmn.domain.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotesViewModel(
    private val useCase: NotesUseCase
) : ViewModel() {

    val bmNotes = NotesBindingModel()

    private var _details = MutableLiveData<Resource<NotesEntity?>?>()
    val details: LiveData<Resource<NotesEntity?>?>
        get() = _details

    fun getNotesDetails() {
        CoroutineScope(Dispatchers.IO).launch {
            showProgress()
            val response = useCase.getNotesDetails("")

            withContext(Dispatchers.Main) {
                hideProgress()
                when (response) {
                    is Resource.Success -> {
                        _details.postValue(Resource.Success(response.data))
                    }

                    is Resource.Error -> {
                        _details.postValue(Resource.Error(response.errorMessage))
                    }

                    Resource.NoInternet -> {
                        _details.postValue(Resource.NoInternet)
                    }

                    Resource.Unknown -> {
                        _details.postValue(Resource.Unknown)
                    }
                }
            }
        }
    }

    private fun showProgress() {
        bmNotes.showProgress = true
        bmNotes.showInitialLayouts = false
    }

    private fun hideProgress() {
        bmNotes.showProgress = false
        bmNotes.showInitialLayouts = true
    }
}