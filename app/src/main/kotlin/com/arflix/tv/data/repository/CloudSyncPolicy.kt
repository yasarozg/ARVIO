package com.arflix.tv.data.repository

/** Fork policy: cloud traffic only runs from the explicit Settings actions. */
internal object CloudSyncPolicy {
    const val MANUAL_ONLY: Boolean = true
}
