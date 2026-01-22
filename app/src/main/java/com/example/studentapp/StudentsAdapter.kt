package com.example.studentapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentapp.models.Student
import com.example.studentapp.databinding.StudentRowLayoutBinding

interface OnItemClickListener {
    fun onStudentItemClick(student: Student)
}

class StudentsAdapter (
    private var students: List<Student>
): RecyclerView.Adapter<StudentRowViewHolder>() {
    var listener: OnItemClickListener? = null
    override fun getItemCount(): Int = students.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentRowViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val binding = StudentRowLayoutBinding.inflate(inflator, parent, false)

        return StudentRowViewHolder(binding, listener)
    }

    override fun onBindViewHolder(
        holder: StudentRowViewHolder,
        position: Int
    ) {
        holder.bind(students[position], position)
    }
}