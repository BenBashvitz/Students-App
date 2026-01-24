package com.example.studentapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentapp.databinding.ActivityAddStudentBinding
import com.example.studentapp.models.Model
import com.example.studentapp.models.Student

class AddStudentActivity : AppCompatActivity() {
    private var binding: ActivityAddStudentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityAddStudentBinding.inflate(layoutInflater)
        setContentView(this.binding?.root)

        this.binding?.saveButton?.setOnClickListener {
            val name = this.binding?.nameInputTextField?.text.toString()
            val id = this.binding?.idInputTextField?.text.toString()
            val phone = this.binding?.phoneInputTextField?.text.toString()
            val address = this.binding?.addressInputTextField?.text.toString()
            val isPresent = this.binding?.isPresentCheckbox?.isChecked ?: false

            Model.shared.students.add(Student(
                name,
                id,
                phone,
                address,
                isPresent
            ))

            val returnIntent = Intent()
            setResult(RESULT_OK, returnIntent)
            finish()
        }
    }
}
