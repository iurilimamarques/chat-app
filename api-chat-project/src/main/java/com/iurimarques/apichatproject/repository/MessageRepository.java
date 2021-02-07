package com.iurimarques.apichatproject.repository;

import java.util.List;

import com.iurimarques.apichatproject.model.Message;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

}
