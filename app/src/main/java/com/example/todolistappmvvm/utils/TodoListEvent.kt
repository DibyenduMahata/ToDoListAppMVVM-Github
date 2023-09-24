package com.example.todolistappmvvm.utils

import com.example.todolistappmvvm.data.Todo

sealed class TodoListEvent {
    data class OnDeleteToDoClick(val todo: Todo): TodoListEvent()
    data class OnDoneChange(val todo: Todo, val isDone: Boolean): TodoListEvent()
    object OnUndoDeleteClick: TodoListEvent()
    data class OnTodoClick(val todo: Todo): TodoListEvent()
    object OnAddTodoClick: TodoListEvent()
}