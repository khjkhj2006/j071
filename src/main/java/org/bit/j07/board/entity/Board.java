package org.bit.j07.board.entity;

import lombok.*;
import org.bit.j07.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "tbl_board")
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;
    private String title;
    private String content;
    private String writer;

    public void change(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

}
