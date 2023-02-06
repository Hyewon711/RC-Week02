package com.example.myrc_02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myrc_02.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityProductDetailBinding
    val TAG: String = "로그"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        share()
        Buy()
        BackBtn()
    }

    /* 구매하기 */
    fun Buy() {
        binding.buyBtn.setOnClickListener{
            startActivity(Intent(this, PayActivity::class.java))
            finish()
        }
    }

    fun BackBtn() {
        binding.backBtn.setOnClickListener{
            finish()
        }
    }

    /* 레이아웃 설정 */
    private fun initBinding() {
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /* 암시적 인텐트 */
   fun share() {
        binding.shareBtn.setOnClickListener {
            val message = "상품 공유하기"
            // 공유 인텐트
            val shareIntent = Intent(Intent.ACTION_SEND)
                .setType("text/plain")
                .putExtra(Intent.EXTRA_TEXT, message)

            // 선택 인텐트
            val chooser = Intent.createChooser(shareIntent, "브랜디")

            startActivity(chooser)
        }
   }

}