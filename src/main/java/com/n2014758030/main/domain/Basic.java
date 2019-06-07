package com.n2014758030.main.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
@Table
public class Basic implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String name;

    @Column
    private String label;

    @Column
    private String email;

    @Column
    private String phone;

    @Builder
    public Basic(String name, String label, String email, String phone) {
        this.name = name;
        this.label = label;
        this.email = email;
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setlabel(String lable) {
        this.label = lable;
    }

    public void setEmail(String emale) { this.email = emale; }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
