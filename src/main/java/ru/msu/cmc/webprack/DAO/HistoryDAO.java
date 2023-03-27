package ru.msu.cmc.webprack.DAO;
import ru.msu.cmc.webprack.models.History;
import ru.msu.cmc.webprack.models.Worker;

import java.util.List;
public interface HistoryDAO extends CommonDAO<History, Long>{
    List<Worker> getAllInfoAboutWorker (Long workerId);
}
