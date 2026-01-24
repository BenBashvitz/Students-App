package com.example.studentapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentapp.databinding.ActivityStudentDetailsBinding

class StudentDetailsActivity : AppCompatActivity() {
    var binding: ActivityStudentDetailsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityStudentDetailsBinding.inflate(layoutInflater)

        setContentView(binding?.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val notAvailable = getString(R.string.not_available)
        val studentName = intent.getStringExtra("student_name") ?: notAvailable
        val studentId = intent.getStringExtra("student_id") ?: notAvailable
        val studentPresent = intent.getBooleanExtra("student_present", false)

        binding?.studentNameTextView?.text = studentName
        binding?.studentIdTextView?.text = studentId
        binding?.studentPresence?.text = getString(R.string.student_presence_checked)
        binding?.studentPresenceCheckbox?.isChecked = studentPresent
    }
}