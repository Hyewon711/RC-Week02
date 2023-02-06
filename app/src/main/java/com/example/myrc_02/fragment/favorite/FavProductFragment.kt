package com.example.myrc_02.fragment.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.myrc_02.MainActivity
import com.example.myrc_02.R
import com.example.myrc_02.databinding.FragmentFavProductBinding
import com.example.myrc_02.fragment.FavoriteFragment
import kotlinx.android.synthetic.main.fragment_fav_product.view.*
import kotlinx.coroutines.Dispatchers.Main


class FavProductFragment : Fragment() {
    private var _binding: FragmentFavProductBinding? = null
    private val binding get() = _binding!!
    // Context 받기위한 변수 선언
    lateinit var mainActivity: MainActivity

    companion object {
        fun newInstance() : FavoriteFragment = FavoriteFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
        val binding = inflater.inflate(R.layout.fragment_fav_product, container, false)
        Glide.with(this).load(R.raw.heart).override(240,240).into(binding.heart_gif)

        binding.fav_btn.setOnClickListener {
        }
        return binding
    }

}