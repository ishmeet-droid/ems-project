package com.kane.sol.dao;

import com.kane.sol.entities.Employee;
import com.kane.sol.entities.Permission;
import com.kane.sol.entities.Role;
import com.kane.sol.entities.RolePermission;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;


@Repository
public class PermissionDaoImpl implements PermissionDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean hasPermission(Long employeeId, String permissionName) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);

        // Root Employee
        Root<Employee> empRoot = cq.from(Employee.class);
        Join<Employee, Role> roleJoin = empRoot.join("role");
        Join<Role, RolePermission> rolePermissionJoin = roleJoin.join("rolePermissions");
        Join<RolePermission, Permission> permJoin = rolePermissionJoin.join("permission");

        cq.select(cb.count(permJoin))
                .where(
                        cb.and(
                                cb.equal(empRoot.get("id"), employeeId),
                                cb.equal(permJoin.get("name"), permissionName)
                        )
                );

        Long count = em.createQuery(cq).getSingleResult();
        return count > 0;
    }
}
