package com.example.todolistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolistapp.ui.theme.ToDoListAppTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.ui.Alignment
import androidx.compose.foundation.shape.RoundedCornerShape

// Data class Task dengan isCompleted sebagai var
data class Task(var name: String, var isCompleted: Boolean = false)

class MainActivity : ComponentActivity() {
    private val todoList = mutableListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoListAppTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(navController, todoList)
                    }
                    composable("addTask") {
                        AddTaskScreen(navController, todoList)
                    }
                    composable("editTask/{index}") { backStackEntry ->
                        val index = backStackEntry.arguments?.getString("index")?.toIntOrNull()
                        if (index != null) {
                            EditTaskScreen(navController, todoList, index)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, todoList: MutableList<Task>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Daftar Tugas") }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            // Menambahkan gambar di atas daftar tugas
            Image(
                painter = painterResource(id = R.drawable.to_do_list), // Ganti dengan nama gambar kamu
                contentDescription = "Gambar Ilustrasi",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp) // Sesuaikan tinggi gambar
            )

            LazyColumn(
                modifier = Modifier.weight(1f) // Mengisi ruang yang tersisa
            ) {
                items(todoList) { task ->
                    val index = todoList.indexOf(task)
                    // Menggunakan Row untuk setiap tugas
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .border(BorderStroke(2.dp, color = Color(0xFF37AFE1)), // Warna border yang diinginkan
                                shape = RoundedCornerShape(8.dp)) // Border dengan sudut melengkung
                            .padding(8.dp), // Menambahkan padding di dalam border
                        verticalAlignment = Alignment.CenterVertically // Menjaga agar item dalam satu garis
                    ) {
                        // Checkbox untuk menandai tugas selesai
                        Checkbox(
                            checked = task.isCompleted,
                            onCheckedChange = { isChecked ->
                                task.isCompleted = isChecked
                            }
                        )
                        Text(
                            task.name,
                            modifier = Modifier.weight(1f).padding(start = 8.dp),
                            style = if (task.isCompleted) {
                                androidx.compose.ui.text.TextStyle(textDecoration = TextDecoration.LineThrough)
                            } else {
                                androidx.compose.ui.text.TextStyle()
                            }
                        )
                        // Tombol Edit di sebelah kanan
                        Button(
                            onClick = {
                                navController.navigate("editTask/$index")
                            },
                            modifier = Modifier.padding(start = 16.dp) // Memberi jarak dengan teks tugas
                        ) {
                            Text("Edit")
                        }
                    }
                }
            }
            Button(
                onClick = { navController.navigate("addTask") },
                modifier = Modifier
                    .fillMaxWidth() // Membuat tombol lebar penuh
                    .padding(16.dp) // Menambahkan padding
            ) {
                Text("Tambah Tugas")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(navController: NavHostController, todoList: MutableList<Task>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tambah Tugas") }
            )
        }
    ) { innerPadding ->
        val newTask = remember { mutableStateOf("") }

        Column(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            TextField(
                value = newTask.value,
                onValueChange = { newTask.value = it },
                label = { Text("Tugas Baru") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.weight(1f)) // Spacer untuk mengisi ruang

            Button(
                onClick = {
                    if (newTask.value.isNotBlank()) {
                        todoList.add(Task(newTask.value, isCompleted = false))
                        newTask.value = ""
                        navController.popBackStack()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth() // Membuat tombol lebar penuh
                    .padding(16.dp)
            ) {
                Text("Simpan Tugas")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTaskScreen(navController: NavHostController, todoList: MutableList<Task>, index: Int) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Tugas") }
            )
        }
    ) { innerPadding ->
        val editedTask = remember { mutableStateOf(todoList[index].name) }

        Column(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            // Menampilkan status selesai
            Text(
                text = "Status Selesai: ${if (todoList[index].isCompleted) "Selesai" else "Belum Selesai"}",
                modifier = Modifier.padding(16.dp)
            )

            // TextField untuk mengedit tugas
            TextField(
                value = editedTask.value,
                onValueChange = { editedTask.value = it },
                label = { Text("Edit Tugas") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.weight(1f)) // Spacer untuk mengisi ruang

            // Tombol untuk menyimpan perubahan
            Button(
                onClick = {
                    if (editedTask.value.isNotBlank()) {
                        todoList[index].name = editedTask.value
                        navController.popBackStack()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth() // Membuat tombol lebar penuh
                    .padding(16.dp)
            ) {
                Text("Simpan Perubahan")
            }
        }
    }
}
