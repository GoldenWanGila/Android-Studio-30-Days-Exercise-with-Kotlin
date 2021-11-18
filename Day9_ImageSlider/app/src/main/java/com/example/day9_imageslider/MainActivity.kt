package com.example.day9_imageslider

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.day9_imageslider.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var imageList: MutableList<Int>

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getImages()
        binding.pageCountTextView.text = "1/${imageList.size}"

        binding.viewPager.adapter = CustomAdapter(this, imageList)
        binding.viewPager.addOnPageChangeListener(
            object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {}

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    val currentPosition = position + 1
                    binding.pageCountTextView.text = "$currentPosition/${imageList.size}"
                }

                override fun onPageSelected(position: Int) {}
            }
        )
    }

    private fun getImages() {
        imageList = arrayListOf()
        for (i in 1..4) {
            val imageName = "image0$i"
            imageList.add(getResourceByResourceName(imageName))
        }
    }

    private fun getResourceByResourceName(name: String): Int {
        return this.resources.getIdentifier(name, "drawable", this.packageName)
    }
}