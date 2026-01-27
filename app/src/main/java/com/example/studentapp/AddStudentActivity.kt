package com.example.studentapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentapp.databinding.ActivityAddStudentBinding
import com.example.studentapp.models.Model
import com.example.studentapp.models.Student

class AddStudentActivity : AppCompatActivity() {
    private var binding: ActivityAddStudentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        this.binding = ActivityAddStudentBinding.inflate(layoutInflater)
        setContentView(this.binding?.root)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        this.binding?.appBar?.setNavigationOnClickListener {
            finish()
        }

        this.binding?.saveButton?.setOnClickListener {
            val name = this.binding?.nameInputEditText?.text.toString()
            val id = this.binding?.idInputEditText?.text.toString()
            val phone = this.binding?.phoneInputEditText?.text.toString()
            val address = this.binding?.addressInputEditText?.text.toString()
            val isPresent = this.binding?.studentPresenceCheckbox?.isChecked ?: false

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

        this.binding?.cancelButton?.setOnClickListener {
            finish()
        }
    }
}
