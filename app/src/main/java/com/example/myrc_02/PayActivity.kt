package com.example.myrc_02

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil.setContentView
import com.example.myrc_02.databinding.ActivityPayBinding
import com.example.myrc_02.helper.Constant
import com.example.myrc_02.helper.PrefHelper
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_pay.*

@Suppress("DEPRECATION")
class PayActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPayBinding
    val TAG: String = "로그"
    lateinit var prefHelper: PrefHelper
    var ContentCheck: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefHelper = PrefHelper(this)
        initBinding()
        backBtn()
        pay()
    }


    /* 구매 완료 */
    fun pay() {
        binding.payBtn.setOnClickListener {
            prefHelper.clearOrder()
            editClear()
            Toast.makeText(this, "빠르게 배송해드릴게요!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    /* 뒤로가기 */
    fun backBtn() {
        binding.backBtn.setOnClickListener{
            onBackPressed()
        }
    }

    /* 구매 중단 임시저장 */
    fun save() {
        saveSession(name_edit.text.toString(), tel_edit.text.toString(), mail_edit.text.toString())
    }

    /* SharedPreference Put (데이터 저장) */
    private fun saveSession(name: String, tel: String, mail: String){
        prefHelper.put( Constant.PREF_ORDER_NAME, name )
        prefHelper.put( Constant.PREF_ORDER_TEL, tel )
        prefHelper.put( Constant.PREF_ORDER_MAIL, mail)
    }

    /* 이전에 작성한 목록 불러오기 */
    fun load() {
        name_edit.setText(prefHelper.getString(Constant.PREF_ORDER_NAME))
        tel_edit.setText(prefHelper.getString(Constant.PREF_ORDER_TEL))
        mail_edit.setText(prefHelper.getString(Constant.PREF_ORDER_MAIL))
    }

    /* 액티비티가 종료될 시 edit 비우기 */
    fun editClear() {
        name_edit.setText("")
        tel_edit.setText("")
        mail_edit.setText("")
    }

    /* 구매 중단 메세지 */
    fun stopPayMessage() {
        val strMessage = "아직 결제가 끝나지 않았어요."

        // AlertDialog 정의
        val MainAlertDialog = AlertDialog.Builder(this)
        MainAlertDialog.setTitle("주문이 진행중입니다."); // Title 설정
        MainAlertDialog.setIcon(R.drawable.brandi_logo); // Icon 설정
        MainAlertDialog.setMessage(strMessage); // 메시지 설정

        // positive 버튼 설정
        MainAlertDialog.setPositiveButton("구매하러 가기", DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })

        // AlertDialog 화면 출력
        MainAlertDialog.show()
    }

    /* 레이아웃 설정 */
    private fun initBinding() {
        binding = ActivityPayBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "PayActivity - onResume() called")
        load()
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "PayActivity - onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "PayActivity - onStop() called")
        save()
    }

    override fun onRestart() {
        super.onRestart()
        stopPayMessage()
    }

}