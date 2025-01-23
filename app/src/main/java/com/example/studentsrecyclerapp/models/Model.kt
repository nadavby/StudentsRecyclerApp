package com.example.studentsrecyclerapp.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Model private constructor() {
    // Start with an empty list
    val students: MutableList<Student> = mutableListOf()

    companion object {
        val shared = Model()
    }


    fun loadStudents(context: Context) {
        val sharedPref = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val json = sharedPref.getString("students_key", "[]") // Defaults to "[]" if not found

        val gson = Gson()
        val type = object : TypeToken<MutableList<Student>>() {}.type

        val loadedList: MutableList<Student> = gson.fromJson(json, type) ?: mutableListOf()

        students.clear()
        students.addAll(loadedList)
    }

    fun saveStudents(context: Context) {
        val sharedPref = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        val gson = Gson()
        val json = gson.toJson(students)

        editor.putString("students_key", json)
        editor.apply()
    }
}
