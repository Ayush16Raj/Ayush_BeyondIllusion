package com.example.ayushassignment.room

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // The module will live as long as the app
object RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context.applicationContext,
            Database::class.java,
            "app_database" // Name of the database file
        ).build()
    }

    @Provides
    fun provideRoomDao(database: Database): RoomDao {
        return database.roomDao()
    }
}
