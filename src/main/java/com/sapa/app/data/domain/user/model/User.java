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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseModel implements UserDetails {

    @Column(unique = true, nullable = false) //user deletion not yet
    private String username;

    private String email;
    private String phoneNumber;
    private String password;
    private Set<GrantedAuthority> authorities;

    @OneToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;
}