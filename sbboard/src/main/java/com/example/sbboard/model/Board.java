package com.example.sbboard.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@Entity
@Table(name="jpa_board")
public class Board {
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
    @CreationTimestamp
    private Timestamp postdate;
}
