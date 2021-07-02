package org.bit.j07.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bit.j07.board.dto.BoardListRequestDTO;
import org.bit.j07.board.dto.ListBoardDTO;
import org.bit.j07.board.repository.BoardRepositorys;
import org.bit.j07.common.dto.ListResponseDTO;
import org.bit.j07.common.dto.PageMaker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepositorys boardRepositorys;

    @Override
    public ListResponseDTO<ListBoardDTO> getList(BoardListRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable();

        Page<Object[]> result = boardRepositorys.getSearchList(requestDTO.getType(), requestDTO.getKeyword(), pageable);

        List<ListBoardDTO> boardDTOList =
                result.getContent().stream().map(arr -> arrToDTO(arr)).collect(Collectors.toList());

        PageMaker pageMaker = new PageMaker(requestDTO.getPage(),requestDTO.getSize(),(int)result.getTotalElements());

        log.info(result);

        return ListResponseDTO.<ListBoardDTO>builder()
                .dtoList(boardDTOList)
                .pageMaker(pageMaker)
                .listRequestDTO(null)
                .build();

    }
}

