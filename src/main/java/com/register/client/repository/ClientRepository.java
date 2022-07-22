package com.register.client.repository;

import com.register.client.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Classe Repository.
 */
@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

}
