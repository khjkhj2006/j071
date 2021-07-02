package org.bit.j07.board.repository;

import org.bit.j07.board.entity.Board;
import org.bit.j07.board.repository.dynamic.BoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepositorys extends JpaRepository<Board, Long>, BoardSearch {
    // [] 배열일 때 2개이상 이어야 한다
    @Query("select b, count(distinct r), count(distinct f) from Board b " +
            "left join Reply r on r.board = b " +
            "left join Favorite f on f.board = b " +
            "group by b order by b.bno desc ")
    Page<Object[]> getData1(Pageable pageable);

}