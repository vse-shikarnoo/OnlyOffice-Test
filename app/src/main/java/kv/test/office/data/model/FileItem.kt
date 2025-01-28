package kv.test.office.data.model

data class FileItem(
    val id: String,
    val title: String,
    val created: String,
    val updated: String,
    val fileType: String,
    val folderId: String?
)
