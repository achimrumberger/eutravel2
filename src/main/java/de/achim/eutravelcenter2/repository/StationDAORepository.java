package de.achim.eutravelcenter2.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import de.achim.eutravelcenter2.dao.StationDAO;

public interface StationDAORepository extends CrudRepository<StationDAO, Integer> {
	
	Iterable <StationDAO> findByName(String name);
	
	
	@Query("SELECT s FROM StationDAO s WHERE s.name LIKE %?1%")
	Iterable<StationDAO> findByNameQuery(String name);

}
