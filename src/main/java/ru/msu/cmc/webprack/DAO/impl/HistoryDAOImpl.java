package ru.msu.cmc.webprack.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webprack.DAO.HistoryDAO;
import ru.msu.cmc.webprack.DAO.WorkerDAO;
import ru.msu.cmc.webprack.models.History;
import ru.msu.cmc.webprack.models.Worker;

import java.util.List;

@Repository
public class HistoryDAOImpl extends CommonDAOImpl<History, Long> implements HistoryDAO {
    public HistoryDAOImpl(WorkerDAO workerDAO) {
        super(History.class);
        this.workerDAO = workerDAO;
    }
    private final WorkerDAO workerDAO;
    @Override
    public List<History> getWorkerHistory(Long workerId) {
        try(Session session = sessionFactory.openSession()) {
            Worker w = workerDAO.getById(1L);
            Query<History> query = session.createQuery("FROM History WHERE worker=:w")
                    .setParameter("w", w);
            return query.getResultList();
        }
    }
}
