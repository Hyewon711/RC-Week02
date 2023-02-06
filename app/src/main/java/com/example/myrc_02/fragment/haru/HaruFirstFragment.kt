package com.example.myrc_02.fragment.haru

import androidx.fragment.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ViewFlipper
import com.example.myrc_02.MainActivity
import com.example.myrc_02.R
import com.example.myrc_02.databinding.FragmentHaruFirstBinding


class HaruFirstFragment : Fragment() {
    private lateinit var view_flipper: ViewFlipper
    private var _binding: FragmentHaruFirstBinding? = null
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
        var view: View = inflater.inflate(R.layout.fragment_haru_first, container, false)

        view_flipper = view.findViewById(R.id.img_slide2)
        _binding = FragmentHaruFirstBinding.inflate(inflater, container, false)

        var imgList = intArrayOf(
            R.drawable.viewflipper_img9,
            R.drawable.viewflipper_img10,
            R.drawable.viewflipper_img8,
            R.drawable.viewflipper_img7,
            R.drawable.viewflipper_img6
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

        return view
    }

}