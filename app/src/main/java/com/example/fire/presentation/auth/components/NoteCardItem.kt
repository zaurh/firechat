package com.example.fire.presentation.auth.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fire.DestinationScreen
import com.example.fire.R
import com.example.fire.data.NoteData
import com.example.fire.presentation.auth.AuthViewModel

@Composable
fun NoteCardItem(
    noteData: NoteData,
    viewModel: AuthViewModel
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(
                if (noteData.userId == viewModel.userData.value?.username) colorResource(id = R.color.yasil) else Color.DarkGray
            )
            .clickable {}) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalAlignment =
            if (noteData.userId == viewModel.userData.value?.username) Alignment.End else Alignment.Start
        ) {
            Text(
                text = "${noteData.userId}",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White
            )
            Text(text = "${noteData.title}", color = Color.White)

        }


    }
}