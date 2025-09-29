package com.sapa.app.data.domain.user.model;

import com.sapa.app.common.base.model.BaseModel;
import com.sapa.app.data.domain.wallet.model.Wallet;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseModel {

    @Column(unique = true, nullable = false) //todo user deletion not yet
    private String username;

    private String email;
    private String phoneNumber;
    private String password;
    private Set<String> permissions = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;
}