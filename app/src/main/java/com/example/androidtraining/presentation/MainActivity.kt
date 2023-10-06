package com.example.androidtraining.presentation

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.androidtraining.data.local.xml.UserXmlLocalDataSource
import com.example.androidtraining.domain.User
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.androidtraining.R
import com.example.androidtraining.data.UserDataRepository
import com.example.androidtraining.domain.GetUserUseCase
import com.example.androidtraining.domain.SaveUserUseCase

class MainActivity : AppCompatActivity() {

    val viewModels: MainViewModel by lazy {
        MainViewModel(
            SaveUserUseCase(UserDataRepository(UserXmlLocalDataSource(this))),
            GetUserUseCase(UserDataRepository(UserXmlLocalDataSource(this))))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        saveUser()
        clean()
        setupObservers()
        viewModels.loadUser()
    }

    fun saveUser() {
        val name = findViewById<EditText>(R.id.name).text
        val surname = findViewById<EditText>(R.id.surname).text

        findViewById<Button>(R.id.button_save).setOnClickListener {

            UserXmlLocalDataSource(this).save(User("$name", "$surname"))
            viewModels.saveUser(User("$name", "$surname"))
        }

    }

    fun getUsers() {

        findViewById<Button>(R.id.button_get).setOnClickListener {
            val users = UserXmlLocalDataSource(this).getAll()

            Log.d("@dev", "$users")

        }
    }


    fun setupObservers() {

        val observer = Observer<MainViewModel.UiState> {observer ->
            findViewById<Button>(R.id.button_get).setOnClickListener{
                Log.d("@dev", "User: $observer")
            }
        }
        viewModels.uiState.observe(this, observer)
    }

    fun clean(){
        findViewById<Button>(R.id.button_clean).setOnClickListener{
            findViewById<EditText>(R.id.name).setText("")
            findViewById<EditText>(R.id.surname).setText("")
        }
    }
}