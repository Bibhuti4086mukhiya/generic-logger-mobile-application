package com.np.genericlogger

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.np.genericlogger.databinding.ActivityAddLoggerBinding
import com.np.genericlogger.databinding.ActivityMainBinding

class AddLoggerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddLoggerBinding
    private lateinit var db: LoggerDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddLoggerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = LoggerDatabaseHelper(this)

        binding.buttonSave.setOnClickListener{
            val title = binding.editTextTitle.text.toString()
            val logger = Logger(0,title)
            db.insertLogger(logger)
            finish()
            Toast.makeText(this,"saved noted", Toast.LENGTH_SHORT).show()
        }

    }
}