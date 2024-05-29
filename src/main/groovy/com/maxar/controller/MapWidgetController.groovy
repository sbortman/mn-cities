package com.maxar.controller

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.views.View

@Controller( "/" )
class MapWidgetController {

  @View( '/mapWidget/index.html' )
  @Get( uri = "/", produces = MediaType.TEXT_HTML )
  Map<String, Object> index() {
    [ : ]
  }
}