package com.example.classworkactivity.models

class Model private constructor() {
    val students = mutableListOf<Student>()

    init {
        for (i in 1 .. 20) {
            val student = Student(
                 "Student $i",
                "ID${1000 + i}",
                "https://i.pravatar.cc/150?img=$i",
                false
            )

            students.add(student)
        }
    }

    companion object {
        val shared = Model()
    }
}