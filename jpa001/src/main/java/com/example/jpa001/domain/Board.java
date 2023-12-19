package com.example.jpa001.domain;

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

//    @ManyToOne(fetch = FetchType.LAZY)//Lazy가 명시적으로 부를때만이고 만약에 eager면 항상 부름
//    @JoinColumn(name= "user_id")
//    private MemberEntity member;
//    @OneToMany(mappedBy = "reply")//양방향 매핑
//    private List<Reply> replyList;
    public void change(String title, String content, String writer){
        this.title=title;
        this.content=content;
        this.writer=writer;
    }
    public void updateVisitcount(){
        this.visitcount++;
    }

}
