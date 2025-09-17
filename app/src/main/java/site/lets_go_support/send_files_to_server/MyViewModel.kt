package site.lets_go_support.send_files_to_server

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MyViewModel(application: Application): AndroidViewModel(application) {

    private val _returnValue: MutableLiveData<Int> = MutableLiveData()
    val returnValue: LiveData<Int> = _returnValue

    private val _exceptionValue: MutableLiveData<Int> = MutableLiveData()
    val exceptionValue: LiveData<Int> = _exceptionValue


    fun setLiveData() {
        CoroutineScope(IO).launch {
            crashing()
        }
    }

    private suspend fun crashing() {
        withContext(IO) {
            maybeCrashing()
//            try {
//                _returnValue.value = 1
//            }
//            catch (e: Exception) {
//                withContext(Main) {
//                    _exceptionValue.value = 1
//                }
//            }
        }
    }

    private fun maybeCrashing() {
        try {
            _returnValue.value = 1
        }
        catch (e: Exception) {
            CoroutineScope(Main).launch {
                _exceptionValue.value = 1
            }
        }
    }
}