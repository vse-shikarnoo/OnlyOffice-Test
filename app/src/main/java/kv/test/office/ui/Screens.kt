package kv.test.office.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kv.test.office.R
import kv.test.office.ui.theme.OfficeTheme


@Composable
fun AuthScreen(modifier: Modifier = Modifier) {

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
        EditTextCompose(R.string.placeholder_profile, {})
        EditTextCompose(R.string.placeholder_email, {})
        EditTextCompose(R.string.placeholder_password, {})
        IndeterminateCircularIndicator(modifier = Modifier.padding(horizontal = 80.dp))
        ErrorCompose()
    }
}


@Composable
fun EditTextCompose(
    @StringRes text: Int,
    onValueChanged: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = "",
        onValueChange = { onValueChanged() },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        placeholder = {
            Text(stringResource(text))
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
fun IndeterminateCircularIndicator(
    modifier: Modifier = Modifier
) {
    var loading by remember { mutableStateOf(false) }

    Button(onClick = { loading = !loading },modifier.fillMaxWidth()) {
        if (!loading){
            Text(stringResource(R.string.placeholder_login))
        }else{
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
            R.string.placeholder_profile,
            {},
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
        IndeterminateCircularIndicator(
        )
    }
}

