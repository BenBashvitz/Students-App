package com.example.studentapp

import android.content.Intent
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
        val studentPhone = intent.getStringExtra("student_phone") ?: notAvailable
        val studentAddress = intent.getStringExtra("student_address") ?: notAvailable
        val studentPresent = intent.getBooleanExtra("student_present", false)
        val studentIndex = intent.getIntExtra("student_index", -1)

        binding?.studentNameTextView?.text = studentName
        binding?.studentIdTextView?.text = studentId
        binding?.studentPresenceCheckbox?.isChecked = studentPresent

        this.binding?.editStudentDetailsButton?.setOnClickListener {
            val intent = Intent(this@StudentDetailsActivity, EditStudentActivity::class.java)
            intent.putExtra("student_index", studentIndex)
            intent.putExtra("student_name", studentName)
            intent.putExtra("student_id", studentId)
            intent.putExtra("student_phone", studentPhone)
            intent.putExtra("student_address", studentAddress)
            intent.putExtra("student_present", studentPresent)
            startActivity(intent)
        }

        binding?.appBar?.setNavigationOnClickListener {
            finish()
        }
    }
}