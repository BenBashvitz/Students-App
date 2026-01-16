package com.example.studentapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentapp.databinding.ActivityAddStudentBinding
import com.example.studentapp.models.Model
import com.example.studentapp.models.Student

class AddStudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val name = binding.nameInput.text.toString()
            val id = binding.idInput.text.toString()
            val phone = binding.phoneInput.text.toString()
            val address = binding.addressInput.text.toString()
            val isPresent = binding.isPresentCheckbox.isChecked

            Model.shared.students.add(Student(
                name,
                id,
                phone,
                address,
                isPresent
            ))
            finish()
        }
    }
}
