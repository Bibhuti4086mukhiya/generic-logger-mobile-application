package com.np.genericlogger

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.np.genericlogger.databinding.ActivityUpdateLoggerBinding

class UpdateLoggerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateLoggerBinding
    private lateinit var db: LoggerDatabaseHelper
    private var loggerId = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateLoggerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = LoggerDatabaseHelper(this)

        loggerId = intent.getIntExtra("note_id",-1)
        if (loggerId == -1){
            finish()
            return
        }
        val note = db.getLoggerByID(loggerId)
        binding.updateTextTitle.setText(note.title)

        binding.updateLoggerButton.setOnClickListener{
            val newTitle = binding.updateTextTitle.text.toString()
            val updateLogger = Logger(loggerId,newTitle,)
            db.updateLogger(updateLogger)
            finish()
            Toast.makeText(this,"Changes Saved", Toast.LENGTH_SHORT).show()
        }

    }
}