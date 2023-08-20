package com.example.todolistappmvvm.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolistappmvvm.data.ToDoRepository
import com.example.todolistappmvvm.data.ToDoRepositoryImpl
import com.example.todolistappmvvm.data.TodoDatabase
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
    fun provideTodoDatabase(app: Application): TodoDatabase {
        return Room.databaseBuilder(
            app,
            TodoDatabase::class.java,
            "todo_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db: TodoDatabase): ToDoRepository {
        return ToDoRepositoryImpl(db.dao)
    }
}