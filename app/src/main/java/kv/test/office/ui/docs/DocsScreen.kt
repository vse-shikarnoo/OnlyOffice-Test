package kv.test.office.ui.docs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kv.test.office.data.model.FileInfo
import kv.test.office.data.model.FolderInfo
import kv.test.office.ui.theme.OfficeTheme

@Composable
fun DocsScreen(
    viewModel: DocsViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        if (!viewModel.isRoot.value) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                modifier = Modifier.clickable {
                    viewModel.getFiles(viewModel.docsState.value.current.parentId)
                }
            )
        }
        Text(
            text = "Docs"
        )
        LazyColumn {
            val state = viewModel.docsState.value
            items(state.folders + state.files) { item ->
                when (item) {
                    is FolderInfo -> {
                        ColumnItem(
                            imageVector = Icons.Filled.CheckCircle,
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
    Row(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 56.dp)
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null
        )
        Text(text = title)
        Text(text = size)
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun DocsScreenPreview() {
    OfficeTheme() {
        Surface(color = MaterialTheme.colorScheme.background) {
            DocsScreen(
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun ColumnItemPreview() {
    ColumnItem(imageVector = Icons.Default.List, title = "File", size = "140.79 KB",
        onClick = {

        })
}