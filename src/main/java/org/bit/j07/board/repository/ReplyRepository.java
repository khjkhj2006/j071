package org.bit.j07.board.repository;

import org.bit.j07.board.entity.Board;
import org.bit.j07.board.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    Page<Reply> getByBoard(Board board, Pageable pageable);
}
