package com.example.androidtraining.data.local.xml

import android.content.Context
import android.content.SharedPreferences
import com.example.androidtraining.app.Either
import com.example.androidtraining.domain.User
import com.example.androidtraining.app.errors.ErrorApp
import com.example.androidtraining.app.left
import com.example.androidtraining.app.right
import com.google.gson.Gson

class UserXmlLocalDataSource(private val context: Context) {

    val sharedPref = context.getSharedPreferences("UserSaved", Context.MODE_PRIVATE)

    private val gson = Gson()

    fun save(user: User): Either<ErrorApp, User> {
        val editor = sharedPref.edit()

        return try {

            editor.putString(user.name, gson.toJson(user))
            editor.apply()

            user.right()

        } catch (ex: Exception) {
            ErrorApp.UnknowErrorApp.left()
        }
    }

    fun getAll(): Either<ErrorApp, List<User>> {
        val users: MutableList<User> = mutableListOf()

        return try {
            sharedPref.all.forEach {
                users.add(gson.fromJson(it.value as String, User::class.java))
            }
            users.right()

        } catch (ex: Exception) {
            ErrorApp.UnknowErrorApp.left()
        }
    }
}