package com.example.studentapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentapp.databinding.ActivityEditStudentBinding
import com.example.studentapp.models.Model

class EditStudentActivity : AppCompatActivity() {
    private var binding: ActivityEditStudentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        this.binding = ActivityEditStudentBinding.inflate(layoutInflater)
        setContentView(this.binding?.root)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        this.binding?.appBar?.setNavigationOnClickListener {
            finish()
        }

        val notAvailable = getString(R.string.not_available)
        val studentName = intent.getStringExtra("student_name") ?: notAvailable
        val studentId = intent.getStringExtra("student_id") ?: notAvailable
        val studentPhone = intent.getStringExtra("student_phone") ?: notAvailable
        val studentAddress = intent.getStringExtra("student_address") ?: notAvailable
        val studentPresent = intent.getBooleanExtra("student_present", false)

        binding?.nameInputTextField?.setText(studentName)
        binding?.idInputTextField?.setText(studentId)
        binding?.phoneInputTextField?.setText(studentPhone)
        binding?.addressInputTextField?.setText(studentAddress)
        binding?.isPresentCheckbox?.isChecked = studentPresent

        val studentIndex = intent.getIntExtra("student_index", -1)

        this.binding?.saveButton?.setOnClickListener {
            val name = this.binding?.nameInputTextField?.text.toString()
            val id = this.binding?.idInputTextField?.text.toString()
            val phone = this.binding?.phoneInputTextField?.text.toString()
            val address = this.binding?.addressInputTextField?.text.toString()
            val isPresent = this.binding?.isPresentCheckbox?.isChecked ?: false

            if(studentIndex != -1) {
                val studentToEdit = Model.shared.students[studentIndex]

                studentToEdit.id = id
                studentToEdit.name = name
                studentToEdit.phone = phone
                studentToEdit.address = address
                studentToEdit.isPresent = isPresent

                val intent = Intent(this, StudentListActivity::class.java)
                intent.putExtra("is_save", true)
                intent.putExtra("student_index", studentIndex)
                startActivity(intent)
            } else {
                finish()
            }
        }

        this.binding?.deleteButton?.setOnClickListener {
            if(studentIndex != -1) {
                Model.shared.students.removeAt(studentIndex)
                val intent = Intent(this, StudentListActivity::class.java)
                intent.putExtra("is_save", false)
                intent.putExtra("student_index", studentIndex)
                startActivity(intent)
            } else {
                finish()
            }
        }

        this.binding?.cancelButton?.setOnClickListener {
            finish()
        }
    }
}
