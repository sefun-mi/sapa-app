package com.sapa.app.data.domain.transaction.model;

import com.sapa.app.common.base.model.BaseModel;
import com.sapa.app.common.identifier.RefUtil;
import com.sapa.app.data.domain.entry.model.Entry;
import com.sapa.app.data.domain.transaction.enums.TransactionStatus;
import jakarta.persistence.*;
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
public class Transaction extends BaseModel {

    @Column(nullable = false)
    private String txRef;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(50)")
    private TransactionStatus status;

    private List<Entry> entries; //todo set?

    @PrePersist
    public void generateRef(){
        this.txRef = "tx-".concat(RefUtil.generateRef());
    }
}