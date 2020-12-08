package com.yxnsx.sopt.week01

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.yxnsx.sopt.week03.HomeActivity
import com.yxnsx.sopt.R
import com.yxnsx.sopt.week03.BottomNavProfileFragment
import com.yxnsx.sopt.week06.RequestSignIn
import com.yxnsx.sopt.week06.ResponseUserData
import com.yxnsx.sopt.week06.RetrofitClient
import kotlinx.android.synthetic.main.activity_log_in.*
import kotlinx.android.synthetic.main.activity_log_in.button_signUp
import kotlinx.android.synthetic.main.activity_log_in.editText_id
import kotlinx.android.synthetic.main.activity_log_in.editText_password
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogInActivity : AppCompatActivity() {

    private val REQUEST_SIGNUP = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        // 버튼 클릭리스너 설정
        button_logIn.setOnClickListener(onClickListener)
        button_signUp.setOnClickListener(onClickListener)

        // SharedPreferences 값 체크 후 저장된 userId 값이 있다면 자동로그인
        checkSharedPreferences()
    }

    private fun checkSharedPreferences() {
        val userName = MyApplication.mySharedPreferences.getString("userName", "")

        if (userName.isNotEmpty()) {
            // HomeActivity로 이동
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)

            Toast.makeText(this, "${userName}님 자동로그인 되었습니다!", Toast.LENGTH_SHORT).show()
        }
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

    private fun checkValidation() {
        val userId = editText_id.text.toString()
        val userPassword = editText_password.text.toString()

        // 모든 폼이 입력되지 않았을 경우,
        if (userId.isEmpty() || userPassword.isEmpty()) {
            // 토스트 메시지 출력
            Toast.makeText(this, "아이디 또는 비밀번호가 입력되지 않았습니다.", Toast.LENGTH_SHORT).show()

        } else { // 모든 폼이 입력되었을 경우,
            signInUserToServer(userId, userPassword)
        }
    }

    private fun signInUserToServer(userId: String, userPassword: String) {
        val call: Call<ResponseUserData> = RetrofitClient.userDataService.postSignIn(
            RequestSignIn(userId, userPassword)
        )
        call.enqueue(object : Callback<ResponseUserData> {
            override fun onFailure(call: Call<ResponseUserData>, t: Throwable) {
                Log.d("TAG", t.localizedMessage!!.toString())
                Toast.makeText(applicationContext, "로그인에 실패헸습니다.", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ResponseUserData>,
                response: Response<ResponseUserData>
            ) {
                response.takeIf { it.isSuccessful }
                    ?.body()
                    ?.let {
                        // SharedPreferences에 자동로그인을 위한 userName 및 userId 정보 저장
                        MyApplication.mySharedPreferences.setString("userName", it.data.userName)
                        MyApplication.mySharedPreferences.setString("userId", it.data.email)

                        // HomeActivity로 이동
                        val intent = Intent(applicationContext, HomeActivity::class.java)
                        startActivity(intent)

                        Toast.makeText(applicationContext, "로그인에 성공헸습니다.", Toast.LENGTH_SHORT)
                            .show()
                    }
            }
        })
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