package br.com.sticup.mvvm

import android.app.Application
import android.arch.persistence.room.Room
import br.com.sticup.mvvm.repository.AlbumPageRepository
import br.com.sticup.mvvm.repository.AlbumRepository
import br.com.sticup.mvvm.repository.StickerRepository
import br.com.sticup.mvvm.repository.UserRepository
import br.com.sticup.mvvm.repository.api.AlbumApi
import br.com.sticup.mvvm.repository.api.AlbumPageApi
import br.com.sticup.mvvm.repository.api.StickerApi
import br.com.sticup.mvvm.repository.api.UserApi
import br.com.sticup.mvvm.repository.db.AppDatabase
import br.com.sticup.mvvm.viewmodel.AlbumListViewModel
import br.com.sticup.mvvm.viewmodel.AlbumPageListViewModel
import br.com.sticup.mvvm.viewmodel.StickerListViewModel
import br.com.sticup.mvvm.viewmodel.UserListViewModel
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

        //AlbumAPI
        private lateinit var albumApi: AlbumApi
        private lateinit var albumRepository: AlbumRepository
        private lateinit var albumListViewModel: AlbumListViewModel
//        fun injectAlbumListViewModel() = albumListViewModel

        //AlbumPageAPI
        private lateinit var albumPageApi: AlbumPageApi
        private lateinit var albumPageRepository: AlbumPageRepository
        private lateinit var albumPageListViewModel: AlbumPageListViewModel
//        fun injectAlbumPageListViewModel() = albumPageListViewModel

        //UserAPI
        private lateinit var userApi: UserApi
        private lateinit var userRepository: UserRepository
        private lateinit var userListViewModel: UserListViewModel
        fun injectUserListViewModel() = userListViewModel


//        fun injectStickerApi() = stickerApi
//        fun injectStickerDao() = appDatabase.stickerDao()
//        fun injectAlbumApi() = albumApi
//        fun injectAlbumDao() = appDatabase.userDao()
//        fun injectAlbumPageApi() = albumPageApi
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

        //Start Album Repo
        albumApi = retrofit.create(AlbumApi::class.java)
        albumRepository = AlbumRepository(albumApi, appDatabase.albumDao())
        albumListViewModel = AlbumListViewModel(albumRepository)

        //Start AlbumPage Repo
        albumPageApi = retrofit.create(AlbumPageApi::class.java)
        albumPageRepository = AlbumPageRepository(albumPageApi, appDatabase.albumPageDao())
        albumPageListViewModel = AlbumPageListViewModel(albumPageRepository)

        //Start User Repo
        userApi = retrofit.create(UserApi::class.java)
        userRepository = UserRepository(userApi, appDatabase.userDao())
        userListViewModel = UserListViewModel(userRepository)
    }
}
