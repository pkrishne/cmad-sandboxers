package com.cmad.sandboxers.data;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.cmad.sandboxers.model.Operator;

/**
 * 
 * @author pkrishne
 *
 */
@Repository
public interface UserRepository extends MongoRepository<Operator, ObjectId> {

	@Query(value = "{'user_id' : ?0}")
	public Operator getUserById(String user_id);
}
