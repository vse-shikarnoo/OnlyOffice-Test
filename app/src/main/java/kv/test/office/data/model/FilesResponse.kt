package kv.test.office.data.model

data class FilesResponse(
    val response: FilesInfo
)

data class FilesInfo(
    val files: List<FileInfo> = emptyList(),
    val folders: List<FolderInfo> = emptyList(),
    val current: CurrentInfo = CurrentInfo(),
    val total: Int = 0
)

data class FileInfo(
    val id: Int,
    val contentLength: String,
    val title: String
)

data class FolderInfo(
    val id: Int,
    val filesCount: Int,
    val title: String
)

data class CurrentInfo(
    val id: Int = 0,
    val parentId: Int = 0
)
