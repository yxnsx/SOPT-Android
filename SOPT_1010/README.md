## :fire: 1주차 세미나: View와 ViewGroup
#### :heavy_check_mark: 과제 완료일: 20/10/12
<img width="1154" alt="스크린샷 2020-10-12 오후 11 20 20" src="https://user-images.githubusercontent.com/47806943/95757010-872d4d00-0ce1-11eb-920b-1a9b09779a53.png">

#### :heavy_check_mark: 필수 과제: `SignUpActivity` 만들기
* 회원가입 완료 클릭 이벤트 구현하기
* 모든 `EditText`에 데이터가 있을 경우 -> 회원가입 완료 `Toast` 메시지 띄우기
* 하나라도 없을 경우 -> 빈 칸이 있다는 `Toast` 메시지 띄우기
* 비밀번호 `EditText`의 입력 내용 숨기기
* 모든 `EditText`에 Hint 넣기
<br>

`SignUpActivity.kt`
```kotlin
private val onClickListener = View.OnClickListener() {
    when (it) {
        button_signUp -> {
            // 폼 입력상태 체크
            checkValidation()
        }
    }
}

fun checkValidation() {
    // 모든 폼이 입력되지 않았을 경우,
    if (editText_name.text.isEmpty() || editText_id.text.isEmpty() || editText_password.text.isEmpty()) {
        // 토스트 메시지 츨력
        Toast.makeText(this, "모든 폼을 입력해주세요.", Toast.LENGTH_SHORT).show()

    } else { // 모든 폼이 입력되었을 경우,
        // 토스트 메시지 츨력
        Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
        
        // LogInActivity로 되돌아가는 인텐트 설정
        val intent = Intent(this, LogInActivity::class.java)
        
        // 인텐트로 넘길 데이터 설정
        intent.putExtra("userName", editText_name.text.toString())
        intent.putExtra("userId", editText_id.text.toString())
        intent.putExtra("userPassword", editText_password.text.toString())
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
```
<br>

#### :heavy_check_mark: 성장 과제 1: 화면 이동 + @
* 회원 가입 성공시 이전 로그인 화면으로 돌아오기
* 회원 가입시 입력한 아이디와 비밀번호가 입력되어 있도록 하기
<br>

`LogInActivity.kt`
```kotlin
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    if (resultCode == RESULT_OK) {
        when (requestCode) {

            REQUEST_SIGNUP -> {
                // 넘어온 인텐트에서 데이터 가져오기
                val userId = data?.getStringExtra("userId")
                val userPassword = data?.getStringExtra("userPassword")

                // 가져온 데이터 바탕으로 editText에 값 설정
                editText_id.setText(userId)
                editText_password.setText(userPassword)
            }
        }
    }
}
```
<br>

#### :heavy_check_mark: 성장 과제 2: 자동 로그인
* 로그인 버튼을 누르면 `HomeActivity`로 이동하기
* 로그인에 성공하는 순간 ID와 Password를 기억해서 다음 로그인 때 자동 로그인 되도록 하기
* 자동 로그인이 될 경우 `Toast` 메시지 출력하기
<br>

`LogInActivity.kt`
```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_log_in)

    // 버튼 클릭리스너 설정
    button_logIn.setOnClickListener(onClickListener)
    button_signUp.setOnClickListener(onClickListener)

    // SharedPreferences 값 체크 후 저장된 userId 값이 있으면 자동로그인
    checkSharedPreferences()
}

private val onClickListener = View.OnClickListener() {
    when (it) {
        button_logIn -> {
            // 폼 입력상태 체크
            checkValidation()
        }
        button_signUp -> {
            // SignUpActivity로 이동
            val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent, REQUEST_SIGNUP)
        }
    }
}

private fun checkSharedPreferences() {
    val userId = MyApplication.mySharedPreferences.getString("userId", "")

    // 저장된 userId 값이 있을 경우,
    if (!userId.isEmpty()) {
        // HomeActivity로 이동
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)

        Toast.makeText(this, "${userId}님 자동로그인 되었습니다!", Toast.LENGTH_SHORT).show()
    }
}

private fun checkValidation() {
    // 모든 폼이 입력되지 않았을 경우,
    if (editText_id.text.isEmpty() || editText_password.text.isEmpty()) {
        // 토스트 메시지 출력
        Toast.makeText(this, "아이디 또는 비밀번호가 입력되지 않았습니다.", Toast.LENGTH_SHORT).show()

    } else { // 모든 폼이 입력되었을 경우,
        // SharedPreferences에 자동로그인을 위한 userId 정보 저장
        saveSharedPreferences()

        // HomeActivity로 이동
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}

private fun saveSharedPreferences() {
    val userId = editText_id.text.toString()
    MyApplication.mySharedPreferences.setString("userId", userId)
}
```
<br>
