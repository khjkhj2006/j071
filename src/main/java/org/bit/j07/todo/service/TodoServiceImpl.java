package org.bit.j07.todo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bit.j07.common.dto.ListResponseDTO;
import org.bit.j07.common.dto.PageMaker;
import org.bit.j07.common.dto.ListRequestDTO;
import org.bit.j07.todo.dto.TodoDTO;
import org.bit.j07.todo.entity.Todo;
import org.bit.j07.todo.repository.TodoRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;

    @Override
    public ListResponseDTO<TodoDTO> list(ListRequestDTO dto) {

        //이때부터 타입이 Entity로 바뀜(Todo)
        Page<Todo> result = todoRepository.listWithSearch(dto.getKeyword(),dto.getPageable());

        // Pageable pageable = PageRequest.of(dto.getPage() -1)
        //Pageable pageable = dto.getPageable();

        //목록데이터 가져오기
        //TotoEntity 객체를 TodoDTO로 다시 바꿔줌
        //타입이 펑션이어야 하기때문에 펑션타입으로 선언한 것
        //Function<Todo, TodoDTO> fn = (todo) -> entityToDTO(todo);

        //또는 이렇게도 가능. 스트림으로 나열 후 묶어 컬렉트
        List<TodoDTO> dtoList = result.getContent().stream()
                .map( (todo) -> entityToDTO(todo))
                .collect(Collectors.toList());

        //페이지정보 불러오기. 원하는 정보(페이지번호, 페이지사이즈, 시작페이지번호, 끝페이지번호, 번호목록, 이전여부, 다음여부) 먼저 뽑는다
        PageMaker pageMaker = new PageMaker(dto.getPage(),dto.getSize(),(int)result.getTotalElements());

        log.info(pageMaker);

        return ListResponseDTO.<TodoDTO>builder()
                .dtoList(dtoList)
                .pageMaker(pageMaker)
                .listRequestDTO(dto)
                .build();
    }

    @Override
    public Long register(TodoDTO dto) {

        log.info(dto);

        Todo entity = dtoToEntity(dto);

        //todoRepository save  마리아디비 값저장
        Todo todo =  todoRepository.save(entity);

        return todo.getTno();
    }

    @Override
    public TodoDTO read(Long tno) {

        Optional<Todo> result = todoRepository.findById(tno);

        log.info(result);

        if(result.isPresent()){
            Todo todo = result.get();
            return entityToDTO(todo);
        }

        return null;
    }

    @Override
    public Long remove(Long tno) {

         todoRepository.deleteById(tno);
        log.info(tno + "dddd");

        return tno;
    }

    @Override
    public TodoDTO modify(TodoDTO todoDTO) {

        Optional<Todo> result = todoRepository.findById(todoDTO.getTno());


        if(result.isPresent()){

            Todo entity = result.get();
            entity.changeTitle(todoDTO.getContent());
            entity.changeDel(todoDTO.isDel());

            todoRepository.save(entity);

            return entityToDTO(entity);
        }
        return null;

    }
}

