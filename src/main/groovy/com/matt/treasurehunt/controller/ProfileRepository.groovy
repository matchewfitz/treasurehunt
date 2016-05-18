package com.matt.treasurehunt.controller

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component

@Component
interface ProfileRepository extends MongoRepository<Profile, String> {
  Profile findByUserUuid(String userUuid)
  List<Profile> findAll()
}