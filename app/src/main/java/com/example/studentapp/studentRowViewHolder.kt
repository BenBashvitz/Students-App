package com.example.studentapp

import androidx.recyclerview.widget.RecyclerView
import com.example.classworkactivity.models.Student
import com.example.studentapp.databinding.StudentRowLayoutBinding
import kotlin.apply
import kotlin.let

class StudentRowViewHolder(
    private val binding: StudentRowLayoutBinding,
    private val listener: OnItemClickListener?
): RecyclerView.ViewHolder(binding.root) {
    private var student: Student? = null

    init {
        binding.checkbox.setOnClickListener {
            student?.isPresent = binding.checkbox.isChecked
        }

        itemView.setOnClickListener {
            student?.let { student ->
                listener?.onStudentItemClick(student)
            }
        }
    }

    fun bind(student: Student, position: Int) {
        this.student = student

        binding.nameTextView.text = student.name
        binding.idTextView.text = student.id
        binding.checkbox.apply {
            isChecked = student.isPresent
            tag = position
        }
    }

}