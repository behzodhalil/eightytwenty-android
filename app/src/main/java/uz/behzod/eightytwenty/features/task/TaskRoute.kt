package uz.behzod.eightytwenty.features.task

sealed interface TaskRoute {
    object NewTaskRoute: TaskRoute
    object SearchTaskRoute: TaskRoute
    object FolderRoute: TaskRoute
}