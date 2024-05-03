package com.example.hilt.di

import android.app.Application
import androidx.room.Room
import com.example.hilt.database.CryptoDatabase
import com.example.hilt.database.dao.CryptoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideCryptoDatabase(application: Application) : CryptoDatabase {
        return Room.databaseBuilder(
            application.applicationContext,CryptoDatabase::class.java,"crypto_db"
        ).build()
    }
    @Provides
    @Singleton
    fun provideCryptoDao(db:CryptoDatabase):CryptoDao{
        return db.CryptoDao()
    }
}