package com.xboxgamecollection.app.database

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.xboxgamecollection.app.AndroidApp

internal actual fun createDataStore(): DataStore<Preferences> = createDataStore(
    producePath = { AndroidApp.INSTANCE.applicationContext.filesDir.resolve(dataStoreFileName).absolutePath }
)
