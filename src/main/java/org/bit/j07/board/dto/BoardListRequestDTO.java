package org.bit.j07.board.dto;

import lombok.Data;
import org.bit.j07.common.dto.ListRequestDTO;

@Data
public class BoardListRequestDTO extends ListRequestDTO {

    private String type;

}
