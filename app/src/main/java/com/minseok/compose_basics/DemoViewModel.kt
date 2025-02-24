package com.minseok.compose_basics

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

// ViewModel: Compose/Activity/Fragment가 재생성되더라도 데이터 유지 가능
class DemoViewModel : ViewModel() {

    // Flow를 사용해 값 방출 (0부터 9까지)
    val myFlow: Flow<Int> = flow {
        for (i in 0..9) {
            emit(i)  // 데이터를 방출
            delay(2000)  // 2초 지연
        }
    }

    val newFlow=myFlow.map{"Current value=$it"}

}
//Flow는 상태를 저장하지 않고 값을 방출하는 흐름만을 제공합니다. 그래서 화면 회전으로 Activity나 Composable이 재생성되면, Flow의 흐름도 다시 처음부터 시작됩니다. 그래서 화면 회전무관하게 그냥 초기화 됨.
//원래 뷰모델에는 값을 저장해야 유지가 되는데, 방출만 하니까 그렇구나







//    // 내부에서 쓸 MutableLiveData, 외부에서는 LiveData만 노출해 캡슐화
//    private val _isFahrenheit = MutableLiveData(true)
//    val isFahrenheit: LiveData<Boolean> get() = _isFahrenheit
//
//    private val _result = MutableLiveData("")
//    val result: LiveData<String> get() = _result
//
//    // 온도 변환 함수
//    fun convertTemp(temp: String) {
//        try {
//            // 입력 문자열을 정수로 변환
//            val tempInt = temp.toInt()
//
//            // _isFahrenheit.value == true → 화씨를 섭씨로, false → 섭씨를 화씨로
//            _result.value = if (_isFahrenheit.value == true) {
//                ((tempInt - 32) * 0.5556).roundToInt().toString()
//            } else {
//                ((tempInt * 1.8) + 32).roundToInt().toString()
//            }
//        } catch (e: Exception) {
//            // 숫자가 아닐 경우 처리
//            _result.value = "Invalid Entry"
//        }
//    }
//
//    // 스위치 값을 반전(화씨 ↔ 섭씨)
//    fun switchChange() {
//        _isFahrenheit.value = _isFahrenheit.value?.not()
//    }
//}
