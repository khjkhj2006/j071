package org.bit.j07.board.service;

import org.bit.j07.board.dto.BoardDTO;
import org.bit.j07.board.dto.BoardListRequestDTO;
import org.bit.j07.board.dto.ListBoardDTO;
import org.bit.j07.board.entity.Board;
import org.bit.j07.common.dto.ListResponseDTO;

public interface BoardService {

    ListResponseDTO<ListBoardDTO> getList(BoardListRequestDTO requestDTO);


    default ListBoardDTO arrToDTO(Object[] arr) {

        Board board = (Board)arr[0];
        long replyCount = (long)arr[1];
        long favoriteCount = (long)arr[2];

        return ListBoardDTO.builder()
                .boardDTO(entityToDTO(board))
                .likeCount(favoriteCount)
                .replyCount(replyCount)
                .build();
    }

    default BoardDTO entityToDTO(Board board) {
        return BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .regData(board.getRegDate())
                .modData(board.getModDate())
                .build();
    }

}
