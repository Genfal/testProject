package ru.project.test.v2.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.project.test.v2.model.User;

import java.util.List;
import java.util.Map;

/**
 * @author TorstendasTost
 * @since 25.10.2022
 */
@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    @Query(value = "SELECT u FROM user_table u where u.country = :country")
    List<User> testQuery1(@Param("country") String country);

    @Query(value = "SELECT * FROM user_table", nativeQuery = true)
    List<User> testQuery2();

    @Query(value = "SELECT u.id, u.name FROM user_table u group by u.id")
    List testQuery3();

    @Query(value = "select user_name, count(user_id) \n" +
            "from user_table \n" +
            "join email_table \n" +
            "on user_table.id = email_table.user_id \n" +
            "group by user_name \n" +
            "order by count(user_id) desc",nativeQuery = true)
    Map<String,Integer> testQuerySqlRequest();

    void searchAllByAge(Integer age);
}
