package org.bit.j07.todo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bit.j07.common.dto.ListResponseDTO;
import org.bit.j07.common.dto.ListRequestDTO;
import org.bit.j07.todo.dto.TodoDTO;
import org.bit.j07.todo.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
@Log4j2
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/list")                            //파라미터 수집
    public ResponseEntity<ListResponseDTO<TodoDTO>> list(ListRequestDTO listRequestDTO) {

        log.info(listRequestDTO);

        return ResponseEntity.ok(todoService.list(listRequestDTO));
        //todoService.list(listRequestDTO);

    }


    @PostMapping("/")
    public ResponseEntity<Long> register(@RequestBody TodoDTO todoDTO) {

        log.info("register................." + todoDTO);

        Long tno = todoService.register(todoDTO);


        log.info("RESULT: " + tno);

        return ResponseEntity.ok().body(tno);
    }


    @GetMapping("/{tno}")
    public ResponseEntity<TodoDTO> read(@PathVariable Long tno) {

        TodoDTO dto = todoService.read(tno);

        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{tno}")
    public ResponseEntity<Long> remove(@PathVariable Long tno) {
        log.info(tno +"159159");
        Long deletedTno = todoService.remove(tno);


        return ResponseEntity.ok().body(deletedTno);
    }

    @PutMapping("/{tno}")
    public ResponseEntity<TodoDTO> modify(@PathVariable Long tno, @RequestBody TodoDTO todoDTO) {


        log.info(tno + "tno!!!");
        log.info(todoDTO + "todoDTO!!!");

        todoDTO.setTno(tno);



        TodoDTO resultDTO = todoService.modify(todoDTO);

        return ResponseEntity.ok(resultDTO);
    }
}