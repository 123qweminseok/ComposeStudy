package com.minseok.compose_basics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.minseok.compose_basics.ui.theme.Compose_BasicsTheme

class MainActivity : ComponentActivity() {
    private var itemArray: Array<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemArray = resources.getStringArray(R.array.car_array)

        setContent {
            Compose_BasicsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AnimatedVisibilityAdvancedExample()                }
            }




        }
    }
}

@Composable
fun AnimatedVisibilityAdvancedExample() {
    // 애니메이션 효과를 위한 상태 변수 (true → 나타남, false → 사라짐)
    var isVisible by remember { mutableStateOf(false) }

    // 수직 정렬된 UI 배치를 위한 Column 사용
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        // 버튼을 눌러서 isVisible 값을 변경 (true ↔ false)
        Button(onClick = { isVisible = !isVisible }) {
            // 버튼의 텍스트는 isVisible 값에 따라 변경됨
            Text(text = if (isVisible) "Collapse" else "Expand")
        }

        // 버튼과 애니메이션 박스 사이 간격 추가
        Spacer(modifier = Modifier.height(16.dp))

        // AnimatedVisibility: 조건(isVisible)에 따라 애니메이션 적용하여 박스를 표시/숨김
        AnimatedVisibility(
            visible = isVisible, // isVisible이 true면 나타남, false면 사라짐
            enter = fadeIn(animationSpec = tween(500)) // 500ms 동안 서서히 나타남
                    + expandVertically(expandFrom = Alignment.Top), // 위에서 아래로 확장됨
            exit = fadeOut(animationSpec = tween(500)) // 500ms 동안 서서히 사라짐
                    + shrinkVertically(shrinkTowards = Alignment.Bottom) // 아래 방향으로 작아짐
        ) {
                // 박스 내부에 표시될 텍스트
                Text(
                    text = "Animated Box!", // 고정된 텍스트
                    color = Color.Blue,
                    style = MaterialTheme.typography.headlineSmall // 텍스트 스타일 (크기, 굵기 등)
                )

        }
    }
}

























//@Composable
//fun MainScreen(itemArray: Array<String>) {
//    val context = LocalContext.current
//    val onListItemClick: (String) -> Unit = { text ->
//        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
//    }
//
//    LazyColumn {
//        items(itemArray) { model ->
//            MyListItem(item = model, onItemClick = onListItemClick)
//        }
//    }
//}
//
//@Composable
//fun MyListItem(item: String, onItemClick: (String) -> Unit) {
//    Card(
//        modifier = Modifier
//            .padding(8.dp)
//            .fillMaxWidth()
//            .clickable { onItemClick(item) },
//        shape = RoundedCornerShape(10.dp),
//        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
//    ) {
//        Row(
//            modifier = Modifier.padding(8.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            ImageLoader(item)
//            Spacer(modifier = Modifier.width(8.dp))
//            Text(
//                text = item,
//                style = MaterialTheme.typography.headlineSmall
//            )
//        }
//    }
//}
//
//@Composable
//fun ImageLoader(item: String) {
//    val url = "https://www.ebookfrenzy.com/book_examples/car_logos/" +
//            item.substringBefore(" ").lowercase() + "_logo.png"
//
//    AsyncImage(
//        model = url,
//        contentDescription = "$item logo",
//        modifier = Modifier.size(60.dp),
//        contentScale = ContentScale.Fit
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    val itemArray: Array<String> = arrayOf(
//        "Cadillac Eldorado",
//        "Ford Fairlane",
//        "Plymouth Fury"
//    )
//    Compose_BasicsTheme {
//        MainScreen(itemArray = itemArray)
//    }
//}

//@Preview
//@Composable
//fun MyListScreen() {
//    val items = listOf("사과", "바나나", "오렌지", "포도", "키위")
//
//    LazyColumn {
//        itemsIndexed(items) { index, item ->
//            Text(
//                text = "번호: $index, 과일: $item",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//            )
//        }
//    }
//}
//
////
//@Preview
//@Composable
//fun ScrollExample() {
//    // LazyListState를 remember로 기억
//    val listState = rememberLazyListState()
//    val coroutineScope = rememberCoroutineScope()
//
//    Column {
//        // 스크롤 버튼들
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            horizontalArrangement = Arrangement.SpaceEvenly
//        ) {
//            // 즉시 스크롤
//            Button(onClick = {
//                coroutineScope.launch {
//                    listState.scrollToItem(50)  // 50번 아이템으로 즉시 이동
//                }
//            }) {
//                Text("50번으로 즉시 이동")
//            }
//
//            // 애니메이션 스크롤
//            Button(onClick = {
//                coroutineScope.launch {
//                    listState.animateScrollToItem(50)  // 50번 아이템으로 애니메이션 이동
//                }
//            }) {
//                Text("50번으로 애니메이션 이동")
//            }
//        }
//
//        // 리스트
//        LazyColumn(
//            state = listState  // LazyListState 연결
//        ) {
//            items(100) { index ->
//                Text(
//                    text = "아이템 $index",
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp)
//                )
//            }
//        }
//    }
//}




//
//fun myFunction1(
//    name: String,
//    action: (String) -> Unit,
//    age: Int
//) {
//    action(name)  // 함수 구현부 추가
//}
//@Preview
//@Composable
//fun MyListScreen2() {
//    // 여기서 함수 호출 가능
//    myFunction1("철수") { name -> println(name) }
//
//    LazyColumn {
//        items(1000) { index ->
//            Text(
//                text = "gdgd $index",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//            )
//        }
//    }
//}
//
//
//@Preview
//@Composable
//fun MyListScreen() {  // 컴포저블 함수 정의 필요
//    LazyColumn {
//        items(1000) { index ->
//            Text(
//                text = "gdgd $index",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//            )
//        }
//    }
//}



//
////네, 맞습니다. 스크롤이 불가능한 일반 Column을 사용했기
//// 때문에 화면 크기를 넘어가는 내용은 짤리게 됩니다. 스크롤이 가능하도록 만들기 위해서는 Column을 verticalScroll 수정자로 감싸주어야 합니다.
//@Preview(showBackground = true)
//@Composable
//fun MyListScreen() {
//    Column {
//        repeat(121) {
//            MyListItem()
//        }
//    }
//}
////verticalScroll 수정자와 rememberScrollState()를 추가하면 스크롤이 가능한 리스트가 됩니다. 이제 121개의 아이템이 모두 스크롤로 보일 수 있습니다.
//
//@Composable
//fun MyListItem() {
//    // 리스트 아이템의 내용을 정의
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//    ) {
//        Text(text = "아이템 내용")
//    }
//}
//
////}
//
//@Composable
//fun MyComposable() {
//    val coroutineScope = rememberCoroutineScope()
//
//    Button(
//        onClick = {
//            coroutineScope.launch { perform() }
//        }
//    ) {
//        Column { // ✅ 여러 UI 요소를 포함하기 위해 Column 사용
//            Text("Click Me")
//            Text("버튼 설명 텍스트") // ✅ 버튼 내부에 추가적인 설명을 포함 가능
//        }
//    }
//}
//
//suspend fun perform() {
//    // 비동기 작업 수행
//}

//@Composable
//fun MyComposable() {
//    LaunchedEffect(Unit) {
//        println("현재 스레드: ${Thread.currentThread().name}")
//        delay(3000)
//        println("Composable이 살아있는 동안만 실행됨")
//    }
//}

//@Composable
//fun MyComposable() {
//    val coroutineScope = rememberCoroutineScope()
//
//    Button(onClick = {
//        println("버튼 클릭됨! 코루틴 실행 시작")  // 버튼 클릭 시 즉시 로그 출력
//
//        coroutineScope.launch {
//            delay(3000)
//            println("버튼 클릭 후 3초 후 실행됨")
//        }
//
//        println("버튼 클릭 후 launch 이후 즉시 실행")  // 코루틴이 시작된 후 즉시 실행
//    }) {
//        Text("클릭 후 3초 대기")
//    }
//}

//
//@Preview
//@Composable
//fun MainScreen() {
//    Row(
//        Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//    ) {
//        // MIN 예시
//        Column(
//            Modifier
//                .background(Color.LightGray)
//                .width(IntrinsicSize.Min)
//            //**IntrinsicSize.Min**은 자식들 중 가장 좁은 너비를 기준으로 부모의 너비를 결정합니다.
//            //"Hi"가 가장 좁은 텍스트이므로, 이 텍스트의 최소 너비에 맞춰 Column의 너비가 설정됩니다.
//        //부모의 너비는 자식 중 가장 좁은 요소에 맞춰지긴 하지만, 부모의 크기 설정은 다른 자식 컴포저블들의 크기와 관계 없이 독립적으로 작동하지 않습니다.
//            //"Hi"라는 텍스트가 가장 좁은 요소라 하더라도, 다른 자식 컴포저블의 크기에 따라 부모가 조금 더 넓게 설정될 수 있습니다.
//
//        ) {
//            Text("Min 컬럼")
//            Text("Hi")
//            Text("Hello World")
//            Box(
//                Modifier
//                    .height(10.dp)
//                    .width(50.dp)  // 더 작은 너비로 변경
//                    .background(Color.Blue)
//            )
//        }
//
//        Spacer(Modifier.width(16.dp))
//
//        // MAX 예시
//        Column(
//            Modifier
//                .background(Color.LightGray)
//                .width(IntrinsicSize.Max)
//        ) {
//            Text("Max 컬럼")
//            Text("Hi")
//            Text("Hello World")
//            Box(
//                Modifier
//                    .height(10.dp)
//                    .width(50.dp)  // 더 작은 너비로 변경
//                    .background(Color.Blue)
//            )
//        }
//    }
//}

//
///**
// * 메인 화면의 UI를 구성하는 Composable 함수
// * 텍스트 입력과 표시를 담당하는 UI 요소들을 포함
// *
// * 특징:
// * - 상태 관리: mutableStateOf를 사용해 텍스트 상태 관리
// * - 레이아웃: Column을 사용해 수직으로 요소들을 배치
// * - IntrinsicSize: 내부 컨텐츠의 크기에 맞춰 자동으로 크기 조절
// */
//@Composable
//fun MainScreen() {
//    // remember: 리컴포지션 시에도 상태를 유지
//    // mutableStateOf: 관찰 가능한 상태 생성
//    var textState by remember { mutableStateOf("") }
//
//    // 텍스트 변경 이벤트 핸들러
//    val onTextChange = { text: String ->
//        textState = text
//    }
//
//    // 최상위 Column - 전체 컨텐츠를 수직으로 배치
//    Column(
//        Modifier
//            .padding(5.dp) // 전체 패딩 적용
//    ) {
//        // 내부 Column - 텍스트와 구분선을 포함
//        Column(Modifier.width(IntrinsicSize.Min)) { // 내용물의 최소 너비에 맞춤
//            // 입력된 텍스트를 표시
//            Text(
//                modifier = Modifier.padding(start = 4.dp),
//                text = textState
//            )
//            // 파란색 구분선
//            Box(
//                Modifier
//                    .height(10.dp)
//                    .fillMaxWidth() // 부모의 너비를 채움
//                    .background(Color.Blue)
//            )
//        }
//        // 텍스트 입력 필드
//        MyTextField(text = textState, onTextChange = onTextChange)
//    }
//}
//
///**
// * 그니까 지금 텍스트 입력하면 상태가 바뀌고 그렇지? 부모->자식으로 전달해줬으니까
// * 상태가 바뀌니까 리컴포지션 즉 재구성 일어나잖아
// * 그럼 이제 다시 위에서부터 아래로 재구성 일어나고 ㅇㅇ 데이터 흐름은 아래에서 위인데,
//  * 솔직히  데이터 흐름까지 파악할 필요는 없고, 상태가 바뀌었으니 위에서 아래로 UI렌더링 다시 되는거잖아.
// *상태가 변경되면 UI가 위에서 아래로 다시 렌더링된다"는 흐름만 이해하면 충분합니다. Compose가 복잡한 내부 처리를 모두 해결해주자나!
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// * 재사용 가능한 텍스트 입력 필드 컴포넌트
// *
// * @param text 현재 입력된 텍스트
// * @param onTextChange 텍스트 변경 시 호출될 콜백 함수
// *
// * 특징:
// * - Material3 디자인의 TextField 사용
// * - 상태 끌어올리기(State Hoisting) 패턴 적용 ->이 뭔 소린지는 모르겠고 걍 부모-자식 관계로 위에서 아래로 내려간다~ 라고 알면 됨 ㅇㅇ
// */
//@Composable
//fun MyTextField(text: String, onTextChange: (String) -> Unit) {
//    TextField(
//        value = text, // 현재 텍스트 값
//        onValueChange = onTextChange // 텍스트 변경 콜백
//    )
//}
//
///**
// * 미리보기를 위한 Composable
// * Android Studio의 미리보기 창에서 UI를 확인 가능
// *
// * @Preview 특징:
// * - showBackground = true: 배경 표시
// * - 실제 디바이스 없이도 UI 확인 가능
// * - 빠른 UI 반복 작업 가능
// */
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    Compose_BasicsTheme {
//        MainScreen()
//    }
//}
//
///* 주요 개념 설명:

//1. Modifier
//  - Compose의 UI 요소를 수정하는 데 사용되는 객체
//  - 크기, 패딩, 배경 등 다양한 속성 설정 가능
//  - 체이닝을 통해 여러 수정자를 연결 가능
//
//2. Column
//  - 자식 요소들을 수직으로 배치하는 레이아웃
//  - LinearLayout vertical과 유사한 역할
//
//3. IntrinsicSize
//  - 컨텐츠의 본질적인 크기에 맞춰 크기를 조절
//  - Min: 최소 필요 크기로 조절
//
//4. State Management
//  - mutableStateOf: 관찰 가능한 상태 생성
//  - remember: 리컴포지션 간 상태 보존
//  - by 델리게이트를 통한 간편한 상태 접근
//
//5. Composable
//  - @Composable 어노테이션이 붙은 함수
//  - 선언적 UI를 구성하는 기본 단위
//  - 재사용 가능한 UI 컴포넌트 생성 가능
//*/








//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun PreviewConstraintLayout() { //안쓴다 ㄹㅇ 쓸모없네 여기선. 하나하나 설정해줘야하고. 써야할 이유가 없어보인다.
//    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
//        val textRef = createRef()
//
//        Text(
//            text = "Hello, ConstraintLayout!",
//            modifier = Modifier.constrainAs(textRef) {
//                start.linkTo(parent.start) // 가로 방향 제약
//                top.linkTo(parent.top)    // 세로 방향 제약
//                bottom.linkTo(parent.bottom)
//            }
//        )
//    }
//}
//

















//@Composable
//fun CustomLayout(
//    modifier: Modifier = Modifier,
//    content: @Composable () -> Unit
//) {
//    Layout(
//        modifier = modifier,
//        content = content
//    ) { measurables, constraints ->
//        // 1. 자식들 측정
//        val placeables = measurables.map { measurable ->
//            measurable.measure(constraints)
//        }
//
//        // 2. 레이아웃 크기 결정
//        val width = placeables.maxOf { it.width }
//        val height = placeables.sumOf { it.height }
//
//        // 3. 자식들 배치
//        layout(width, height) {
//            var y = 0
//            placeables.forEach { placeable ->
//                placeable.placeRelative(x = 0, y = y)
//                y += placeable.height
//            }
//        }
//    }
//}
//
//
//
//
//









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




