package com.example.jpa001.domain;

import jakarta.persistence.*;
import lombok.*;
//관계
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Reply  extends BaseEntity { //댓글에서 검색은 안할거지만 만약에 검색을 한다면 (gradle에서 컴파일 자바)
    @Id//Id임을 설정해줘야 에러가 없어짐
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    @ManyToOne(fetch = FetchType.LAZY) //reply를 기준(many)Board가 (one)
    private Board board;
    private String replytext;
    private String replyer;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="user_id")
//    private MemberEntity member;

}
