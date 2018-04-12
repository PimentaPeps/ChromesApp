package br.com.sticup.mvvm

import android.app.Application
import android.arch.persistence.room.Room
import br.com.sticup.mvvm.repository.*
import br.com.sticup.mvvm.repository.api.*
import br.com.sticup.mvvm.repository.db.AppDatabase
import br.com.sticup.mvvm.viewmodel.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber


class App : Application() {

    //For the sake of simplicity, for now we use this instead of Dagger
    companion object {
        private lateinit var retrofit: Retrofit
        private lateinit var appDatabase: AppDatabase

        //StickerAPI
        private lateinit var stickerApi: StickerApi
        private lateinit var stickerRepository: StickerRepository
        private lateinit var stickerListViewModel: StickerListViewModel
        fun injectStickerListViewModel() = stickerListViewModel

        //UserStickerAPI
        private lateinit var userStickerApi: UserStickerApi
        private lateinit var userStickerRepository: UserStickerRepository
        private lateinit var userStickerListViewModel: UserStickerListViewModel
        fun injectUserStickerListViewModel() = userStickerListViewModel

        //AlbumAPI
        private lateinit var albumApi: AlbumApi
        private lateinit var albumRepository: AlbumRepository
        private lateinit var albumListViewModel: AlbumListViewModel
//        fun injectAlbumListViewModel() = albumListViewModel

        //UserAlbumAPI
        private lateinit var userAlbumApi: UserAlbumApi
        private lateinit var userAlbumRepository: UserAlbumRepository
        private lateinit var userAlbumListViewModel: UserAlbumListViewModel
        fun injectAlbumListViewModel() = userAlbumListViewModel

        //AlbumPageAPI
        private lateinit var albumPageApi: AlbumPageApi
        private lateinit var albumPageRepository: AlbumPageRepository
        private lateinit var albumPageListViewModel: AlbumPageListViewModel
//        fun injectAlbumPageListViewModel() = albumPageListViewModel

        //UserAlbumPageAPI
        private lateinit var userAlbumPageApi: UserAlbumPageApi
        private lateinit var userAlbumPageRepository: UserAlbumPageRepository
        private lateinit var userAlbumPageListViewModel: UserAlbumPageListViewModel
        fun injectAlbumPageListViewModel() = userAlbumPageListViewModel

        //UserAPI
        private lateinit var userApi: UserApi
        private lateinit var userRepository: UserRepository
        private lateinit var userListViewModel: UserListViewModel
        fun injectUserListViewModel() = userListViewModel


//        fun injectStickerApi() = stickerApi
//        fun injectStickerDao() = appDatabase.stickerDao()
//        fun injectAlbumApi() = albumApi
//        fun injectAlbumDao() = appDatabase.userDao()
//        fun injectAlbumPageApi() = userAlbumPageApi
//        fun injectAlbumPageDao() = appDatabase.userDao()
//        fun injectUserApi() = userApi
//        fun injectUserDao() = appDatabase.userDao()
    }

    override fun onCreate() {
        super.onCreate()
        Timber.uprootAll()
        Timber.plant(Timber.DebugTree())

        retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://randomapi.com/api/")
                .build()

        appDatabase = Room.databaseBuilder(applicationContext,
                AppDatabase::class.java, "mvvm-database.db").build()

        //Start Sticker Repo
        stickerApi = retrofit.create(StickerApi::class.java)
        stickerRepository = StickerRepository(stickerApi, appDatabase.stickerDao())
        stickerListViewModel = StickerListViewModel(stickerRepository)

        //Start UserSticker Repo
        userStickerApi = retrofit.create(UserStickerApi::class.java)
        userStickerRepository = UserStickerRepository(userStickerApi, appDatabase.userStickerDao())
        userStickerListViewModel = UserStickerListViewModel(userStickerRepository)

        //Start Album Repo
        albumApi = retrofit.create(AlbumApi::class.java)
        albumRepository = AlbumRepository(albumApi, appDatabase.albumDao())
        albumListViewModel = AlbumListViewModel(albumRepository)

        //Start UserAlbum Repo
        userAlbumApi = retrofit.create(UserAlbumApi::class.java)
        userAlbumRepository = UserAlbumRepository(userAlbumApi, appDatabase.userAlbumDao())
        userAlbumListViewModel = UserAlbumListViewModel(userAlbumRepository)

        //Start AlbumPage Repo
        albumPageApi = retrofit.create(AlbumPageApi::class.java)
        albumPageRepository = AlbumPageRepository(albumPageApi, appDatabase.albumPageDao())
        albumPageListViewModel = AlbumPageListViewModel(albumPageRepository)

        //Start UserAlbumPage Repo
        userAlbumPageApi = retrofit.create(UserAlbumPageApi::class.java)
        userAlbumPageRepository = UserAlbumPageRepository(userAlbumPageApi, appDatabase.userAlbumPageDao())
        userAlbumPageListViewModel = UserAlbumPageListViewModel(userAlbumPageRepository)

        //Start User Repo
        userApi = retrofit.create(UserApi::class.java)
        userRepository = UserRepository(userApi, appDatabase.userDao())
        userListViewModel = UserListViewModel(userRepository)
    }
}
