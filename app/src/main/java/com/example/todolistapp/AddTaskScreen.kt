package com.example.todolistapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AddTaskScreen(navController: NavController) {
    var newTask by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = newTask,
            onValueChange = { newTask = it },
            label = { Text("Tugas Baru") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (newTask.isNotBlank()) {
                    // Kembali ke layar sebelumnya dan mengirimkan data
                    navController.previousBackStackEntry?.savedStateHandle?.set("newTask", newTask)
                    navController.popBackStack()
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Tambah Tugas")
        }
    }
}
