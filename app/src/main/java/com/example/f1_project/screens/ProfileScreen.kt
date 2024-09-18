package com.example.f1_project.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.f1_project.R // Certifique-se de ter uma imagem no diretório res/drawable

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Espaçamento para centralizar a imagem acima do centro
        Spacer(modifier = Modifier.height(100.dp))

        // Imagem de perfil (você pode usar um drawable local, por exemplo R.drawable.placeholder)
        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_camera), // Usa uma imagem do próprio sistema Android
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape) // Faz a imagem ficar circular
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Nome do usuário
        Text(text = "Nome do Usuário", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(8.dp))

        // Equipe favorita
        Text(text = "Equipe Favorita: Red Bull Racing", style = MaterialTheme.typography.bodyMedium)
    }
}
