package org.bit.j07.todo.repository.dynamic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.bit.j07.todo.entity.Todo;

public interface TodoSearch {

    Todo doA();

    Page<Todo> listWithSearch(String keyword, Pageable pageable);
}