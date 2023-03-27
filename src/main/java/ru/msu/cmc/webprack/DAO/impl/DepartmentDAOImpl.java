package ru.msu.cmc.webprack.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webprack.DAO.DepartmentDAO;
import ru.msu.cmc.webprack.models.Department;
import ru.msu.cmc.webprack.models.Worker;

import java.util.List;

@Repository
public class DepartmentDAOImpl extends CommonDAOImpl<Department, Long> implements DepartmentDAO {
    public DepartmentDAOImpl() {super(Department.class);}
   @Override
    public List<Department> getAllDepartments() {
        try(Session session = sessionFactory.openSession()) {
            Query<Department> query = session.createQuery("FROM Department", Department.class);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
   }
   @Override
    public List <Department> getAllDepartmentsByName(String depName){
       try(Session session = sessionFactory.openSession()) {
            Query<Department> query = session.createQuery("FROM Department WHERE name LIKE :gotName", Department.class)
                    .setParameter("gotName", likeExpr(depName));
           return query.getResultList().size() == 0 ? null : query.getResultList();
       }
   }
   @Override
   public Department getSingleDepartmentByName(String depName) {
       try(Session session = sessionFactory.openSession()) {

           List<Department> candidates = this.getAllDepartmentsByName(depName);
           return candidates == null ? null : candidates.size() == 1 ? candidates.get(0) : null;

       }
    }
    @Override
    public List<Department> getAllSubdepartments(String depName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Department> q = session.createQuery("FROM Department WHERE name = :param1", Department.class)
                    .setParameter("param1", depName);

            Long headId = q.getResultList().get(0).getId();

            Query<Department> query = session.createQuery("From Department WHERE head = :headId", Department.class)
                    .setParameter("headId", headId);

            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }
    private String likeExpr(String param) {
        return "%" + param + "%";
    }
}
