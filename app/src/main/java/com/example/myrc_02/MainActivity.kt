package com.example.myrc_02

import android.content.DialogInterface
import android.os.Bundle
import androidx.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myrc_02.helper.Constant
import com.example.myrc_02.databinding.ActivityMainBinding
import com.example.myrc_02.fragment.*
import com.example.myrc_02.helper.PrefHelper
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity()  {
    private lateinit var binding: ActivityMainBinding
    val TAG: String = "로그"
    //SharedPreference
    lateinit var prefHelper: PrefHelper
    val day_name = "Day"
    var day_check = "0"
    var adCheck: Int = 0

    //뒤로가기 연속 클릭 대기 시간
    var mBackWait:Long = 0

    // 액티비티가 생성되었을 때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        setupBottomNavigationView()
        prefHelper = PrefHelper(this)

        if (savedInstanceState == null ) {
            // Activity가 재생성될 시 첫 번째 화면을 표시할 필요가 없기때문에
            // 앱이 처음 실행되었는지 여부를 확인한다.
            binding.bottomNav.selectedItemId = R.id.menu_home
        }

        Log.d(TAG, "MainActivity - onCreate() called")
    }


    /* 레이아웃 설정 */
    private fun initBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) 
    }

    /* BottomNavigationView Fragment Change */
    private fun setupBottomNavigationView() {
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, HomeFragment())
                        .commit()
                    true
                }
                R.id.menu_category -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, CategoryFragment())
                        .commit()
                    true
                }
                R.id.menu_haru -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, HaruFragment())
                        .commit()
                    true
                }
                R.id.menu_favorite -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, FavoriteFragment())
                        .commit()
                    true
                }
                R.id.menu_user -> {
                    if(prefHelper.getString(Constant.PREF_USERNAME).isNullOrBlank() || prefHelper.getString(Constant.PREF_PASSWORD).isNullOrBlank()){
                        Log.d(TAG, "${prefHelper.getString(Constant.PREF_USERNAME)} 로그인 정보 없음")
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.frame_layout, UserFragment())
                            .commit()
                        true
                    } else {
                        Log.d(TAG, "${prefHelper.getString(Constant.PREF_USERNAME)} 현재 로그인한 아이디")
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.frame_layout, LoginUserFragment())
                            .commit()
                        true
                    }
                }
                else -> false
            }
        }
        binding.bottomNav.itemIconTintList = null
    }

    /* pop up banner */
    fun popUp() {
        // pop-up
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val prefDay = pref.getString(day_name, "0")
        val CurrentTime = System.currentTimeMillis()
        val TodayDate = Date(CurrentTime)
        val SDFormat = SimpleDateFormat("dd", java.util.Locale.getDefault())
        day_check = SDFormat.format(TodayDate)
        Log.d(TAG, "${pref.getString(day_name, day_check)} 오늘 날짜 시간")

        if ((Integer.parseInt(day_check) - Integer.parseInt(prefDay)) != 0) {
            // Dialog Message 설정
            val strMessage = "1개만 구매해도 전액 페이백!"

            // AlertDialog 정의
            val MainAlertDialog = AlertDialog.Builder(this)
            MainAlertDialog.setTitle("단 3일! 매일브랜디 앵콜"); // Title 설정
            MainAlertDialog.setIcon(R.drawable.brandi_logo); // Icon 설정
            MainAlertDialog.setMessage(strMessage); // 메시지 설정

            // positive 버튼 설정
            MainAlertDialog.setPositiveButton("닫기", DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })

            // Neutral 버튼 설정
            MainAlertDialog.setNeutralButton("오늘하루 보지않기", DialogInterface.OnClickListener { dialog, which ->
                pref.edit().putString(day_name, day_check).commit() // 오늘 '일(day)' 저장
                Log.d(TAG, "${pref.getString(day_name, day_check)} 오늘 날짜 시간")
                dialog.dismiss()
            })

            // AlertDialog 화면 출력
            MainAlertDialog.show()
        }
    }

    /* onBackPressed 메서드 */
    override fun onBackPressed() {
        // 뒤로가기 버튼 클릭
        if (System.currentTimeMillis() - mBackWait >= 2000) {
            Toast.makeText(this, "뒤로가기 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
        } else {
            finishAffinity() //액티비티 종료
        }
        mBackWait = System.currentTimeMillis()
    }

    /* ad Check */
    fun adCheck() {
        adCheck++
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "MainActivity - onStart() called")
        if (adCheck % 5 == 0) popUp()
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "MainActivity - onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "MainActivity - onPause() called")
        adCheck()
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "MainActivity - onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "MainActivity - onDestroy() called")
        prefHelper.clear()
        Log.d(TAG, "${prefHelper.getString(Constant.PREF_USERNAME)} 종료 로그")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "MainActivity - onRestart() called")
    }

}
