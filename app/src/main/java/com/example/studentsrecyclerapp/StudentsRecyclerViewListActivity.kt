package com.example.studentsrecyclerapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsrecyclerapp.models.Model
import com.example.studentsrecyclerapp.models.Student

class StudentsRecyclerViewListActivity : AppCompatActivity() {
    private lateinit var studentsAdapter: StudentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students_list)

        Model.shared.loadStudents(this)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val addStudentButton: View = findViewById(R.id.addStudentButton)

        studentsAdapter = StudentsAdapter(
            Model.shared.students,
            onStudentClicked = { position ->
                val student: Student = Model.shared.students[position]
                val intent = Intent(this, StudentDetailsActivity::class.java)
                intent.putExtra("student", student)
                intent.putExtra("position", position)
                startActivity(intent)
            },
            onCheckBoxClicked = { position, isChecked ->
                Model.shared.students[position].isChecked = isChecked

            }
        )
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentsAdapter

        addStudentButton.setOnClickListener {
            val intent = Intent(this, NewStudentActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        studentsAdapter.notifyDataSetChanged()
    }
}
