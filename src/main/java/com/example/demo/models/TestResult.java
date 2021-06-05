package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "test")
@Getter
@Setter
@ToString
public class TestResult implements Serializable {
    @Id
    @Column(name = "user_id")
    Long id;
    @Column(name="q1")
    int q1;
    @Column(name="q2")
    int q2;
    @Column(name="q3")
    int q3;
    @Column(name="q4")
    int q4;
    @Column(name="q5")
    int q5;
    @Column(name="q6")
    int q6;
    @Column(name="q7")
    int q7;
    @Column(name="q8")
    int q8;
    @Column(name="q9")
    int q9;
    @Column(name="q10")
    int q10;
    @Column(name="result")
    int result;
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}
