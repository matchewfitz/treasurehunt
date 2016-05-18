package com.matt.treasurehunt.controller

import org.springframework.data.annotation.Id

class Coordinate {
  double latitude
  double longitude
}

class Profile {
  @Id
  String userUuid
  List<Coordinate> route
}
