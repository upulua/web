package ru.msu.cmc.webprack.DAO.impl;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webprack.DAO.HistoryDAO;
import ru.msu.cmc.webprack.DAO.WorkerDAO;
import ru.msu.cmc.webprack.models.Worker;

import jakarta.persistence.*;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WorkerDAOImpl extends CommonDAOImpl<Worker, Long> implements WorkerDAO {
    public WorkerDAOImpl() {
        super(Worker.class);
    }

    @Override
    public List<Worker> getAllWorkers() {
        try (Session session = sessionFactory.openSession()) {
            Query<Worker> query = session.createQuery("FROM Worker", Worker.class);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }
    @Override
    public List<Worker> getAllWorkerByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<Worker> query = session.createQuery("FROM Worker WHERE name LIKE :gotName", Worker.class)
                    .setParameter("gotName", likeExpr(name));
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public List<Worker> getByFilter(Filter filter) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Worker> criteriaQuery = builder.createQuery(Worker.class);
            Root<Worker> root = criteriaQuery.from(Worker.class);

            List<Predicate> predicates = new ArrayList<>();
            if (filter.getName() != null) {
                predicates.add(builder.like(root.get("name"), likeExpr(filter.getName())));
            }
            if (filter.getStartDate() != null) {
                predicates.add(builder.equal(root.get("start"), filter.getStartDate()));
            }
            criteriaQuery.select(root).where(predicates.toArray(new Predicate[] {}));

            return session.createQuery(criteriaQuery).getResultList();
        }
    }

    @Override
    public Worker getWorkerByName(String name) {
       List<Worker> candidates = this.getAllWorkerByName(name);
       if (candidates == null)
           return null;
       if (candidates.size() != 1)
           return null;
       Worker w = candidates.get(0);
       return w;
    }
    private String likeExpr(String param) {
        return "%" + param + "%";
    }
}
