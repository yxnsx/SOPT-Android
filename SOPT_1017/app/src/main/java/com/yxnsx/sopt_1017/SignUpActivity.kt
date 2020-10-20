package com.yxnsx.sopt_1017

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // 버튼에 클릭리스너 설정
        button_signUp.setOnClickListener(onClickListener)
    }

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
}