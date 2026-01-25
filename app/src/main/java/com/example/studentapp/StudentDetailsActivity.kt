package com.example.studentapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentapp.databinding.ActivityStudentDetailsBinding
import com.example.studentapp.models.Model
import com.example.studentapp.models.getStudentFromIntent
import com.example.studentapp.models.getStudentPositionFromIntent
import com.example.studentapp.models.passStudentPositionToIntent
import com.example.studentapp.models.passStudentToIntent

class StudentDetailsActivity : AppCompatActivity() {
    var binding: ActivityStudentDetailsBinding? = null

    private val editStudentActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                setResult(RESULT_OK, result.data)
                finish()
            }
        }

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
        val student = getStudentFromIntent(intent, notAvailable)
        val studentIndex = getStudentPositionFromIntent(intent)

        binding?.studentNameTextView?.text = student.name
        binding?.studentIdTextView?.text = student.id
        binding?.studentPhoneTextView?.text = student.phone
        binding?.studentAddressTextView?.text = student.address
        binding?.studentPresenceCheckbox?.isChecked = student.isPresent

        this.binding?.editStudentDetailsButton?.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            passStudentToIntent(intent, student)
            passStudentPositionToIntent(intent, studentIndex)
            this.editStudentActivityResultLauncher.launch(intent)
        }

        binding?.appBar?.setNavigationOnClickListener {
            finish()
        }
    }
}