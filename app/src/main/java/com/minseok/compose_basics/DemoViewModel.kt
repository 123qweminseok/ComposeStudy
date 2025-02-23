package com.minseok.compose_basics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.roundToInt

// ViewModel: Compose/Activity/Fragment가 재생성되더라도 데이터 유지 가능
class DemoViewModel : ViewModel() {

    // 내부에서 쓸 MutableLiveData, 외부에서는 LiveData만 노출해 캡슐화
    private val _isFahrenheit = MutableLiveData(true)
    val isFahrenheit: LiveData<Boolean> get() = _isFahrenheit

    private val _result = MutableLiveData("")
    val result: LiveData<String> get() = _result

    // 온도 변환 함수
    fun convertTemp(temp: String) {
        try {
            // 입력 문자열을 정수로 변환
            val tempInt = temp.toInt()

            // _isFahrenheit.value == true → 화씨를 섭씨로, false → 섭씨를 화씨로
            _result.value = if (_isFahrenheit.value == true) {
                ((tempInt - 32) * 0.5556).roundToInt().toString()
            } else {
                ((tempInt * 1.8) + 32).roundToInt().toString()
            }
        } catch (e: Exception) {
            // 숫자가 아닐 경우 처리
            _result.value = "Invalid Entry"
        }
    }

    // 스위치 값을 반전(화씨 ↔ 섭씨)
    fun switchChange() {
        _isFahrenheit.value = _isFahrenheit.value?.not()
    }
}
