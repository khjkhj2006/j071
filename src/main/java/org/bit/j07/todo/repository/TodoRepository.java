package org.bit.j07.todo.repository;


import org.bit.j07.todo.entity.Todo;
import org.bit.j07.todo.repository.dynamic.TodoSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TodoRepository extends JpaRepository<Todo,Long>, TodoSearch {

    @Query("select t from Todo t where t.content like concat('%',:keyword,'%') order by t.tno desc ")
    Page<Todo> getList(String keyword, Pageable pageable);

    @Modifying
    @Query("update Todo set content =:content where tno = :tno")
    void updateContent(String content, Long tno);
}