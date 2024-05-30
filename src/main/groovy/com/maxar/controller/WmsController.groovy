package com.maxar.controller

import com.maxar.service.WebMappingService
import groovy.util.logging.Slf4j
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.server.types.files.StreamedFile
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn

@Slf4j
@Controller( "/wms" )
class WmsController {
  WebMappingService webMappingService

  WmsController( WebMappingService webMappingService ) {
    this.webMappingService = webMappingService
  }

  @ExecuteOn( TaskExecutors.IO)
  @Get( uri = "/getMap", produces = [ MediaType.IMAGE_PNG ] )
  StreamedFile getMap(GetMapRequest getMapRequest) {
    log.info("${getMapRequest}")
    webMappingService?.getMap(getMapRequest)
  }
}