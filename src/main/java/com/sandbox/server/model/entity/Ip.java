package com.sandbox.server.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
public class Ip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ipNum;
    String ip;
    String data;
    public Ip() {
    }
}
