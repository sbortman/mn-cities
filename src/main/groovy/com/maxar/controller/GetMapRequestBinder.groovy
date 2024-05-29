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
    return null
  }
}
