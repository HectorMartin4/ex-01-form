package com.example.androidtraining.domain

import com.example.androidtraining.app.Either
import com.example.androidtraining.app.errors.ErrorApp

interface UserRepository {

    fun save(user: User): Either<ErrorApp, User>
    fun getAll(): Either<ErrorApp, List<User>>

}