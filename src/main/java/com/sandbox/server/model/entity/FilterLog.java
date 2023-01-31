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
public class FilterLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long seq;
    String header;
    String request;
    String response;
    public FilterLog() {

    }
}
