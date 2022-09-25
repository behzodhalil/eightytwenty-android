package uz.behzod.eightytwenty.domain.interactor.manager

import uz.behzod.eightytwenty.data.manager.PermissionManager
import javax.inject.Inject

class ReadStorePermissionImpl @Inject constructor(
    private val manager: PermissionManager
) : ReadStorePermission {

    override fun invoke(): Boolean {
        return manager.readStoragePermission()
    }
}