package com.mac.flights.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mac.flights.R
import com.mac.flights.databinding.ActivityMainBinding
import com.mac.flights.extensions.applyTaskDescription
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        applyTaskDescription()
    }
}