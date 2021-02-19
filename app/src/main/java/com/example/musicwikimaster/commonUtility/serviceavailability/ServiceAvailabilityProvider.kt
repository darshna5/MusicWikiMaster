package com.example.musicwikimaster.commonUtility.serviceavailability

interface ServiceAvailabilityProvider {

  fun getServiceStatus(code: String): ServiceStatus?

}
