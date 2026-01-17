package com.example.studentapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.classworkactivity.models.Model
import com.example.classworkactivity.models.Student
import com.example.studentapp.databinding.ActivityStudentsRecyclerViewBinding

class StudentsRecyclerViewActivity : AppCompatActivity() {
    var binding: ActivityStudentsRecyclerViewBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        this.binding = ActivityStudentsRecyclerViewBinding.inflate(layoutInflater)

        setContentView(this.binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val layout = LinearLayoutManager(this)
        this.binding?.recyclerView?.layoutManager = layout

        this.binding?.recyclerView?.setHasFixedSize(true)

        val adapter = StudentsAdapter(Model.shared.students)

        adapter.listener = object: OnItemClickListener {
            override fun onStudentItemClick(student: Student) {
                presentToastFor(student)
            }
        }

        this.binding?.recyclerView?.adapter = adapter

    }

    private fun presentToastFor(student: Student){
        val presentStatus = if (student.isPresent) "present" else "absent"

        Toast.makeText(this@StudentsRecyclerViewActivity, "${student.name} is $presentStatus",
            Toast.LENGTH_SHORT).show()
    }
}