package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "messаges")
@Getter
@Setter
@ToString
public class BotMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    Long id;
    @Column(name="login")
    String login;
    @Column(name = "message")
    String message;

}
