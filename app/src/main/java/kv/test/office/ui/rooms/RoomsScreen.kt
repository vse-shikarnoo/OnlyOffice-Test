package kv.test.office.ui.rooms

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kv.test.office.R
import kv.test.office.data.model.FileInfo
import kv.test.office.data.model.FolderInfo
import kv.test.office.ui.theme.OfficeTheme

@Composable
fun RoomsScreen(
    viewModel: RoomsViewModel = viewModel()
) {

    Column(
        modifier = Modifier
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
                        viewModel.getStart()
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
                if (!viewModel.isRoot.value) {
                    Spacer(Modifier.weight(0.5f))
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                viewModel.getFiles(viewModel.docsState.value.current.parentId)
                            }
                            .weight(0.5f)
                    )
                } else {
                    Spacer(Modifier.weight(1f))
                }
                Text(
                    text = stringResource(R.string.title_rooms),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(1f)
                )
                LazyColumn(
                    modifier = Modifier.weight(5f),

                    ) {
                    val state = viewModel.docsState.value
                    items(state.folders + state.files) { item ->
                        when (item) {
                            is FolderInfo -> {
                                ColumnItem(
                                    imageVector = ImageVector.vectorResource(R.drawable.folder_outline),
                                    title = item.title,
                                    size = "",
                                    onClick = {
                                        viewModel.getFiles(item.id)
                                    }
                                )
                            }

                            is FileInfo -> {
                                ColumnItem(
                                    imageVector = Icons.Default.Menu,
                                    title = item.title,
                                    size = "",
                                    onClick = {

                                    }
                                )
                            }
                        }
                        Divider(color = MaterialTheme.colorScheme.secondary, thickness = 1.dp)
                    }
                }
            }
        }
    }
}

@Composable
fun ColumnItem(
    imageVector: ImageVector,
    title: String,
    size: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {

        Row(
            modifier = modifier
                .defaultMinSize(minHeight = 56.dp)
                .clickable {
                    onClick()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = imageVector,
                contentDescription = null,
                contentScale = ContentScale.Inside,
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.background),
                modifier = Modifier
                    .padding(8.dp)
                    .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
                    .clip(
                        CircleShape
                    )
                    .scale(0.8f)
                    .size(32.dp)
            )
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 8.dp)
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = size,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(end = 16.dp)
            )
        }
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun RoomsScreenPreview() {
    OfficeTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            RoomsScreen(
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun ColumnItemPreview() {
    ColumnItem(imageVector = ImageVector.vectorResource(R.drawable.folder_outline),
        title = "File",
        size = "140.79 KB",
        onClick = {

        })
}