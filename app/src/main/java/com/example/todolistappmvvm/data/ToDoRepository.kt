package com.example.todolistappmvvm.data

import kotlinx.coroutines.flow.Flow

interface ToDoRepository {

    suspend fun insertTodo(todo: Todo)

    suspend fun deleteTodo(todo: Todo)

    suspend fun getTodoById(id: Int): Todo?

    fun getTodos(): Flow<List<Todo>>
}