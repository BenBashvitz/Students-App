package com.example.studentapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentapp.databinding.ActivityEditStudentBinding
import com.example.studentapp.models.Model
import com.example.studentapp.models.getStudentFromIntent
import com.example.studentapp.models.getStudentPositionFromIntent
import com.example.studentapp.models.passIsSaveToIntent
import com.example.studentapp.models.passStudentPositionToIntent

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
        val student = getStudentFromIntent(intent, notAvailable)
        val studentIndex = getStudentPositionFromIntent(intent)

        binding?.nameInputEditText?.setText(student.name)
        binding?.idInputEditText?.setText(student.id)
        binding?.phoneInputEditText?.setText(student.phone)
        binding?.addressInputEditText?.setText(student.address)
        binding?.studentPresenceCheckbox?.isChecked = student.isPresent


        this.binding?.saveButton?.setOnClickListener {
            val name = this.binding?.nameInputEditText?.text.toString()
            val id = this.binding?.idInputEditText?.text.toString()
            val phone = this.binding?.phoneInputEditText?.text.toString()
            val address = this.binding?.addressInputEditText?.text.toString()
            val isPresent = this.binding?.studentPresenceCheckbox?.isChecked ?: false

            if(studentIndex == -1) {
                finish()
            } else {
                val studentToEdit = Model.shared.students[studentIndex]

                studentToEdit.id = id
                studentToEdit.name = name
                studentToEdit.phone = phone
                studentToEdit.address = address
                studentToEdit.isPresent = isPresent

                val intent = Intent()
                passIsSaveToIntent(intent, true)
                passStudentPositionToIntent(intent, studentIndex)
                setResult(RESULT_OK, intent)
                finish()
            }
        }

        this.binding?.deleteButton?.setOnClickListener {
            if(studentIndex == -1) {
                finish()
            } else {
                Model.shared.students.removeAt(studentIndex)
                val intent = Intent()
                passIsSaveToIntent(intent, false)
                passStudentPositionToIntent(intent, studentIndex)
                setResult(RESULT_OK, intent)
                finish()
            }
        }

        this.binding?.cancelButton?.setOnClickListener {
            finish()
        }
    }
}
