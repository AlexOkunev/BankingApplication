package com.test.banking.repository;

import com.test.banking.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientsRepository extends CrudRepository<Client, Integer>, ClientsRepositoryReading {
}
