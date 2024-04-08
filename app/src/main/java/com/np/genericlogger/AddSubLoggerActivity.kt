package com.np.genericlogger

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.np.genericlogger.databinding.ActivityAddLoggerBinding
import com.np.genericlogger.databinding.ActivityAddSubLoggerBinding
import com.np.genericlogger.databinding.ActivityMainBinding

class AddSubLoggerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddSubLoggerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddSubLoggerBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}