package ru.msu.cmc.webprack.DAO;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.webprack.models.Department;
import ru.msu.cmc.webprack.models.History;
import ru.msu.cmc.webprack.models.Position;
import ru.msu.cmc.webprack.models.Worker;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class PositionDAOTest {
    @Autowired
    private DepartmentDAO departmentDAO;
    @Autowired
    private WorkerDAO workerDAO;
    @Autowired
    private PositionDAO positionDAO;
    @Autowired
    private HistoryDAO historyDAO;
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void testSimpleManipulations() {
        List<Position> posAnalysis = positionDAO.getAllPositionsInDepartment("Аналитический отдел");
        assertEquals(2, posAnalysis.size());
    }

    @Test
    void testUpdate() {
        Position p = positionDAO.getById(7L);
        assertNotNull(p);
        p.setDuty("Анализировать");
        positionDAO.update(p);

        p = positionDAO.getById(7L);
        assertEquals("Анализировать", p.getDuty());
    }

    @BeforeEach
    void beforeEach() {
        List<Worker> workerList = new ArrayList<>();
        java.sql.Timestamp ts2 = java.sql.Timestamp.valueOf("2022-02-22 00:00:00");
        Timestamp tsTime2;
        Timestamp tsTime1;
        workerList.add(new Worker(1L, "Иванов Иван Иванович","МГУ им. Ломоносова",ts2,"Ломоносовский пр-кт 27к11"));
        tsTime2 = java.sql.Timestamp.valueOf("2019-05-04 00:00:00");
        workerList.add(new Worker( 2L,"Петров Виктор Сергеевич","МГТУ им. Баумана",tsTime2,"Мичуринский пр-кт 11"));
        tsTime2 = java.sql.Timestamp.valueOf("2014-07-11 00:00:00");
        workerList.add(new Worker(3L, "Амеличева Анна Николаевна","МИФИ",tsTime2,"ул. Большая Филевская 3"));
        tsTime2 = java.sql.Timestamp.valueOf("2016-02-22 00:00:00");
        workerList.add(new Worker(4L, "Занина Елена Алексеевна","МГУ им. Ломоносова",tsTime2,"ул. Молодогвардейская 35"));
        tsTime2 = java.sql.Timestamp.valueOf("2017-09-13 00:00:00");
        workerList.add(new Worker(5L, "Аркунова Ирина Евгеньевна","МИФИ",tsTime2,"Ломоносовский пр-кт 14"));
        tsTime2 = java.sql.Timestamp.valueOf("2021-04-28 00:00:00");
        workerList.add(new Worker(6L, "Ямолкин Леонид Игоревич","МГУ им. Ломоносова",tsTime2, "Ломоносовский пр-кт 27к11"));
        tsTime2 = java.sql.Timestamp.valueOf("2019-05-08 00:00:00");
        workerList.add(new Worker(7L, "Гнатенко Павел Сергеевич","МАИ", tsTime2, "Мичуринский пр-кт 11"));
        tsTime2 = java.sql.Timestamp.valueOf("2014-08-10 00:00:00");
        workerList.add(new Worker(8L, "Вьюнова Анна Викторовна","МИФИ",tsTime2 ,"ул. Большая Филевская 3"));
        tsTime2 = java.sql.Timestamp.valueOf("2016-08-09 00:00:00");
        workerList.add(new Worker(9L, "Ким Тимур Александрович","МГУ им. Ломоносова", tsTime2,"ул. Молодогвардейская 35"));
        tsTime2 = java.sql.Timestamp.valueOf("2017-07-12 00:00:00");
        workerList.add(new Worker(10L, "Черемисенов Иван Михайлович","МИФИ",tsTime2,"Ломоносовский пр-кт 14"));

        workerDAO.saveCollection(workerList);

        List<Department> departmentList = new ArrayList<>();
        departmentList.add(new Department(null, "Главное отделение", null, workerList.get(2)));
        departmentList.add(new Department(null, "Отдел продаж", 1L, workerList.get(2)));
        departmentList.add(new Department(null, "Технический отдел",  1L, workerList.get(1)));
        departmentList.add(new Department(null, "Аналитический отдел", 2L, workerList.get(4)));
        departmentList.add(new Department(null, "Отдел системного администрирования", 3L, workerList.get(9)));

        departmentDAO.saveCollection(departmentList);

        List<Position> positionList = new ArrayList<>();
        positionList.add(new Position(null, "Директор главного отделения", "Руководить всеми дочерними отделениями", departmentList.get(0), 1L));
        positionList.add(new Position(null, "Директор отдела продаж", "Руководить отделом продаж", departmentList.get(1), 1L));
        positionList.add(new Position(null, "Директор технического отдела", "Руководить техничсеким отделом", departmentList.get(2), 1L));
        positionList.add(new Position(null, "Директор аналитического отдела", "Руководить аналитическим отделом", departmentList.get(3), 1L));
        positionList.add(new Position(null, "Директор отдела системного администратора", "Руководить отделом системного администрирования", departmentList.get(4), 1L));
        positionList.add(new Position(null, "Системный администратор","",departmentList.get(4), 2L));
        positionList.add(new Position(null, "Аналитик", "", departmentList.get(3), 3L));
        positionList.add(new Position(null, "Разработчик", "", departmentList.get(2), 2L));
        positionList.add(new Position(null, "SMM-специалист","",departmentList.get(1),3L));
        positionList.add(new Position(null, "Менеджер","",departmentList.get(1), 1L));

        positionDAO.saveCollection(positionList);

        List<History> historyList = new ArrayList<>();
        tsTime1 = java.sql.Timestamp.valueOf("2019-05-12 00:00:00");
        historyList.add(new History(1L, workerList.get(0), positionList.get(0), tsTime1, null));
        tsTime2 = java.sql.Timestamp.valueOf("2014-07-11 00:00:00");
        tsTime1 = java.sql.Timestamp.valueOf("2019-05-11 00:00:00");
        historyList.add(new History(2L, workerList.get(0), positionList.get(1), tsTime2, tsTime1));
        tsTime1 = java.sql.Timestamp.valueOf("2016-02-22 00:00:00");
        historyList.add(new History(3L, workerList.get(1), positionList.get(8), tsTime1, null));
        tsTime2 = java.sql.Timestamp.valueOf("2013-04-12 00:00:00");
        tsTime1 = java.sql.Timestamp.valueOf("2016-02-21 00:00:00");
        historyList.add(new History(4L, workerList.get(1), positionList.get(9),tsTime2, tsTime1));
        tsTime2 = java.sql.Timestamp.valueOf("2019-05-04 00:00:00");
        historyList.add(new History(5L, workerList.get(2), positionList.get(2), tsTime2, null));
        tsTime2 = java.sql.Timestamp.valueOf("2017-09-13 00:00:00");
        historyList.add(new History(6L, workerList.get(3), positionList.get(3),tsTime2,null));
        tsTime2 = java.sql.Timestamp.valueOf("2012-01-25 00:00:00");
        tsTime1 = java.sql.Timestamp.valueOf("2017-09-12 00:00:00");
        historyList.add(new History(7L, workerList.get(3), positionList.get(6), tsTime2, tsTime1));
        tsTime2 = java.sql.Timestamp.valueOf("2017-07-12 00:00:00");
        historyList.add(new History(8L,workerList.get(9), positionList.get(4),tsTime2, null));
        tsTime2 = java.sql.Timestamp.valueOf("2015-08-09 00:00:00");
        tsTime1 = java.sql.Timestamp.valueOf("2017-07-11 00:00:00");
        historyList.add(new History(9L,workerList.get(9),positionList.get(5),tsTime2,tsTime1));
        tsTime2 = java.sql.Timestamp.valueOf("2017-09-13 00:00:00");
        historyList.add(new History(10L, workerList.get(4),positionList.get(5),tsTime2, null));
        tsTime2 = java.sql.Timestamp.valueOf("2021-04-28 00:00:00");
        historyList.add(new History(11L,workerList.get(5),positionList.get(6),tsTime2,null));
        tsTime2 = java.sql.Timestamp.valueOf("2020-03-21 00:00:00");
        tsTime1 = java.sql.Timestamp.valueOf("2021-04-27 00:00:00");
        historyList.add(new History(12L,workerList.get(5),positionList.get(7), tsTime2,tsTime1));
        tsTime2 = java.sql.Timestamp.valueOf("2014-08-10 00:00:00");
        historyList.add(new History(13L,workerList.get(7),positionList.get(9),tsTime2, null));
        tsTime2 = java.sql.Timestamp.valueOf("2019-05-08 00:00:00");
        historyList.add(new History(14L,workerList.get(6),positionList.get(7),tsTime2,null));
        tsTime2 = java.sql.Timestamp.valueOf("2016-08-09 00:00:00");
        historyList.add(new History(15L,workerList.get(8),positionList.get(8),tsTime2, null));
        tsTime2 = java.sql.Timestamp.valueOf("2014-05-06 00:00:00");
        tsTime1 = java.sql.Timestamp.valueOf("2016-08-08 00:00:00");
        historyList.add(new History(16L,workerList.get(8),positionList.get(9),tsTime2,tsTime1));

        historyDAO.saveCollection(historyList);
    }

    @BeforeAll
    @AfterEach
    void annihilation() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            NativeQuery query1 = session.createNativeQuery("TRUNCATE worker RESTART IDENTITY CASCADE;");
            query1.executeUpdate();
            NativeQuery query2 = session.createNativeQuery("ALTER SEQUENCE worker_worker_id_seq RESTART WITH 1;");
            query2.executeUpdate();
            session.getTransaction().commit();

            session.beginTransaction();
            query1 = session.createNativeQuery("TRUNCATE department RESTART IDENTITY CASCADE;");
            query1.executeUpdate();
            query2 = session.createNativeQuery("ALTER SEQUENCE department_department_id_seq RESTART WITH 1;");
            query2.executeUpdate();
            session.getTransaction().commit();

            session.beginTransaction();
            query1 = session.createNativeQuery("TRUNCATE position RESTART IDENTITY CASCADE;");
            query1.executeUpdate();
            query2 = session.createNativeQuery("ALTER SEQUENCE position_position_id_seq RESTART WITH 1;");
            query2.executeUpdate();
            session.getTransaction().commit();

            session.beginTransaction();
            query1 = session.createNativeQuery("TRUNCATE history RESTART IDENTITY CASCADE;");
            query1.executeUpdate();
            query2 = session.createNativeQuery("ALTER SEQUENCE history_history_id_seq RESTART WITH 1;");
            query2.executeUpdate();
            session.getTransaction().commit();
        }
    }

}
