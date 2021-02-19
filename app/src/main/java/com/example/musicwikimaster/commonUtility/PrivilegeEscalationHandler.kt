package com.example.musicwikimaster.commonUtility

interface PrivilegeEscalationHandler {

  /**
   * Request for a privilege escalation using [request].
   */
  fun requestPrivilege(request: PrivilegeEscalationRequest)

}
