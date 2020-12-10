package com.yxnsx.sopt.week01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.yxnsx.sopt.week03.HomeActivity
import com.yxnsx.sopt.databinding.ActivitySignInBinding
import com.yxnsx.sopt.util.*
import com.yxnsx.sopt.week06.sopt_api.RequestSignIn
import com.yxnsx.sopt.week06.sopt_api.ResponseUserData
import com.yxnsx.sopt.week06.sopt_api.SoptRetrofitObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivitySignInBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 뷰바인딩 적용
        viewBinding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        setListeners()
        checkSharedPreferences() // SharedPreferences 값 체크 후 저장된 userId 값이 있다면 자동로그인
    }

    private fun setListeners() {
        viewBinding.buttonSignIn.setOnClickListener(onClickListener)
        viewBinding.textViewSignUp.setOnClickListener(onClickListener)
    }

    private fun checkSharedPreferences() {
        val userName = MyApplication.mySharedPreferences.getString(USER_NAME, "")

        if (userName.isNotEmpty()) {
            // HomeActivity로 이동
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)

            Toast.makeText(this, "${userName}님 자동로그인 되었습니다!", Toast.LENGTH_SHORT).show()
        }
    }

    private val onClickListener = View.OnClickListener() {
        when (it.id) {
            viewBinding.buttonSignIn.id -> {
                // 폼 입력상태 체크
                checkValidation()
            }
            viewBinding.textViewSignUp.id -> {
                // SignUpActivity로 이동
                val intent = Intent(this, SignUpActivity::class.java)
                startActivityForResult(intent, REQUEST_SIGN_UP)
            }
        }
    }

    private fun checkValidation() {
        val email = viewBinding.editTextEmail.text.toString()
        val password = viewBinding.editTextPassword.text.toString()

        // 모든 폼이 입력되지 않았을 경우,
        if (email.isEmpty() || password.isEmpty()) {
            // 토스트 메시지 출력
            Toast.makeText(this, "아이디 또는 비밀번호가 입력되지 않았습니다.", Toast.LENGTH_SHORT).show()

        } else { // 모든 폼이 입력되었을 경우,
            signInUserToServer(email, password)
        }
    }

    private fun signInUserToServer(email: String, password: String) {
        val call: Call<ResponseUserData> =
            SoptRetrofitObject.SOPT_USER_API.postSignIn(RequestSignIn(email, password))

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
                        MyApplication.mySharedPreferences.setString(USER_NAME, it.data.userName)
                        MyApplication.mySharedPreferences.setString(USER_EMAIL, it.data.email)

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

                REQUEST_SIGN_UP -> {
                    // 넘어온 인텐트에서 데이터 가져오기
                    val email = data?.getStringExtra(USER_EMAIL)
                    val password = data?.getStringExtra(USER_PASSWORD)

                    // 가져온 데이터 바탕으로 editText에 값 설정
                    viewBinding.editTextEmail.setText(email)
                    viewBinding.editTextPassword.setText(password)
                }
            }
        }
    }


    companion object {
        const val REQUEST_SIGN_UP = 100
    }
}