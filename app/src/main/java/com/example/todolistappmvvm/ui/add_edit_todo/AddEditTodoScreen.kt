package com.example.todolistappmvvm.ui.add_edit_todo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolistappmvvm.utils.AddEditTodoEvent
import com.example.todolistappmvvm.utils.UiEvent

@Composable
fun AddEditTodoScreen(
    onPopBackStack: () -> Unit,
    viewModel: AddEditTodoViewModel
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(
        key1 = true,
        block = {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.PopBackStack -> {
                    onPopBackStack()
                }

                is UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.msg,
                        actionLabel = event.action
                    )
                }

                else -> Unit
            }
        }
    })

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.onEvent(AddEditTodoEvent.OnSaveTodoClick) }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            TextField(
                value = viewModel.title,
                onValueChange = { viewModel.onEvent(AddEditTodoEvent.OnTitleChange(it)) },
                placeholder = { Text(text = "Title") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.size(8.dp))

            TextField(
                value = viewModel.description,
                onValueChange = { viewModel.onEvent(AddEditTodoEvent.OnDescriptionChange(it)) },
                placeholder = { Text(text = "Description") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                maxLines = 5
            )
        }
    }
}