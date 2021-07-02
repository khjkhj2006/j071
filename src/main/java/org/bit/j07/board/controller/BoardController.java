package org.bit.j07.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bit.j07.board.dto.BoardListRequestDTO;
import org.bit.j07.board.dto.ListBoardDTO;
import org.bit.j07.board.service.BoardService;
import org.bit.j07.common.dto.ListResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public ResponseEntity<ListResponseDTO<ListBoardDTO>> list(BoardListRequestDTO requestDTO){

        return ResponseEntity.ok(boardService.getList(requestDTO));
    }


}
