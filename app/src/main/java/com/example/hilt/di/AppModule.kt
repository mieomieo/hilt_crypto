package com.example.hilt.di

import android.app.Application
import androidx.room.Room
import com.example.hilt.database.ContactDatabase
import com.example.hilt.database.dao.ContactDao
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
    fun provideContactDatabase(application: Application) : ContactDatabase {
        return Room.databaseBuilder(
            application.applicationContext,ContactDatabase::class.java,"contact_db"
        ).build()
    }
    @Provides
    @Singleton
    fun provideContactDao(db:ContactDatabase):ContactDao{
        return db.contactDao()
    }
}