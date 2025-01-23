package com.example.studentsrecyclerapp

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentsrecyclerapp.models.Model
import com.example.studentsrecyclerapp.models.Student

class EditStudentActivity : AppCompatActivity() {
    private var studentPosition: Int = -1
    private lateinit var student: Student
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        studentPosition = intent.getIntExtra("position", -1)
        if (studentPosition < 0 || studentPosition >= Model.shared.students.size) {
            finish()
            return
        }

        student = Model.shared.students[studentPosition]
        val nameEditText: EditText = findViewById(R.id.edit_student_activity_name_edit_text)
        val idEditText: EditText = findViewById(R.id.edit_student_activity_id_edit_text)
        val phoneEditText: EditText = findViewById(R.id.edit_student_activity_phone_edit_text)
        val addressEditText: EditText = findViewById(R.id.edit_student_activity_address_edit_text)
        val checkBox: CheckBox = findViewById(R.id.edit_student_activity_checkoBox)

        val cancelButton: Button = findViewById(R.id.edit_student_activity_cancel_button)
        val deleteButton: Button = findViewById(R.id.edit_student_activity_delete_button)
        val saveButton: Button = findViewById(R.id.edit_student_activity_save_button)

        nameEditText.setText(student.name)
        idEditText.setText(student.id)
        phoneEditText.setText(student.phone)
        addressEditText.setText(student.address)
        checkBox.isChecked = student.isChecked

        cancelButton.setOnClickListener {
            finish()
        }

        deleteButton.setOnClickListener {
            Model.shared.students.removeAt(studentPosition)
            Model.shared.saveStudents(this)
            finish()
        }
        saveButton.setOnClickListener {
            val newName = nameEditText.text.toString().trim()
            val newId = idEditText.text.toString().trim()
            val newPhone = phoneEditText.text.toString().trim()
            val newAddress = addressEditText.text.toString().trim()
            val isChecked = checkBox.isChecked

            if (newName.isNotEmpty() && newId.isNotEmpty()) {
                student.name = newName
                student.id = newId
                student.phone = newPhone
                student.address = newAddress
                student.isChecked = isChecked

                Model.shared.students[studentPosition] = student
                Model.shared.saveStudents(this)
                finish()
            } else {
            }
        }
    }
}
