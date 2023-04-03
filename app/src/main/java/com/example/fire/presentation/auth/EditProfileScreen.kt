package com.example.fire.presentation.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.NavController
import com.example.fire.DestinationScreen
import com.example.fire.common.CircularProgressSpinner

@Composable
fun EditProfileScreen(
    navController: NavController,
    viewModel: AuthViewModel
) {
    val focus = LocalFocusManager.current
    val isLoading = viewModel.isLoading.value
    val userData = viewModel.userData.value
    var usernameTf by rememberSaveable { mutableStateOf(userData?.username ?: "") }
    var bioTf by rememberSaveable { mutableStateOf(userData?.bio ?: "") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = userData?.image ?: "No image", modifier = Modifier.clickable { })
        TextField(enabled = false,value = usernameTf, onValueChange = { usernameTf = it }, label = {
            Text(
                text = "Username"
            )
        })
        TextField(
            maxLines = 1,
            value = bioTf,
            onValueChange = { if (it.length < 15) bioTf = it },
            label = { Text(text = "Bio") }
        )


        Button(onClick = {
            focus.clearFocus()
            viewModel.updateProfileData(username = usernameTf, bio = bioTf)
        }) {
            Text(text = "Save")
        }

        Button(onClick = {
            viewModel.signOut()
            navController.navigate(DestinationScreen.SignIn.route) {
                popUpTo(0)
            }
        }) {
            Text(text = "Sign Out")
        }

        Button(onClick = {
            viewModel.deleteUser()
            navController.navigate(DestinationScreen.SignIn.route) {
                popUpTo(0)
            }
        }) {
            Text(text = "Delete account")
        }
    }


    if (isLoading) {
        CircularProgressSpinner()
    }
}