package com.sapa.app.data.domain.glaccount.model;


import com.sapa.app.common.base.model.BaseModel;
import com.sapa.app.common.identifier.RefUtil;
import com.sapa.app.data.domain.entry.model.Entry;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class GLAccount extends BaseModel {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String accountRef;

    private List<Entry> entries; //todo set?

    @PrePersist
    public void generateRef(){
        this.accountRef = "glACC-".concat(RefUtil.generateRef());
    }
}