/*
 * Copyright 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.platform.ui.share.sender

import android.content.ContentProvider
import android.content.ContentValues
import android.content.res.AssetFileDescriptor
import android.database.Cursor
import android.net.Uri
import android.webkit.MimeTypeMap
import androidx.core.net.toUri

/**
 * Exposes asset files in this app with content URIs.
 *
 * For now, it only exposes JPEG images stored in
 * "${PROJECT_HOME}/samples/graphics/ultrahdr/src/main/assets/gainmaps"
 * with URIs starting with
 * "content://com.example.platform.ui.share.sender.provider/image/".
 *
 * In order to expose files in your data or cache directory, use
 * [androidx.core.content.FileProvider].
 */
class AssetFileProvider : ContentProvider() {

    companion object {
        fun getUriForFilename(filename: String): Uri {
            return "content://com.example.platform.ui.share.sender.provider/image/$filename".toUri()
        }
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun getType(uri: Uri): String? {
        val segments = uri.pathSegments
        return when (segments[0]) {
            "image" -> MimeTypeMap.getSingleton().getMimeTypeFromExtension("jpg")
            else -> "application/octet-stream"
        }
    }

    override fun openAssetFile(uri: Uri, mode: String): AssetFileDescriptor? {
        val segments = uri.pathSegments
        return when (segments[0]) {
            "image" -> {
                val filename = segments[1]
                context?.resources?.assets?.openFd("gainmaps/$filename")
            }

            else -> null
        }
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?,
    ): Cursor? {
        throw UnsupportedOperationException("No query")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        throw UnsupportedOperationException("No insert")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?,
    ): Int {
        throw UnsupportedOperationException("No update")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        throw UnsupportedOperationException("No delete")
    }
}
