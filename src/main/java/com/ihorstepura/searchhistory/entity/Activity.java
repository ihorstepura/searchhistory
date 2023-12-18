package com.ihorstepura.searchhistory.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Table(name = "activities")
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Activity extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "activities_info_id", referencedColumnName = "id")
    private ActivityInfo activityInfo;
}

