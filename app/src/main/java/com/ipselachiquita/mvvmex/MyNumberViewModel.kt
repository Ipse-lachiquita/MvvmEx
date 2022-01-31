package com.ipselachiquita.mvvmex

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class  ActionType {
    PLUS, MINUS
}

// 데이터의 변경
// 뷰모델은 데이터의 변경사항을 알려주는 라이브 데이터를 가지고 있고
class MyNumberViewModel: ViewModel() {

    companion object {
        const val TAG: String = "로그"
    }

    //뮤터블 라이브 데이터 - 변경 가능한 데이터.
    //라이브 데이터 - 값 변경이 안되고 읽기전용이라 생각하면 된다.

    private val _currentValue = MutableLiveData<Int>()

    val currentValue: LiveData<Int>
        get() = _currentValue

    //뷰모델을 생성 될 때 초기 값
    init {
        Log.d(TAG, "MyNumberViewMode - 생성자 호출")
        _currentValue.value = 0
    }

    //뷰모델이 가지고 있는 값을 변경하는 메소드
    fun updateValue(actionType: ActionType, input: Int) {
        when(actionType) {
            ActionType.PLUS -> {
                _currentValue.value = _currentValue.value?.plus(input)
            }
            ActionType.MINUS -> {
                _currentValue.value = _currentValue.value?.minus(input)
            }
        }
    }

}