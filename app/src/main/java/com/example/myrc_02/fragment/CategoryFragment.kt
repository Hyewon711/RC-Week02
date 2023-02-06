package com.example.myrc_02.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import com.example.myrc_02.R
import com.example.myrc_02.databinding.FragmentCategoryBinding
import kotlinx.android.synthetic.main.fragment_category.*

class CategoryFragment : Fragment() {
    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    var listner = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
        if (isChecked){
            // 어떤 아이디인지 확인
            when(buttonView.id){
                R.id.category_btn1 -> {
                    category_img1.visibility = View.VISIBLE
                    category_img2.visibility = View.INVISIBLE
                    category_img3.visibility = View.INVISIBLE
                    category_img4.visibility = View.INVISIBLE
                }
                R.id.category_btn2 -> {
                    category_img2.visibility = View.VISIBLE
                    category_img1.visibility = View.INVISIBLE
                    category_img3.visibility = View.INVISIBLE
                    category_img4.visibility = View.INVISIBLE
                }
                R.id.category_btn3 -> {
                    category_img3.visibility = View.VISIBLE
                    category_img1.visibility = View.INVISIBLE
                    category_img2.visibility = View.INVISIBLE
                    category_img4.visibility = View.INVISIBLE
                }
                R.id.category_btn4 -> {
                    category_img4.visibility = View.VISIBLE
                    category_img1.visibility = View.INVISIBLE
                    category_img2.visibility = View.INVISIBLE
                    category_img3.visibility = View.INVISIBLE
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)

        binding.categoryBtn1.setOnCheckedChangeListener (listner)
        binding.categoryBtn2.setOnCheckedChangeListener (listner)
        binding.categoryBtn3.setOnCheckedChangeListener (listner)
        binding.categoryBtn4.setOnCheckedChangeListener (listner)

        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}