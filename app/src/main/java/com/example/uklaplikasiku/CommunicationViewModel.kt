package com.example.uklaplikasiku

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CommunicationViewModel : ViewModel() {
    private val mName = MutableLiveData<String>()
    private val mEmail = MutableLiveData<String>()
    private val mUsername = MutableLiveData<String>()

    val name: LiveData<String>
    get() = mName

    val email: LiveData<String>
    get() = mEmail

    val username: LiveData<String>
    get() = mUsername

    fun setName(name : String){
        mName.value = name
    }

    fun setEmail(email: String) {
        mEmail.value = email
    }

    fun setUsername(username: String) {
        mUsername.value = username
    }
}