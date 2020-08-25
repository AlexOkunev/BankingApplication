package com.test.banking.repository.impl;

import com.test.banking.dto.request.BanksFilter;
import com.test.banking.entity.Bank;
import com.test.banking.entity.Bank_;
import com.test.banking.repository.BanksRepositoryReading;

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

public class BanksRepositoryReadingImpl implements BanksRepositoryReading {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Bank> findBanks(BanksFilter filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Bank> cq = cb.createQuery(Bank.class);

        Root<Bank> bankRoot = cq.from(Bank.class);

        List<Predicate> predicates = new LinkedList<>();

        if (isNotBlank(filter.getName())) {
            predicates.add(cb.like(cb.upper(bankRoot.get(Bank_.name)), "%" + filter.getName().toUpperCase() + "%"));
        }

        if (isNotBlank(filter.getBik())) {
            predicates.add(cb.like(cb.upper(bankRoot.get(Bank_.bik)), "%" + filter.getBik().toUpperCase() + "%"));
        }

        if (filter.getId() != null) {
            predicates.add(cb.equal(bankRoot.get(Bank_.id), filter.getId()));
        }

        cq.where(predicates.toArray(new Predicate[predicates.size()]));

        cq.orderBy(cb.asc(bankRoot.get(Bank_.id)));

        TypedQuery<Bank> query = entityManager.createQuery(cq).setFirstResult(filter.getPagingFirstResult()).setMaxResults(filter.getPagingMaxResults());

        return query.getResultList();
    }
}
