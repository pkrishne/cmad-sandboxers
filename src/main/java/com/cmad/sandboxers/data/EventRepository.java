package com.cmad.sandboxers.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cmad.sandboxers.model.EventV1;

/**
 * 
 * @author pkrishne
 *
 */

@Repository
public interface EventRepository extends CrudRepository<EventV1, Integer> {

}
