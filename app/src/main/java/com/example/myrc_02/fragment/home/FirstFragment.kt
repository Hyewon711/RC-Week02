package com.example.myrc_02.fragment.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ViewFlipper
import com.example.myrc_02.MainActivity
import com.example.myrc_02.ProductDetailActivity
import com.example.myrc_02.R
import com.example.myrc_02.databinding.FragmentFirstBinding
import kotlinx.android.synthetic.main.fragment_first.view.*

class FirstFragment : Fragment() {
    private lateinit var view_flipper: ViewFlipper
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    // Context 받기위한 변수 선언
    lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Context를 Activity로 형변환하여 할당
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_first, container, false)

        view_flipper = view.findViewById(R.id.img_slide)
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        var imgList = intArrayOf(
            R.drawable.viewflipper_img1,
            R.drawable.viewflipper_img2,
            R.drawable.viewflipper_img3,
            R.drawable.viewflipper_img4,
            R.drawable.viewflipper_img5
        )


        view_flipper.inAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_right)
        view_flipper.outAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_out_left)
        for (image in imgList) {
            val imageView = ImageView(requireContext())
            val layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            imageView.layoutParams = layoutParams
            imageView.setImageResource(image)
            imageView.setScaleType(ImageView.ScaleType.FIT_XY)
            view_flipper.addView(imageView)
        }

        view_flipper.isAutoStart = true
        view_flipper.flipInterval = 2500
        view_flipper.startFlipping()

        view.product_icon1.setOnClickListener {

            val intent = Intent(getActivity(), ProductDetailActivity::class.java)
            startActivity(intent)

        }

        return view
    }



}