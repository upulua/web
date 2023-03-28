package ru.msu.cmc.webprack.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webprack.DAO.PositionDAO;
import ru.msu.cmc.webprack.models.Department;
import ru.msu.cmc.webprack.models.Position;

import java.util.List;

@Repository
public class PositionDAOImpl extends CommonDAOImpl<Position, Long> implements PositionDAO {
    public PositionDAOImpl() {
        super(Position.class);
    }
    @Override
    public List<Position> getAllPositionsInDepartment(String depName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Department> q = session.createQuery("FROM Department WHERE name = :param1", Department.class)
                    .setParameter("param1", depName);
            Long depId = q.getResultList().get(0).getId();
//            Query<Position> query = session.createQuery("FROM Position WHERE department = :depId", Position.class)
//                    .setParameter("depId", depId);
            Query<Position> query = session.createQuery("FROM Position WHERE department = :dep", Position.class)
                    .setParameter("dep", q.getResultList().get(0));
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }
}
