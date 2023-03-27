package ru.msu.cmc.webprack.DAO;

import ru.msu.cmc.webprack.models.Position;
import java.util.List;

public interface PositionDAO extends CommonDAO<Position, Long>{
    List<Position> getAllPositionsInDepartment(String depName);

}
