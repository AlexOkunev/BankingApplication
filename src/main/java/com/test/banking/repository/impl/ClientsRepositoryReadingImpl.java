package com.test.banking.repository.impl;

import com.test.banking.dto.request.ClientsFilter;
import com.test.banking.entity.Client;
import com.test.banking.entity.Client_;
import com.test.banking.repository.ClientsRepositoryReading;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class ClientsRepositoryReadingImpl implements ClientsRepositoryReading {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Client> findClients(ClientsFilter filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Client> cq = cb.createQuery(Client.class);

        Root<Client> clientRoot = cq.from(Client.class);

        List<Predicate> predicates = new LinkedList<>();

        if (isNotBlank(filter.getAddress())) {
            predicates.add(cb.like(cb.upper(clientRoot.get(Client_.address)), "%" + filter.getAddress().toUpperCase() + "%"));
        }

        if (isNotBlank(filter.getShortName())) {
            predicates.add(cb.like(cb.upper(clientRoot.get(Client_.shortName)), "%" + filter.getShortName().toUpperCase() + "%"));
        }

        if (isNotBlank(filter.getFullName())) {
            predicates.add(cb.like(cb.upper(clientRoot.get(Client_.fullName)), "%" + filter.getFullName().toUpperCase() + "%"));
        }

        if (filter.getId() != null) {
            predicates.add(cb.equal(clientRoot.get(Client_.id), filter.getId()));
        }

        if (filter.getType() != null) {
            predicates.add(cb.equal(clientRoot.get(Client_.type), filter.getType()));
        }

        cq.where(predicates.toArray(new Predicate[predicates.size()]));

        cq.orderBy(cb.asc(clientRoot.get(Client_.id)));

        TypedQuery<Client> query = entityManager.createQuery(cq).setFirstResult(filter.getPagingFirstResult()).setMaxResults(filter.getPagingMaxResults());

        return query.getResultList();
    }
}
