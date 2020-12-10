package com.yxnsx.sopt.week01

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.yxnsx.sopt.R
import com.yxnsx.sopt.databinding.ActivitySignUpBinding
import com.yxnsx.sopt.util.USER_EMAIL
import com.yxnsx.sopt.util.USER_NAME
import com.yxnsx.sopt.util.USER_PASSWORD
import com.yxnsx.sopt.week06.sopt_api.RequestSignUp
import com.yxnsx.sopt.week06.sopt_api.ResponseUserData
import com.yxnsx.sopt.week06.sopt_api.SoptRetrofitObject
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivitySignUpBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // 뷰바인딩 적용
        viewBinding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        setListeners()
    }

    private fun setListeners() {
        button_signUp.setOnClickListener(onClickListener)
    }

    private val onClickListener = View.OnClickListener {
        when (it.id) {
            viewBinding.buttonSignUp.id -> {
                // 폼 입력상태 체크
                checkValidation()
            }
        }
    }

    private fun checkValidation() {

        val name = viewBinding.editTextName.text.toString()
        val email = viewBinding.editTextEmail.text.toString()
        val password = viewBinding.editTextPassword.text.toString()

        Log.d("TAG", "checkValidation: $name + $email + $password")

        // 모든 폼이 입력되지 않았을 경우,
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            // 토스트 메시지 츨력
            Toast.makeText(this, "모든 폼을 입력해주세요.", Toast.LENGTH_SHORT).show()

        } else { // 모든 폼이 입력되었을 경우,
            signUpUserToServer(email, password, name)
        }
    }

    private fun signUpUserToServer(email: String, password: String, name: String) {
        val call: Call<ResponseUserData> =
            SoptRetrofitObject.SOPT_USER_API.postSignUp(RequestSignUp(email, password, name))

        call.enqueue(object : Callback<ResponseUserData> {
            override fun onFailure(call: Call<ResponseUserData>, t: Throwable) {
                Log.d("TAG", t.localizedMessage!!.toString())
                Toast.makeText(applicationContext, "회원가입에 실패헸습니다.", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ResponseUserData>,
                response: Response<ResponseUserData>
            ) {
                response.takeIf { it.isSuccessful }
                    ?.body()
                    ?.let {
                        Toast.makeText(applicationContext, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT)
                            .show()
                        passUserInfoToLoginActivity(it.data.userName, it.data.email, password)
                    }
            }
        })
    }

    private fun passUserInfoToLoginActivity(
        name: String,
        email: String,
        password: String
    ) {
        // LogInActivity로 되돌아가는 인텐트 설정
        val intent = Intent(this, SignInActivity::class.java)

        // 인텐트로 넘길 데이터 설정
        intent.putExtra(USER_NAME, name)
        intent.putExtra(USER_EMAIL, email)
        intent.putExtra(USER_PASSWORD, password)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}