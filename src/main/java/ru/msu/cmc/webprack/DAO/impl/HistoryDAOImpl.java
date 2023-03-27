package ru.msu.cmc.webprack.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webprack.DAO.HistoryDAO;
import ru.msu.cmc.webprack.models.History;
import ru.msu.cmc.webprack.models.Worker;

import java.util.List;

@Repository
public class HistoryDAOImpl extends CommonDAOImpl<History, Long> implements HistoryDAO {
    public HistoryDAOImpl() {
        super(History.class);
    }
    @Override
    public List<Worker> getAllInfoAboutWorker(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Worker> query = session.createQuery(
                    "FROM Worker w, Position p " +
                            "JOIN w.id JOIN p.id " +
                            "WHERE w.id = :id", Worker.class)
                    .setParameter("id", id);
            return query.getResultList();
        }
    }
}
