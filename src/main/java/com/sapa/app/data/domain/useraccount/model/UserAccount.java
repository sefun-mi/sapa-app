package com.sapa.app.data.domain.useraccount.model;

import com.sapa.app.common.base.model.BaseModel;
import com.sapa.app.common.identifier.RefUtil;
import com.sapa.app.data.domain.entry.model.Entry;
import com.sapa.app.data.domain.wallet.model.Wallet;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount extends BaseModel {

    @Column(nullable = false)
    private String accountRef;

    private List<Entry> entries; //todo set?

    @OneToOne(mappedBy = "userAccount")
    private Wallet wallet;

    @PrePersist
    public void generateRef(){
        this.accountRef = "uACC-".concat(RefUtil.generateRef());
    }
}