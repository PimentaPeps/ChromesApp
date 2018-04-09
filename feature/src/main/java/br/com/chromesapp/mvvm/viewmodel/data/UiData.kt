package br.com.chromesapp.mvvm.viewmodel.data

import br.com.chromesapp.mvvm.repository.data.User

data class UsersList(val users: List<User>, val message: String, val error: Throwable? = null)
