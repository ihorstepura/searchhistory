package com.ihorstepura.searchhistory.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ihorstepura.searchhistory.type.ActivityType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Table(name = "activities")
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Activity extends BaseEntity {

    @NotNull
    @Column(name = "activity_type")
    @JsonProperty(value = "activityType")
    @Enumerated(EnumType.STRING)
    private ActivityType activityType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "activities_info_id", referencedColumnName = "id")
    private ActivityInfo activityInfo;
}
