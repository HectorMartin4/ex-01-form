package com.example.androidtraining.app.errors

sealed class ErrorApp {
    object InternetErrorApp : ErrorApp()
    object DatabaseErrorApp : ErrorApp()
    object UnknowErrorApp : ErrorApp()
}