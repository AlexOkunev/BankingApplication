package com.test.banking.repository.impl;

import com.test.banking.dto.request.DepositsFilter;
import com.test.banking.entity.*;
import com.test.banking.repository.DepositsRepositoryReading;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.LinkedList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class DepositsRepositoryReadingImpl implements DepositsRepositoryReading {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Deposit> findDeposits(DepositsFilter filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Deposit> cq = cb.createQuery(Deposit.class);

        Root<Deposit> depositRoot = cq.from(Deposit.class);

        Join<Deposit, Bank> joinDepositBank = depositRoot.join("bank");
        Join<Deposit, Client> joinDepositClient = depositRoot.join("client");

        List<Predicate> predicates = new LinkedList<>();

        if (filter.getId() != null) {
            predicates.add(cb.equal(depositRoot.get(Deposit_.id), filter.getId()));
        }

        if (filter.getBankId() != null) {
            predicates.add(cb.equal(joinDepositBank.get(Bank_.id), filter.getBankId()));
        }

        if (isNotBlank(filter.getBankName())) {
            predicates.add(cb.like(cb.upper(joinDepositBank.get(Bank_.name)), "%" + filter.getBankName().toUpperCase() + "%"));
        }

        if (filter.getClientId() != null) {
            predicates.add(cb.equal(joinDepositClient.get(Client_.id), filter.getClientId()));
        }

        if (isNotBlank(filter.getClientFullName())) {
            predicates.add(cb.like(cb.upper(joinDepositClient.get(Client_.fullName)), "%" + filter.getClientFullName().toUpperCase() + "%"));
        }

        if (isNotBlank(filter.getClientShortName())) {
            predicates.add(cb.like(cb.upper(joinDepositClient.get(Client_.shortName)), "%" + filter.getClientShortName().toUpperCase() + "%"));
        }

        if(filter.getPercentMin() != null) {
            predicates.add(cb.ge(depositRoot.get(Deposit_.percent), filter.getPercentMin()));
        }

        if(filter.getPercentMax() != null) {
            predicates.add(cb.le(depositRoot.get(Deposit_.percent), filter.getPercentMax()));
        }

        if(filter.getTermMin() != null) {
            predicates.add(cb.ge(depositRoot.get(Deposit_.term), filter.getTermMin()));
        }

        if(filter.getTermMax() != null) {
            predicates.add(cb.le(depositRoot.get(Deposit_.term), filter.getTermMax()));
        }

        if(filter.getDateMin() != null) {
            predicates.add(cb.greaterThanOrEqualTo(depositRoot.get(Deposit_.createDate), filter.getDateMin()));
        }

        if(filter.getDateMax() != null) {
            predicates.add(cb.lessThanOrEqualTo(depositRoot.get(Deposit_.createDate), filter.getDateMax()));
        }

        cq.where(predicates.toArray(new Predicate[predicates.size()]));

        cq.orderBy(cb.asc(depositRoot.get(Deposit_.id)));

        TypedQuery<Deposit> query = entityManager.createQuery(cq)
                .setFirstResult(filter.getPagingFirstResult())
                .setMaxResults(filter.getPagingMaxResults());

        return query.getResultList();
    }
}
