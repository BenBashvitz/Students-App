package com.example.studentapp.models

class Model private constructor() {
    val students = mutableListOf<Student>()

    init {
        for (i in 1 .. 20) {
            val student = Student(
                 "Student $i",
                "ID${1000 + i}",
                "05299999${20 + i}",
                "Rishon Lezion",
                false
            )

            students.add(student)
        }
    }

    companion object {
        val shared = Model()
    }
}