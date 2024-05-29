package com.maxar.controller

import groovy.transform.ToString
import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@ToString(includeNames = true)
@Serdeable
@Introspected
class GetMapRequest {
  String service
  String version
  String request
  String bbox
  String srs
  String layers
  String styles
  String format
  Integer width
  Integer height
  Boolean transparent
}
