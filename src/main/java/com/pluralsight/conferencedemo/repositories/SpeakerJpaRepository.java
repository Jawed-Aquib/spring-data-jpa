package com.pluralsight.conferencedemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pluralsight.conferencedemo.models.Speaker;

public interface SpeakerJpaRepository extends JpaRepository<Speaker, Long> {

}
