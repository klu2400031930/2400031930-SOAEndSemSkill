package com.klef.soa.exam.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity @Table(name="tourist_table") @Data @NoArgsConstructor @AllArgsConstructor
public class Tourist {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 private String name;
 private String email;
 private String country;
 private String contact;
}
