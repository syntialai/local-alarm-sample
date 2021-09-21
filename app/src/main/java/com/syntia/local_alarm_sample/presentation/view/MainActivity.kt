package com.syntia.local_alarm_sample.presentation.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.syntia.local_alarm_sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnToggleAlarm.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.btnToggleAlarm -> toggleAlarm()
        }
    }

    private fun toggleAlarm() {
        Toast.makeText(this, "Toggled!", Toast.LENGTH_SHORT).show()
    }
}