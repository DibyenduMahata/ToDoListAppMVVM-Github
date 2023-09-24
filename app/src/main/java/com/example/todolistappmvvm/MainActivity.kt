package com.example.todolistappmvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todolistappmvvm.ui.add_edit_todo.AddEditTodoScreen
import com.example.todolistappmvvm.ui.add_edit_todo.AddEditTodoViewModel
import com.example.todolistappmvvm.ui.theme.ToDoListAppMVVMTheme
import com.example.todolistappmvvm.ui.todo_list.TodoListScreen
import com.example.todolistappmvvm.ui.todo_list.TodoListViewModel
import com.example.todolistappmvvm.utils.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val todoListViewModel by viewModels<TodoListViewModel>()
    private val addEditTodoListViewModel by viewModels<AddEditTodoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoListAppMVVMTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Routes.TODO_LIST
                ) {
                    composable(Routes.TODO_LIST) {
                        TodoListScreen(onNavigate = { navController.navigate(it.route) },
                            viewModel = todoListViewModel)
                    }
                    composable(
                        route = "${Routes.ADD_EDIT_TODO}?todoId={todoId}",
                        arguments = listOf(
                            navArgument(name = "todoId") {
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ) {
                        AddEditTodoScreen(
                            onPopBackStack = { navController.popBackStack() },
                            viewModel = addEditTodoListViewModel
                        )
                    }
                }
            }
        }
    }
}