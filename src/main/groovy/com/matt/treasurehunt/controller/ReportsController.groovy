package com.matt.treasurehunt.controller

import groovy.util.logging.Slf4j
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import static org.springframework.web.bind.annotation.RequestMethod.GET

@Slf4j
@Component
@RestController
@RequestMapping('/report')
class ReportsController {

  @RequestMapping(method=GET, value='{latitude}/{longitude}/{userUuid}')
  void publishReport(@PathVariable('userUuid') String userUuid, @PathVariable('latitude') double latitude, @PathVariable('longitude') double longitude) {
    log.info("User with uuid $userUuid is now at $latitude,$longitude")
  }

}