package com.example.sbjap001.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="reply",indexes = {
        @Index(name="idx_reply_board_bno", columnList="board_bno")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
@Builder
public class Reply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;
    private String replytext;
    private String replyer;

    public void changeText(String text){
        this.replytext=text;
    }


    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name="user_id")
    //private MemberEntity member;
}
