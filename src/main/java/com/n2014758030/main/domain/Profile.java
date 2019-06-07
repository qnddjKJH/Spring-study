package com.n2014758030.main.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table
public class Profile implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String network;

    @Column
    private String userName;

    @Column
    private String url;

    @Column
    private LocalDateTime createDate;

    @Builder
    public Profile(String network, String userName, String url, LocalDateTime createDate) {
        this.network = network;
        this.userName = userName;
        this.url = url;
        this.createDate = createDate;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
