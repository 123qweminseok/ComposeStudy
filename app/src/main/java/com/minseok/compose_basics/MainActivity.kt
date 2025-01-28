package com.minseok.compose_basics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.minseok.compose_basics.ui.theme.Compose_BasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Compose_BasicsTheme {
                Surface(color = MaterialTheme.colorScheme.background) {






                }
            }
        }
    }
}

@Composable
fun MyCard(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier.padding(16.dp),
        elevation = CardDefaults.cardElevation(8.dp) // Material3의 CardElevation 설정
    ) {
        content() // 전달받은 컴포저블 호출
    }
}



@Composable
fun SlotApiExample() {
    MyCard(content ={
        Text("This is dynamic content inside MyCard!") // Slot에 전달된 콘텐츠
    } )
}






//
//@Composable
//fun Text1() {
//    var text by rememberSaveable { mutableStateOf("gdgd") }
//
//    TextField(
//        value = text,
//        onValueChange = { newText ->
//            text = newText // 상태 업데이트
//            println("Current input: $newText") // 추가 동작
//        },
//        modifier = Modifier.padding(16.dp) // 여백 추가
//    )
//}
//
//@Composable
//fun FunctionA() {
//    // 부모에서 상태 관리
//    var switchState by remember { mutableStateOf(true) }
//
//    // 상태 변경 함수
//    val onSwitchChange = { value: Boolean -> switchState = value }
//
//    // 자식 컴포넌트에 상태와 이벤트 핸들러 전달
//    FunctionB(
//        switchState = switchState,
//        onSwitchChange = onSwitchChange
//    )
//}
//
//@Composable
//fun FunctionB(
//    switchState: Boolean,
//    onSwitchChange: (Boolean) -> Unit
//) {
//    Switch(
//        checked = switchState,
//        onCheckedChange = onSwitchChange
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    Compose_BasicsTheme {
//        FunctionA()
//    }
//}
//
//@Composable
//fun ParentScreen() {
//    // 1. 상태 선언 (State Hoisting)
//    var textState = remember { mutableStateOf("") }
//    /* remember: 리컴포지션 시에도 상태 유지
//       mutableStateOf: 관찰 가능한 상태 생성 */
//
//    // 2. 상태 변경 함수 정의
//    val onTextChange = { newText: String ->
//        println("부모: 텍스트 변경 감지 - $newText")
//        textState.value = newText // 상태 업데이트
//    }
//
//    // 3. UI 구성
//    Column(modifier = Modifier.padding(16.dp)) {
//        // 3-1. 상태 표시 텍스트
//        Text(
//            text = "현재 입력값: ${textState.value}",  // 현재 상태 표시
//            style = MaterialTheme.typography.bodyLarge
//        )
//
//        // 3-2. 자식 컴포넌트에 상태와 이벤트 핸들러 전달
//        ChildTextField(
//            text = textState.value,     // ↓ 상태는 아래로
//            onTextChange = onTextChange // ↑ 이벤트는 위로
//        )
//    }
//
//}
//
//// 4. 자식 컴포넌트 정의
//@Composable
//fun ChildTextField(
//    text: String,                    // 부모로부터 받는 읽기 전용 상태
//    onTextChange: (String) -> Unit   // 부모에게 변경을 알리는 콜백
//) {
//    TextField(
//        value = text,  // 부모로부터 받은 상태 표시
//        onValueChange = onTextChange,  // 변경 시 부모에게 알림
//        label = { Text("입력해주세요") }
//    )
//}
//
//// 5. 미리보기 구현
//@Preview(showBackground = true)
//@Composable
//fun ParentScreenPreview() {
//    // 테마 적용하여 전체 화면 미리보기
//    Compose_BasicsTheme {
//        ParentScreen()
//    }
//}
//
//// 6. 자식 컴포넌트 미리보기
//@Preview(showBackground = true)
//@Composable
//fun ChildTextFieldPreview() {
//    Compose_BasicsTheme {
//        // 독립적인 테스트를 위한 더미 데이터 사용
//        ChildTextField(
//            text = "미리보기 텍스트",
//            onTextChange = { /* 미리보기용 더미 함수 */ }
//        )
//    }
//}









//
//@Composable
//fun DemooScreen() {
//
//    MyTextFild()
//}
//
//@Composable
//fun MyTextFild() {
//
//var textState=remember{ mutableStateOf("")}
//    val onTextChange = {text:String-> textState.value=text}
//
//
//    TextField(value = textState.value, onValueChange = onTextChange )
//}
//
//
//
//@Preview(showBackground = true)
//@Composable
//fun MyTextFildPreview() {
//    Compose_BasicsTheme {
//        DemooScreen()
//    }
//}
//
//













//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Surface(
//        modifier = modifier.padding(1.dp)
//    ) {
//        Column(  modifier = Modifier.padding(24.dp),
//        ) {
//            Text(
//                text = "gdgd"  )
//            Text(
//                text = "name",
//            )
//            Text(
//                text = "gdgd"  )
//
//            Button(onClick = { print("ㅎㅇㅎㅇ") }) {
//
//            }
//            Button(onClick = { print("ㅎㅇㅎㅇ") }) {
//
//            }
//
//        }
//
//
//    }
//}
//@Composable
//fun OverlappingLayout() {
//    Box {
//        Text("Background Text")
//        Text("Foreground Text")
//    }
//}




