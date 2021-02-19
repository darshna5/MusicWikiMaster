package com.example.musicwikimaster.commonUtility

/**
 * Represents an intent to escalate the user's current privilege.
 */
data class PrivilegeEscalationRequest(
  val scope: NextScope,
  val onError: (Throwable) -> Unit,
  val onSuccess: (scopeRequested: NextScope?) -> Unit
)
