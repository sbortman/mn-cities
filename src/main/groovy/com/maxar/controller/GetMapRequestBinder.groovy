package com.maxar.controller

import io.micronaut.core.convert.ArgumentConversionContext
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.bind.binders.TypedRequestArgumentBinder
import jakarta.inject.Singleton

@Singleton
class GetMapRequestBinder implements TypedRequestArgumentBinder<GetMapRequest> {
  @Override
  Argument<GetMapRequest> argumentType() {
    Argument.of( GetMapRequest )
  }

  @Override
  BindingResult<GetMapRequest> bind( ArgumentConversionContext<GetMapRequest> context, HttpRequest<?> source ) {
    Map<String, List<String>> queryParams = source.getParameters().asMap()
    GetMapRequest request = new GetMapRequest()

    queryParams.each { key, value ->
      switch ( key.toLowerCase() ) {
      case 'service':
        request.service = value[ 0 ]
        break
      case 'version':
        request.version = value[ 0 ]
        break
      case 'request':
        request.request = value[ 0 ]
        break
      case 'width':
        request.width = value[ 0 ]?.toInteger()
        break
      case 'height':
        request.height = value[ 0 ]?.toInteger()
        break
      case 'transparent':
        request.transparent = value[ 0 ]?.toBoolean()
        break
      case 'layers':
        request.layers = value[ 0 ]
        break
      case 'styles':
        request.styles = value[ 0 ]
        break
      case 'format':
        request.format = value[ 0 ]
        break
      case 'srs':
      case 'crs':
        request.srs = value[ 0 ]
        break
      case 'bbox':
        request.bbox = value[ 0 ]
        break
      }
    }

    return () -> Optional.of( request )
  }
}