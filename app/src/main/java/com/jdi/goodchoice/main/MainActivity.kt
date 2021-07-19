package com.jdi.goodchoice.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayout
import com.jdi.goodchoice.R
import com.jdi.goodchoice.databinding.ActivityMainBinding
import com.jdi.goodchoice.main.`else`.ElseFragment
import com.jdi.goodchoice.main.list.ListFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModel()
    lateinit var binding: ActivityMainBinding

    lateinit var listFragment: ListFragment
    lateinit var elseFragment: ElseFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listFragment = ListFragment()
        elseFragment = ElseFragment()
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this ,R.layout.activity_main).apply {
            lifecycleOwner = this@MainActivity
            tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.apply {
                        var fragment: Fragment
                        when(position) {
                            0-> {
                                //리스트뷰
                                fragment = listFragment
                            }
                            else -> {
                                //???
                                fragment = elseFragment
                            }
                        }
                        supportFragmentManager.beginTransaction().replace(container.id, fragment).commit()
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

            })
        }

        viewModel.hotels.observe(this, {
            Log.d("JDI", "hotels Updated ${it.size}")
        })
    }
}