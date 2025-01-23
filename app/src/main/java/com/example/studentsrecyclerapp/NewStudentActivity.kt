package com.example.studentsrecyclerapp

import android.widget.Button
import android.widget.EditText
import android.widget.CheckBox
import android.widget.TextView
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentsrecyclerapp.models.Model
import com.example.studentsrecyclerapp.models.Student

class NewStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val saveButton: Button = findViewById(R.id.add_new_student_activity_save_button)
        val cancelButton: Button = findViewById(R.id.add_new_student_activity_cancel_button)

        val nameEditText: EditText = findViewById(R.id.add_new_student_activity_name_edit_text)
        val idEditText: EditText = findViewById(R.id.add_new_student_activity_id_edit_text)
        val phoneEditText: EditText = findViewById(R.id.add_new_student_activity_phone_edit_text)
        val adressEditText: EditText = findViewById(R.id.add_new_student_activity_adress_edit_text)
        val checkBox: CheckBox = findViewById(R.id.add_new_student_activity_checkoBox)

        val savedMessageTextView: TextView = findViewById(R.id.add_new_student_activity_save_messeage_textView)

        cancelButton.setOnClickListener {
            finish()
        }

        saveButton.setOnClickListener {

            val name = nameEditText.text.toString().trim()
            val id = idEditText.text.toString().trim()
            val phone = phoneEditText.text.toString().trim()
            val address = adressEditText.text.toString().trim()
            val isChecked = checkBox.isChecked

            if (name.isNotEmpty() && id.isNotEmpty() && phone.isNotEmpty() && address.isNotEmpty()) {
                val newStudent = Student(
                    name = name,
                    id = id,
                    avatarUrl = "default_avatar_url",
                    isChecked = isChecked,
                    phone = phone,
                    address = address
                )
                Model.shared.students.add(newStudent)
                Model.shared.saveStudents(this)

                savedMessageTextView.text = "Student: $name (ID: $id) is saved!"
                setResult(RESULT_OK)
                finish()
            } else {
                savedMessageTextView.text = "Please fill in all fields."
            }
        }
    }
}
