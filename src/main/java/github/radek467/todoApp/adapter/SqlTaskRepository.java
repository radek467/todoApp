package github.radek467.todoApp.adapter;

import github.radek467.todoApp.model.Task;
import github.radek467.todoApp.model.TaskRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    interface SqlTaskRepository extends TaskRepository, JpaRepository <Task, Integer> {

    @Query(nativeQuery = true, value = "select count(*) > 0 from TASK where id =:id")
    boolean existsById(@Param("id") Integer id);


}
