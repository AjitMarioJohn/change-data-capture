package com.cdc.id.allotment.data.entities;

import com.cdc.data.model.entities.EntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "allotment")
public class Allotment extends EntityBase<Allotment> {
    @Column(name = "instance_id", nullable = false)
    private String instanceId;
    @Column(name = "active", nullable = false)
    private Boolean active;

}
