package com.cmad.sandboxers.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

import com.cmad.sandboxers.model.EventType;
import com.cmad.sandboxers.model.EventV1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 
 * @author pkrishne
 *
 */

@Repository
public interface EventRepository extends CrudRepository<EventV1, Integer> {
    public Page<EventV1> findByEventType(EventType eventtype, Pageable pageable);
    public Page<EventV1> findBySource(String source, Pageable pageable);
    public Page<EventV1> findById(Integer Id, Pageable pageable);
    public Page<EventV1> findByDateAfter(Date date, Pageable pageable);
    public List<EventV1> findByDateAfter(Date date);
}
