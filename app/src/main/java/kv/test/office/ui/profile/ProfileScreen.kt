package kv.test.office.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import kv.test.office.R
import kv.test.office.ui.auth.AuthScreen
import kv.test.office.ui.theme.OfficeTheme

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel(),
    onNavigate: () -> Unit
){

    if (viewModel.logoutState.value){
        onNavigate()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Profile"
        )
        AsyncImage(
            model = viewModel.profileState.value.avatarUrl,
            contentDescription = "",
            placeholder = painterResource(id = R.drawable.outline_person_24),
            error = painterResource(id = R.drawable.outline_person_24),
        )
        Text(
            text = viewModel.profileState.value.displayName
        )
        Text(
            text = "E-mail\n"+viewModel.profileState.value.email
        )
        Button(
            onClick = {
                viewModel.logout()
            }
        ) {
            Text(text = "Logout")
        }
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun ProfileScreenPreview() {
    OfficeTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ProfileScreen(
                onNavigate = {}
            )
        }
    }
}