package com.example.sbjap001.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name="jpa_board")
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;
    @Column(nullable = false, length = 200)
    private String title;
    @Lob
    @Column(nullable = false)
    private String content;
    @Column(nullable = false, length = 45)
    private String writer;
    @ColumnDefault("0")
    private int visitcount;
    //private int replaycount;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="user_id")
//    private MemberEntity member;
//    @OneToMany(mappedBy = "reply")
//    private List<Reply> replyList;

    public void change(String title, String content, String writer){
        this.title=title;
        this.content=content;
        this.writer=writer;
    }
    public void updateVisitcount(){
        this.visitcount++;
    }

//    public void updateReplycout(){
//        this.replaycount++;
//    }

}
