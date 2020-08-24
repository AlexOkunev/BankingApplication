package com.test.banking.repository;

import com.test.banking.entity.Client;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClientsRepository extends PagingAndSortingRepository<Client, Integer>, ClientsRepositoryReading {
}
