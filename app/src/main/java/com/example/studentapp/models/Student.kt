package com.example.studentapp.models

import android.content.Intent

data class Student(
    var name: String,
    var id: String,
    var phone: String,
    var address: String,
    var isPresent: Boolean,
)

fun getStudentFromIntent(intent: Intent, defaultString: String): Student {
    val studentName = intent.getStringExtra("student_name") ?: defaultString
    val studentId = intent.getStringExtra("student_id") ?: defaultString
    val studentPhone = intent.getStringExtra("student_phone") ?: defaultString
    val studentAddress = intent.getStringExtra("student_address") ?: defaultString
    val isStudentPresent = intent.getBooleanExtra("student_present", false)

    return Student(
        studentName,
        studentId,
        studentPhone,
        studentAddress,
        isStudentPresent
    )
}

fun getStudentPositionFromIntent(intent: Intent): Int {
    return intent.getIntExtra("student_index", -1)
}

fun getIsSaveFromIntent(intent: Intent): Boolean {
    return intent.getBooleanExtra("is_save", false)
}

fun passStudentToIntent(intent: Intent, student: Student) {
    intent.putExtra("student_name", student.name)
    intent.putExtra("student_id", student.id)
    intent.putExtra("student_phone", student.phone)
    intent.putExtra("student_address", student.address)
    intent.putExtra("student_present", student.isPresent)
}

fun passStudentPositionToIntent(intent: Intent, position: Int) {
    intent.putExtra("student_index", position)
}

fun passIsSaveToIntent(intent: Intent, isSave: Boolean) {
    intent.putExtra("is_save", isSave)
}
