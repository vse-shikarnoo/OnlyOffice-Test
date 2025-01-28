package kv.test.office.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kv.test.office.R
import kv.test.office.ui.theme.OfficeTheme


@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = AuthViewModel()
) {

    var portal by rememberSaveable {
        mutableStateOf("https://testdocspaceportal.onlyoffice.com/")
    }
    var email by rememberSaveable {
        mutableStateOf("1one.test901@gmail.com")
    }
    var password by rememberSaveable {
        mutableStateOf("Testpass123")
    }
    val isError = viewModel.errorState.value
    val isLoading = viewModel.loadingState.value




    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            stringResource(R.string.connect_to_onlyoffice),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.paddingFromBaseline(bottom = 32.dp)
        )
        EditTextCompose(
            placeholder = R.string.placeholder_portal,
            imageVector = Icons.Filled.Build,
            text = portal,
            onValueChanged = {
                portal = it
            },
            isEnabled = !isLoading
        )
        EditTextCompose(
            placeholder = R.string.placeholder_email,
            imageVector = Icons.Filled.Email,
            text = email,
            onValueChanged = {
                email = it
            },
            isEnabled = !isLoading
        )
        EditTextCompose(
            placeholder = R.string.placeholder_password,
            imageVector = Icons.Filled.Lock,
            text = password,
            onValueChanged = {
                password = it
            },
            isEnabled = !isLoading
        )
        ButtonProgress(
            isEnabled = !isLoading,
            isLoading = isLoading,
            modifier = Modifier.padding(horizontal = 80.dp),
            onClick = {
                viewModel.authenticate(portal, email, password)
                viewModel.loadingState.value= true
            })
        if (isError){
            ErrorCompose()
        }
    }
}


@Composable
fun EditTextCompose(
    @StringRes placeholder: Int,
    imageVector: ImageVector,
    text: String,
    onValueChanged: (String) -> Unit,
    isEnabled: Boolean,
    modifier: Modifier = Modifier
) {
    TextField(
        value = text,
        onValueChange = { onValueChanged(it) },
        enabled = isEnabled,
        leadingIcon = {
            Icon(
                imageVector = imageVector,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        placeholder = {
            Text(stringResource(placeholder))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .padding(16.dp)
    )
}

@Composable
fun ErrorCompose(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.errorContainer,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Error", color = MaterialTheme.colorScheme.error)
        }
    }

}

@Composable
fun ButtonProgress(
    isEnabled: Boolean,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Button(onClick = {
        onClick()
    }, modifier.fillMaxWidth(), enabled = isEnabled) {
        if (!isLoading) {
            Text(stringResource(R.string.placeholder_login))
        } else {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier.size(24.dp)
            )
        }
    }


}


@Preview(widthDp = 360, heightDp = 640)
@Composable
fun AuthScreenPreview() {
    OfficeTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            AuthScreen(
                Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 64.dp, bottom = 16.dp)
            )
        }
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun DarkAuthScreenPreview() {
    OfficeTheme(
        darkTheme = true
    ) {
        Surface(color = MaterialTheme.colorScheme.background) {
            AuthScreen(
                Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 64.dp, bottom = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun EditTextComposePreview() {
    OfficeTheme {
        EditTextCompose(
            R.string.placeholder_portal,
            imageVector = Icons.Default.Person,
            text = "",
            {},
            true,
            Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun ErrorComposePreview() {
    OfficeTheme {
        ErrorCompose(
            Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun ProgressComposePreview() {
    OfficeTheme {
        ButtonProgress(
            true, isLoading = false, Modifier.padding(8.dp), {}
        )
    }
}

