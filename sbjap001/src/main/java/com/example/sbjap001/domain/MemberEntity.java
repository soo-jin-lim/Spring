package com.example.sbjap001.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class MemberEntity {
    @Id
    private String username;
    private String password;
    private String phone;

}
