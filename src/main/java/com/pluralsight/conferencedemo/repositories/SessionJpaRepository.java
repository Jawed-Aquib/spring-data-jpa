package com.pluralsight.conferencedemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pluralsight.conferencedemo.models.Session;

public interface SessionJpaRepository extends JpaRepository<Session, Long> {

}
