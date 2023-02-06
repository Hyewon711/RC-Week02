package com.example.myrc_02.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myrc_02.MainActivity
import com.example.myrc_02.helper.Constant
import com.example.myrc_02.databinding.FragmentLoginUserBinding
import com.example.myrc_02.helper.PrefHelper


class LoginUserFragment : Fragment() {
    private var _binding: FragmentLoginUserBinding? = null
    private val binding get() = _binding!!
    lateinit var prefHelper: PrefHelper
    // Context 받기위한 변수 선언
    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // 2. Context를 Activity로 형변환하여 할당
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        prefHelper = PrefHelper(mainActivity)

        _binding = FragmentLoginUserBinding.inflate(inflater, container, false)

        binding.userLoginTv.text ="${prefHelper.getString(Constant.PREF_USERNAME).toString()}님, 반가워요!"
        binding.userLoginBtn.setOnClickListener {
            prefHelper.clear()
            Toast.makeText(mainActivity, "로그아웃", Toast.LENGTH_SHORT).show()
            val intent = Intent(getActivity(), MainActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}