package com.example.androidtraining.domain

import com.example.androidtraining.app.Either
import com.example.androidtraining.app.errors.ErrorApp

class GetUserUseCase(private val repository: UserRepository) {

    operator fun invoke(): Either<ErrorApp, List<User>> {
        return repository.getAll()
    }
}