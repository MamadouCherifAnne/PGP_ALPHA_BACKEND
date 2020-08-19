package com.iscae.alpha.pgp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iscae.alpha.pgp.entities.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
