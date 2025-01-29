package com.minseok.compose_basics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.dp
import com.minseok.compose_basics.ui.theme.Compose_BasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_BasicsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CustomLayout(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        // 배치할 컴포저블들
                        Text("첫 번째")
                        Text("두 번째")
                    }                }
            }
        }
    }
}
@Composable
fun CustomLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        // 1. 자식들 측정
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }

        // 2. 레이아웃 크기 결정
        val width = placeables.maxOf { it.width }
        val height = placeables.sumOf { it.height }

        // 3. 자식들 배치
        layout(width, height) {
            var y = 0
            placeables.forEach { placeable ->
                placeable.placeRelative(x = 0, y = y)
                y += placeable.height
            }
        }
    }
}














//@Composable
//fun MyLayout() {  // 컴포저블 함수를 만들고
//    Column(  // 그 안에서 Column 사용
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text("상단 텍스트", fontSize = 24.sp)
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 16.dp),
//            horizontalArrangement = Arrangement.SpaceEvenly
//        ) {
//            Column(
//            ) {
//                Text("왼쪽")
//                Text("항목")
//            }
//
//            Column(
//            ) {
//                Text("오른쪽")
//                Text("항목")
//            }
//        }
//
//        Text("하단 텍스트", fontSize = 24.sp)
//    }
//}
//
//
//
//
//
//
//
//
////
////@Composable
////fun TextCell(text: String, modifier: Modifier = Modifier) {
////    val cellModifier = Modifier.padding(4.dp)
////
////    Text(
////        text = text,
////        modifier = cellModifier.then(modifier),//모디파이어 연결. 근데 호출시 아무것도 안넘기면 걍 빈 객체만 들어와서 의미없음
////        fontSize = 70.sp
////    )
////}
////
//
//// Modifier 객체를 전역 변수로 선언
//// Border를 가진 Modifier 객체를 생성
////var a = Modifier.border(width = 2.dp, color = Color.Black)

//@Composable
//fun DemoScreen() {
//    // UI를 세로로 배치하기 위한 Column 컴포저블
//    Column(
//        modifier = Modifier
//            .padding(20.dp) // Column의 외부에 20dp 패딩 추가
//            .border(width = 8.dp, color = Color.Black) // Column에 테두리 추가
//            .padding(all = 10.dp), // Column의 내부에 10dp 패딩 추가
//        horizontalAlignment = Alignment.CenterHorizontally, // 자식들을 수평으로 중앙 정렬
//        verticalArrangement = Arrangement.SpaceBetween // 자식들 사이를 고르게 배치
//    ) {
//        // 텍스트 컴포저블: "Hello"를 출력
//        Text(
//            text = "Hello", // 출력할 텍스트
//            fontSize = 40.sp, // 글자 크기 설정
//            fontWeight = FontWeight.Bold // 글자 굵기 설정
//        )
//
////        // Spacer: 두 컴포저블 간에 공간을 추가
////        Spacer(modifier = Modifier.height(40.dp)) // 높이 16dp의 빈 공간 추가
//
//        // CustomImage 컴포저블 호출, 이미지를 표시
//        CustomImage(
//            image = R.drawable.ic_launcher_background // 이미지 리소스
////            modifier = a // 위에서 선언한 Modifier를 전달
//        )
//    }
//}
//
//// CustomImage: 이미지를 표시하는 컴포저블 함수
//@Composable
//fun CustomImage(image: Int, modifier: Modifier=Modifier) {
//    Image(
//        painter = painterResource(image), // 리소스 ID를 기반으로 이미지를 로드
//        contentDescription = null, // 이미지 설명 (접근성을 위한 설명, 여기선 생략)
//        modifier = modifier // 전달받은 Modifier 적용
//    )
//}
//


//
//var modi=Modifier.padding(all=10.dp).border(width=2.dp,color=Black)
//
//@Preview
//@Composable
//fun DemoScreen() {
//    Column {
//
//
//    Text(
//         "Hellow Compose", // 텍스트 내용
//        fontSize = 40.sp, // 글자 크기 설정 (대소문자 정확히 구분)
//        fontWeight = FontWeight.Bold, // 글자 굵기 설정
//        modifier = modi
//        )
//
//    }
//}
//
//
//@Composable
//fun CustomText(
//    text: String,
//    modifier: Modifier  // 기본값 지정
//) {
//    Text(
//        text = text,
//        modifier = modifier
//    )
//}
//
//
//






//// 화면 레이아웃을 구성하는 컴포저블 함수
//@Composable
//fun ScreenContent(
//    linearSelected: Boolean, // 리니어 체크박스 상태
//    imageSelected: Boolean, // 이미지 체크박스 상태
//    onTitleClick: (Boolean) -> Unit, // 이미지 클릭 콜백
//    onLinearClick: (Boolean) -> Unit, // 리니어 클릭 콜백
//    titleContent: @Composable () -> Unit, // 제목 부분 콘텐츠
//    progressContent: @Composable () -> Unit // 프로그레스 바 부분 콘텐츠
//) {
//    Column(
//        modifier = Modifier.fillMaxSize(), // 화면 전체를 채우도록 설정
//        horizontalAlignment = Alignment.CenterHorizontally, // 콘텐츠를 수평 중앙 정렬
//        verticalArrangement = Arrangement.SpaceBetween // 콘텐츠 간격을 위아래로 골고루 배치
//    ) {
//        titleContent() // 제목 콘텐츠 표시
//        progressContent() // 프로그레스 콘텐츠 표시
//        CheckBoxes(linearSelected, imageSelected, onTitleClick, onLinearClick) // 체크박스 표시
//    }
//}
//
//// 체크박스 UI를 구성하는 컴포저블 함수
//@Composable
//fun CheckBoxes(
//    linearSelected: Boolean, // 리니어 체크박스 상태
//    imageSelected: Boolean, // 이미지 체크박스 상태
//    onTitleClick: (Boolean) -> Unit, // 이미지 체크박스 클릭 콜백
//    onLinearClick: (Boolean) -> Unit // 리니어 체크박스 클릭 콜백
//) {
//    Row(Modifier.padding(20.dp)) { // 가로 정렬 + 20dp의 패딩 추가
//        Checkbox(
//            checked = imageSelected, // 이미지 체크박스 상태
//            onCheckedChange = onTitleClick // 이미지 클릭 시 호출되는 콜백
//        )
//        Text("Image Title") // 이미지 체크박스 옆의 텍스트
//        Spacer(Modifier.width(20.dp)) // 체크박스 간의 간격을 20dp로 설정
//        Checkbox(
//            checked = linearSelected, // 리니어 체크박스 상태
//            onCheckedChange = onLinearClick // 리니어 클릭 시 호출되는 콜백
//        )
//        Text("Linear Progress") // 리니어 체크박스 옆의 텍스트
//    }
//}
//
//// 제목에 이미지를 표시하는 컴포저블 함수
//@Composable
//fun TitleImage(drawing: Int) {
//    Image(
//        painter = painterResource(drawing), // 리소스에서 이미지를 불러옴
//        contentDescription = "title image" // 접근성 텍스트 설정
//    )
//}
//
//// 디자인 화면에서 미리보기 기능을 제공하는 컴포저블 함수
//@Preview(showSystemUi = true) // 시스템 UI를 포함한 화면을 미리보기로 표시
//@Composable
//fun DemoPreview() {
//    MainScreen() // MainScreen 컴포저블 호출
//}

// 화면의 메인 내용을 구성하는 컴포저블 함수
//@Composable
//fun MainScreen() {
//    // 체크박스 상태를 저장하는 변수 (리니어 프로그레스 선택 여부)
//    var linearSelected by remember { mutableStateOf(true) }
//    // 체크박스 상태를 저장하는 변수 (이미지 선택 여부)
//    var imageSelected by remember { mutableStateOf(true) }
//
//    // 리니어 프로그레스 체크박스를 클릭했을 때 호출되는 콜백 함수
//    val onLinearClick = { value: Boolean ->
//        linearSelected = value // linearSelected 값을 업데이트
//    }
//
//    // 이미지 체크박스를 클릭했을 때 호출되는 콜백 함수
//    val onTitleClick = { value: Boolean ->
//        imageSelected = value // imageSelected 값을 업데이트
//    }
//
//    // 화면의 콘텐츠를 구성하는 ScreenContent 컴포저블 호출
//    ScreenContent(
//        linearSelected = linearSelected, // 리니어 체크박스 상태 전달
//        imageSelected = imageSelected, // 이미지 체크박스 상태 전달
//        onLinearClick = onLinearClick, // 리니어 클릭 콜백 전달
//        onTitleClick = onTitleClick, // 이미지 클릭 콜백 전달
//        titleContent = { // 제목 부분에 표시할 콘텐츠 정의
//            if (imageSelected) {
//                // 이미지가 선택된 경우 이미지를 표시
//                TitleImage(drawing = R.drawable.ic_baseline_cloud_download_24)
//            } else {
//                // 이미지가 선택되지 않은 경우 텍스트를 표시
//                Text(
//                    "Downloading",
//                    modifier = Modifier.padding(30.dp) // 텍스트 주변에 30dp 패딩 추가
//                )
//            }
//        },
//        progressContent = { // 프로그레스 바 부분에 표시할 콘텐츠 정의
//            if (linearSelected) {
//                // 리니어 프로그레스 바가 선택된 경우
//                LinearProgressIndicator(Modifier.height(40.dp)) // 높이를 40dp로 설정
//            } else {
//                // 원형 프로그레스 바가 선택된 경우
//                CircularProgressIndicator(
//                    Modifier.size(200.dp), // 크기를 200dp로 설정
//                    strokeWidth = 18.dp // 선의 두께를 18dp로 설정
//                )
//            }
//        }
//    )
//}
//









//@Composable
//fun MyCard(content: @Composable () -> Unit) {
//    Card(
//        modifier = Modifier.padding(16.dp),
//        elevation = CardDefaults.cardElevation(8.dp) // Material3의 CardElevation 설정
//    ) {
//        content() // 전달받은 컴포저블 호출
//    }
//}
//
//
//
//@Composable
//fun SlotApiExample() {
//    MyCard(content ={
//        Text("This is dynamic content inside MyCard!") // Slot에 전달된 콘텐츠
//    } )
//}





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




