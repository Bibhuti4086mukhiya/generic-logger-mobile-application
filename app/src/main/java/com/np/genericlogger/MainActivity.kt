package com.np.genericlogger

import LoggerAdapter
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.np.genericlogger.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: LoggerDatabaseHelper
    private lateinit var loggerAdapter: LoggerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db= LoggerDatabaseHelper(this)
        loggerAdapter = LoggerAdapter(db.getAllLogger(),this)

//      for horizontal
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.loggersRecyclerView.layoutManager = layoutManager
        // Set adapter for RecyclerView
        binding.loggersRecyclerView.adapter = loggerAdapter

        binding.addLoggerButton.setOnClickListener {
            val intent = Intent(this,AddLoggerActivity::class.java)
            startActivity(intent)
        }

    }
    override fun onResume() {
        super.onResume()
        loggerAdapter.refreshData(db.getAllLogger())
    }
}