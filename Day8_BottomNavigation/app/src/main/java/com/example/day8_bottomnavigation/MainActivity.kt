package com.example.day8_bottomnavigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.day8_bottomnavigation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // 利用「列舉類別」來列舉出所有 fragment 的種類
    enum class FragmentType {
        Home,
        Notifications,
        Dashboard
    }

    private val manager = this.supportFragmentManager

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 剛進入APP，預設顯示 HomeFragment
        changeFragmentTo(FragmentType.Home)

        // 處理 bottom navigation 被選取時，要跳到哪一個 fragment
        binding.navigation.setOnItemSelectedListener {
            // 當被 bottom navigation 被點選時，將即將要進入 fragment 暫存於變數 type 中
            when (it.itemId) {
                R.id.navigation_home -> {
                    changeFragmentTo(FragmentType.Home)
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_dashboard -> {
                    changeFragmentTo(FragmentType.Dashboard)
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_notifications -> {
                    changeFragmentTo(FragmentType.Notifications)
                    return@setOnItemSelectedListener true
                }
                else -> {
                    return@setOnItemSelectedListener false
                }
            }
        }
    }

    private fun changeFragmentTo(type: FragmentType) {
        val transaction = manager.beginTransaction()
        when (type) {
            FragmentType.Home -> {
                val homeFragment = HomeFragment()
                transaction.replace(R.id.container, homeFragment)
            }
            FragmentType.Dashboard -> {
                val dashboardFragment = DashboardFragment()
                transaction.replace(R.id.container, dashboardFragment)
            }
            FragmentType.Notifications -> {
                val notificationsFragment = NotificationsFragment()
                transaction.replace(R.id.container, notificationsFragment)
            }
        }
        transaction.addToBackStack(null)
        transaction.commit()
    }
}