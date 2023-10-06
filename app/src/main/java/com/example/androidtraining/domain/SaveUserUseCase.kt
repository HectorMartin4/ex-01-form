package com.example.androidtraining.domain

import com.example.androidtraining.app.Either
import com.example.androidtraining.app.errors.ErrorApp

class SaveUserUseCase(private val repository: UserRepository) {

    operator fun invoke(user: User): Either<ErrorApp, User> {
        return repository.save(user)
    }
}