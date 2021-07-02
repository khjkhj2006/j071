package org.bit.j07.board;

import com.google.common.primitives.Ints;
import lombok.extern.log4j.Log4j2;
import org.bit.j07.board.entity.Board;
import org.bit.j07.board.entity.Favorite;
import org.bit.j07.board.repository.BoardRepositorys;
import org.bit.j07.board.repository.FavoriteRepository;
import org.bit.j07.board.repository.ReplyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;


import java.util.Arrays;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
@ActiveProfiles("dev")
public class BoardRepositorysTests {
    @Autowired
    ReplyRepository replyRepository;
    @Autowired
    BoardRepositorys boardRepositorys;

    @Autowired
    FavoriteRepository favoriteRepository;

    @Test
    public void testFavorite() {

        IntStream.rangeClosed(1, 500).forEach(i -> {

            long bno = (long)(Math.random()*200)+1;
            Board board = Board.builder().bno(bno).build();

            Favorite favorite = Favorite.builder()
                    .board(board)
                    .mark(true)
                    .actor("user" + i)
                    .build();

            favoriteRepository.save(favorite);

        });
    }



    // 게시판에 1000개에 글을 작성하라
    // insert create
    @Test
    public void testInsult() {
        IntStream.rangeClosed(1, 1000).forEach(i -> {
            Board board = Board.builder()
                    .title("제목" + i)
                    .content("내용" + i)
                    .writer("작성자" + i)
                    .build();
            boardRepositorys.save(board);
        });
    }
    @Test
    public void testGetData() {
        Pageable pageable = PageRequest.of(0,10);
        Page<Object[]> result = boardRepositorys.getData1(pageable);
        result.getContent().forEach(arr -> log.info(Arrays.toString(arr)));

    }

    @Test
    public void testSearch(){

        Pageable pageable = PageRequest.of(0,10);
        String type ="tcw";
        String keyword ="10";

        Page<Object[]> result = boardRepositorys.getSearchList(type,keyword,pageable);

    }
}