package org.bit.j07.board.entity;

import lombok.*;
import org.bit.j07.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@ToString
//@Table(name = "tbl_reply", indexes = @Index(name = "i_reply", columnList = "i_replyText"))
//@Table(name = "tbl_reply", indexes = @Index(name = "i_reply",  columnList="i_replyText", unique = true))
@Table(name = "tbl_reply")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    private String replyText;

    @ManyToOne
    Board board;

}
