package com.yxnsx.sopt_1017.week_01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.yxnsx.sopt_1017.week_02.HomeActivity
import com.yxnsx.sopt_1017.R
import kotlinx.android.synthetic.main.activity_log_in.*

class LogInActivity : AppCompatActivity() {

    private val REQUEST_SIGNUP = 100

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
}