package com.example.androidtraining.data

import com.example.androidtraining.app.Either
import com.example.androidtraining.app.errors.ErrorApp
import com.example.androidtraining.app.right
import com.example.androidtraining.data.local.xml.UserXmlLocalDataSource
import com.example.androidtraining.domain.User
import com.example.androidtraining.domain.UserRepository

class UserDataRepository(private val localDataSource: UserXmlLocalDataSource) : UserRepository {

    override fun save(user: User): Either<ErrorApp, User> {
        return localDataSource.save(user)
    }
}