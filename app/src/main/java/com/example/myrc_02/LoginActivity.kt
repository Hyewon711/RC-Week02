package com.example.myrc_02

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myrc_02.databinding.ActivityLoginBinding
import com.example.myrc_02.helper.Constant
import com.example.myrc_02.helper.PrefHelper
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    val TAG: String = "로그"
    lateinit var prefHelper: PrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        prefHelper = PrefHelper(this)
        Login()
        BackBtn()
    }



    fun Login() {
        binding.loginBtn.setOnClickListener {
            if(login_id.text.isNotEmpty() && login_pw.text.isNotEmpty()) {
                saveSession(login_id.text.toString(), login_pw.text.toString())
                Toast.makeText(this, "${prefHelper.getString(Constant.PREF_USERNAME)}님 로그인 되었습니다.", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            else {
                Toast.makeText(this, "아이디와 비밀번호를 다시 확인해주세요.", Toast.LENGTH_SHORT).show()
                login_id.setText("")
                login_pw.setText("")
            }
        }
    }

    fun BackBtn() {
        binding.backBtn.setOnClickListener{
            finish()
        }
    }
    /* SharedPreference Put (데이터 저장) */
    private fun saveSession(username: String, password: String){
        prefHelper.put( Constant.PREF_USERNAME, username )
        prefHelper.put( Constant.PREF_PASSWORD, password )
        prefHelper.put( Constant.PREF_IS_LOGIN, true)
    }

    /* 레이아웃 설정 */
    private fun initBinding() {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "LoginActivity - onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "LoginActivity - onResume() called")
    }
    
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "LoginActivity - onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "LoginActivity - onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "LoginActivity - onDestroy() called")
    }

}