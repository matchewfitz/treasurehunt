package com.matt.treasurehunt.controller

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
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

  @Autowired
  ProfileRepository profileRepository

  @RequestMapping(method=GET, value='{latitude}/{longitude}/{userUuid}')
  void updateReport(@PathVariable('userUuid') String userUuid, @PathVariable('latitude') double latitude, @PathVariable('longitude') double longitude) {
    Profile profile = profileRepository.findByUserUuid(userUuid)
    profile? updateProfile(profile, latitude, longitude) : createProfile(userUuid, latitude, longitude)
    log.info("User with uuid $userUuid is now at $latitude,$longitude")
  }

  void updateProfile(Profile profile, double latitude, double longitude) {
    profile.route.add(new Coordinate(latitude: latitude, longitude: longitude))
    profileRepository.save(profile)
  }

  void createProfile(String userUuid, double latitude, double longitude) {
    profileRepository.save(new Profile(userUuid: userUuid, route: [new Coordinate(latitude: latitude, longitude: longitude)]))
  }

  @RequestMapping(method=GET, value='generate/{userUuid}')
  Profile publishReport(@PathVariable('userUuid') String userUuid) {
    profileRepository.findByUserUuid(userUuid)
  }

  @RequestMapping(method=GET, value='generate')
  List<Profile> publishReportForAll() {
    profileRepository.findAll()
  }



}