package com.example.day8_bottomnavigation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

const val TAG = "HomeFragment"

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // fragment 使用 binding 的方式和 activity 使用 binding 的方式不同，因此這邊如果使用 binding button 會無法作用
        // (fragment 還是可以使用 binding，但為了不再多記一個用法導致搞混，所以直接使用 findViewById method)
        val homeButton = view.findViewById<Button>(R.id.homeButton)
        homeButton.setOnClickListener(homeButtonClickHandler)

        return view
    }

    private val homeButtonClickHandler = View.OnClickListener {
        val intent = Intent(activity, Home2Activity::class.java)
        startActivity(intent)
    }
}