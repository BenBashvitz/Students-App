package com.example.studentapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentapp.databinding.ActivityEditStudentBinding
import com.example.studentapp.models.Model
import com.example.studentapp.models.Student

class EditStudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditStudentBinding.inflate(layoutInflater)
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

        binding.cancelButton.setOnClickListener {
            finish()
        }

        binding.deleteButton.setOnClickListener {
            val id = binding.idInput.text.toString()
            val name = binding.nameInput.text.toString()

            Model.shared.students.removeIf { student -> student.id == id && student.name == name }
            finish()
        }
    }
}