package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.models.Speaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SessionRepository {
    @PersistenceContext
    private EntityManager entityManager;
    
    //Using proxy to extend the functionality of SessionRepository
    @Autowired
    SessionJpaRepository sessionRepository;

    public Session create(Session session) {
    	sessionRepository.saveAndFlush(session);
		/*
		 * entityManager.merge(session); entityManager.flush();
		 */
        return session;
    }

    public Session update(Session session) {
    	
    	sessionRepository.saveAndFlush(session);
    	
		/*
		 * session = entityManager.merge(session); entityManager.flush();
		 */
        return sessionRepository.getOne(session.getSessionId());
    }

    public void delete(Long id) {
    	
    	sessionRepository.deleteById(id);
		/*
		 * entityManager.remove(find(id)); entityManager.flush();
		 */
    }

    public Session find(Long id) {
      //  return entityManager.find(Session.class, id);
        
        return sessionRepository.getOne(id);
    }

    public List<Session> list() {
      //  return entityManager.createQuery("select s from Session s").getResultList();
        
        return sessionRepository.findAll();
    }

    public List<Session> getSessionsThatHaveName(String name) {
        List<Session> ses = entityManager
                .createQuery("select s from Session s where s.sessionName like :name")
                .setParameter("name", "%" + name + "%").getResultList();
        return ses;
    }
}
