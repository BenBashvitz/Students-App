package com.example.studentapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentapp.models.Model
import com.example.studentapp.models.Student
import com.example.studentapp.databinding.ActivityStudentListBinding

class StudentListActivity : AppCompatActivity() {
    var binding: ActivityStudentListBinding? = null
    var adapter: StudentsAdapter? = null

    private val getContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                this.adapter?.notifyItemInserted(Model.shared.students.size - 1)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        this.binding = ActivityStudentListBinding.inflate(layoutInflater)

        setContentView(this.binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val layout = LinearLayoutManager(this)
        this.binding?.recyclerView?.layoutManager = layout

        this.binding?.recyclerView?.setHasFixedSize(true)

        this.adapter = StudentsAdapter(Model.shared.students)

        this.adapter?.listener = object: OnItemClickListener {
            override fun onStudentItemClick(student: Student) {
                presentToastFor(student)
            }
        }

        this.binding?.recyclerView?.adapter = adapter

        this.binding?.addStudentButton?.setOnClickListener {
            val intent = Intent(this@StudentListActivity, AddStudentActivity::class.java)
            this.getContent.launch(intent)
        }
    }

    private fun presentToastFor(student: Student){
        val presentStatus = if (student.isPresent) "present" else "absent"

        Toast.makeText(this@StudentListActivity, "${student.name} is $presentStatus",
            Toast.LENGTH_SHORT).show()
    }
}