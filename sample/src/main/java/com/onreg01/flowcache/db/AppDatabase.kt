package com.onreg01.flowcache.db

import androidx.room.*
import androidx.room.Database
import com.onreg01.flowcache.App
import java.time.Instant

@Database(entities = [TodoEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}

class Converters {
    @TypeConverter
    fun fromInstant(instant: Instant) = instant.toEpochMilli()

    @TypeConverter
    fun toInstant(long: Long) = Instant.ofEpochMilli(long)
}

object Database {
    private val appDatabase by lazy {
        Room.databaseBuilder(App.CONTEXT, AppDatabase::class.java, "app_db").build()
    }

    val todoDao by lazy {
        appDatabase.todoDao()
    }
}