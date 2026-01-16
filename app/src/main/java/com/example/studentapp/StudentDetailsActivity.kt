package com.example.studentapp

import android.os.Bundle
import android.util.Log
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

        val studentName = intent.getStringExtra("student_name") ?: "N/A"
        val studentId = intent.getStringExtra("student_id") ?: "N/A"
        val studentPresent = intent.getBooleanExtra("student_present", false)

        Log.v("studentName" , studentName)
        Log.v("studentId", studentId)
        Log.v("studentPresent", "$studentPresent")
        Log.v("binding", "$binding")

        binding?.studentNameTextView?.text = studentName
        binding?.studentIdTextView?.text = studentId
        binding?.studentPresence?.text = if (studentPresent) "Checked" else "Unchecked"
        binding?.studentPresenceCheckbox?.isChecked = studentPresent
    }
}