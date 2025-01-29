package kv.test.office.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import kv.test.office.R
import kv.test.office.ui.auth.ButtonProgress
import kv.test.office.ui.theme.OfficeTheme

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel(),
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier
) {

    if (viewModel.logoutState.value) {
        onNavigate()
    }



    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        if (viewModel.loadingState.value) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(top = 100.dp)
                    .align(Alignment.CenterHorizontally)
            )
        } else {
            if (viewModel.errorState.value) {
                Button(
                    modifier = Modifier
                        .padding(top = 100.dp)
                        .align(Alignment.CenterHorizontally),
                    onClick = {
                        viewModel.getProfile()
                    },
                    colors = ButtonColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer,
                        contentColor = MaterialTheme.colorScheme.onErrorContainer,
                        disabledContainerColor = MaterialTheme.colorScheme.errorContainer,
                        disabledContentColor = MaterialTheme.colorScheme.onErrorContainer
                    )
                ) {
                    Text(stringResource(R.string.placeholder_retry))
                }
            } else {
                Spacer(modifier.weight(1f))

                Text(
                    text = stringResource(R.string.title_profile),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = modifier.weight(1f)
                )
                Column(
                    modifier
                        .weight(3f)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = viewModel.profileState.value.avatarUrl,
                        contentDescription = "",
                        placeholder = painterResource(id = R.drawable.outline_person_24),
                        error = painterResource(id = R.drawable.outline_person_24),
                        colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary),
                        modifier = modifier
                            .background(
                                color = MaterialTheme.colorScheme.primaryContainer,
                                shape = CircleShape
                            )
                            .clip(
                                CircleShape
                            )
                            .size(100.dp)
                    )
                    Text(
                        text = viewModel.profileState.value.displayName,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(vertical = 24.dp)
                    )
                    Text(
                        text = "E-mail",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = viewModel.profileState.value.email,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    ButtonProgress(
                        text = R.string.placeholder_logout,
                        isEnabled = !viewModel.loadingState.value,
                        isLoading = viewModel.loadingState.value,
                        isError = viewModel.errorState.value,
                        modifier = Modifier
                            .padding(horizontal = 80.dp)
                            .padding(top = 16.dp),
                        onClick = {
                            viewModel.logout()
                        }
                    )
                }
            }
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