package com.iurimarques.apichatproject.repository;

import com.iurimarques.apichatproject.model.Message;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

}
