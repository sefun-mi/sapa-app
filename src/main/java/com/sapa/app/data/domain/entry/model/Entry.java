package com.sapa.app.data.domain.entry.model;

import com.sapa.app.common.base.model.BaseModel;
import com.sapa.app.common.identifier.RefUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Entry extends BaseModel {

    @Column(nullable = false)
    private String entryRef;

    @Column(precision = 20, scale = 4)
    private BigDecimal credit;

    @Column(precision = 20, scale = 4)
    private BigDecimal debit;

    @PrePersist
    public void generateRef(){
        this.entryRef = "en-".concat(RefUtil.generateRef());
    }
}