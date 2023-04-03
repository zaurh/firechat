package com.example.fire.presentation.auth.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fire.DestinationScreen
import com.example.fire.presentation.auth.AuthViewModel
import com.example.fire.presentation.auth.components.NoteCardItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: AuthViewModel
) {
    val notes = viewModel.notes.value
    var messageTf by remember { mutableStateOf("") }
    var username = viewModel.userData.value?.username
    var bio = viewModel.userData.value?.bio
    if (bio == null){
        bio = ""
    }
    val scope = rememberCoroutineScope()
    val scrollState = rememberLazyListState()
    var focus = LocalFocusManager.current

    SideEffect {
        scope.launch {
            scrollState.scrollToItem(notes.size)
        }
    }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "$username   $bio")  },
            actions = {
                IconButton(onClick = {
                    focus.clearFocus()
                    navController.navigate(DestinationScreen.EditProfile.route)
                }) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            })
    }, content = {

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyColumn(state = scrollState, modifier = Modifier.weight(9f)) {
                    items(viewModel.notes.value) { note ->
                        NoteCardItem(noteData = note, viewModel)
                    }
                }
                TextField(
                    placeholder = { Text(text = "Message") },
                    modifier = Modifier.fillMaxWidth(),
                    value = messageTf,
                    onValueChange = { messageTf = it },
                    trailingIcon = {
                        IconButton(
                            enabled = messageTf != "",
                            onClick = {
                                viewModel.createMessage(messageTf)
                                messageTf = ""
                            }) {
                            Icon(
                                imageVector = Icons.Default.Send,
                                contentDescription = "",
                                tint = if (messageTf == "") Color.Gray else Color.Blue
                            )
                        }
                    },
                    leadingIcon = {
                        IconButton(
                            onClick = {
                                viewModel.deleteCollection()
                            }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = ""
                            )
                        }
                    }

                )

            }
        }
    })


}