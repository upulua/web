package ru.msu.cmc.webprack.DAO;

import ru.msu.cmc.webprack.models.Department;
import java.util.List;

public interface DepartmentDAO extends CommonDAO<Department, Long>{
    List<Department> getAllDepartments();
    List<Department> getAllDepartmentsByName(String depName);
    Department getSingleDepartmentByName(String depName);

    List<Department> getAllSubdepartments(String depName);
}
