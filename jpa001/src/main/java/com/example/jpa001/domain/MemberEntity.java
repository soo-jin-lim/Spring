package com.example.jpa001.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class MemberEntity {
    @Id
    private String username;//계정
    private String password;//비번
    private String phone;//번호


}
