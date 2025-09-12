package com.sapa.app.data.domain.wallet.model;

import com.sapa.app.common.base.model.BaseModel;
import com.sapa.app.data.domain.user.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Wallet extends BaseModel {

    @OneToOne(mappedBy = "wallet")
    private User user;
}