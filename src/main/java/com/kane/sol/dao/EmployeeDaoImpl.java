package com.kane.sol.dao;

import com.kane.sol.entities.Employee;
import java.util.List;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;


@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Employee> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);
        cq.select(root);
//        return entityManager.createQuery("from Employee", Employee.class).getResultList();
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<Employee> search(String keyword) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);

        Predicate byName = cb.like(cb.lower(root.get("name")), keyword.toLowerCase() + "%");
        Predicate byId = cb.like(cb.toString(root.get("id")), keyword + "%");

        cq.select(root).where(cb.or(byName, byId));
        return entityManager.createQuery(cq).getResultList();
    }


    @Override
    public Employee findById(Long id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public Employee findByCredentials(String name, String password) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);

        cq.select(root).where(
                cb.and(
                        cb.equal(root.get("name"), name),
                        cb.equal(root.get("password"), password)
                )
        );

        return entityManager.createQuery(cq)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Employee e) {
        if (e.getId() == null) {
            // brand‐new employee
            entityManager.persist(e);
        } else {
            // existing/detached employee—merge back into persistence context
            entityManager.merge(e);
        }
    }


    @Override
    public void delete(Employee e) {
        entityManager.remove(entityManager.contains(e) ? e : entityManager.merge(e));
    }
}

