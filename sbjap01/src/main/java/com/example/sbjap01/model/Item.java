package com.example.sbjap01.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="tbl_item")
public class Item {
    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false,length = 100)//null이 없움. 문자열 길이 100
    private String itemName;
    @Column(name="item_price", nullable = false)
    private int price;
    @Lob
    private String itemDetail;
    private LocalDateTime regTime;


}
