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
        this.binding.checkbox.setOnClickListener {
            this.student?.isPresent = binding.checkbox.isChecked
        }

        itemView.setOnClickListener {
            this.student?.let { student ->
                this.listener?.onStudentItemClick(student)
            }
        }
    }

    fun bind(student: Student, position: Int) {
        this.student = student

        this.binding.nameTextView.text = student.name
        this.binding.idTextView.text = student.id
        this.binding.checkbox.apply {
            isChecked = student.isPresent
            tag = position
        }
    }
}