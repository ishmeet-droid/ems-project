package com.kane.sol.dao;


import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.kane.sol.entities.Role;

@Repository
public class RoleDaoImpl  implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role findByName(Role.Roles roleName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Role> cq = cb.createQuery(Role.class);

        Root<Role> root = cq.from(Role.class);

        cq.select(root)
                .where(cb.equal(root.get("name"), roleName));

        return entityManager.createQuery(cq)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }
}
