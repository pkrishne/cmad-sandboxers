package com.cmad.sandboxers.data;

import java.util.List;

import com.cmad.sandboxers.model.EventType;
import com.cmad.sandboxers.model.EventV1;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author pkrishne
 *
 */

@Repository
public interface EventRepository extends PagingAndSortingRepository<EventV1, Integer> {
	
    public Page<EventV1> findByEventType(EventType eventtype, Pageable pageable);
    public Page<EventV1> findBySource(String source, Pageable pageable);
    public Page<EventV1> findById(Integer Id, Pageable pageable);
    public Page<EventV1> findByTimestampAfter(Long timestamp, Pageable pageable);
    public List<EventV1> findByTimestampAfter(Long l);
    public List<EventV1> findAll();
    public Page<EventV1> findAll(Pageable page);
    
    @Query(value="SELECT * FROM event e WHERE e.source in :deviceList and (e.source LIKE %:search_str% OR e.description LIKE %:search_str%)",nativeQuery = true)
    public Page<EventV1> findAllEventsofAssignedDevices(List<String> deviceList,String search_str, Pageable pageable);

}
