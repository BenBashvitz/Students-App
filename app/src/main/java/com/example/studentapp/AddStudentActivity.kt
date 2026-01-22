package com.example.studentapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentapp.databinding.ActivityAddStudentBinding
import com.example.studentapp.models.Model
import com.example.studentapp.models.Student
import kotlin.math.log

class AddStudentActivity : AppCompatActivity() {
    private var binding: ActivityAddStudentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityAddStudentBinding.inflate(layoutInflater)
        setContentView(this.binding?.root)

        this.binding?.saveButton?.setOnClickListener {
            val name = this.binding?.nameInput?.text.toString()
            val id = this.binding?.idInput?.text.toString()
            val phone = this.binding?.phoneInput?.text.toString()
            val address = this.binding?.addressInput?.text.toString()
            val isPresent = this.binding?.isPresentCheckbox?.isChecked ?: false

            Model.shared.students.add(Student(
                name,
                id,
                phone,
                address,
                isPresent
            ))

            val returnIntent = Intent()
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }
}
