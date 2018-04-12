package br.com.sticup.mvvm.repository

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import br.com.sticup.mvvm.repository.api.UserApi
import br.com.sticup.mvvm.repository.data.User
import br.com.sticup.mvvm.repository.db.UserDao
import timber.log.Timber

class UserRepository(val userApi: UserApi, val userDao: UserDao) {

    fun getUsers(): Observable<List<User>> {
        return Observable.concatArray(
                getUsersFromDb(),
                getUsersFromApi())
    }


    fun getUsersFromDb(): Observable<List<User>> {
        return userDao.getUsers().filter { it.isNotEmpty() }
                .toObservable()
                .doOnNext {
                    Timber.d("Dispatching ${it.size} stickers from DB...")
                }
    }

    fun getUsersFromApi(): Observable<List<User>> {
        return userApi.getUser()
                .doOnNext {
                    Timber.d("Dispatching ${it.size} stickers from API...")
                    storeUsersInDb(it)
                }
    }

    fun storeUsersInDb(users: List<User>) {
        Observable.fromCallable { userDao.insertAll(users) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                    Timber.d("Inserted ${users.size} stickers from API in DB...")
                }
    }

}
