package com.ihorstepura.searchhistory.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ihorstepura.searchhistory.type.ActivityType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Table(name = "activities_info")
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class ActivityInfo extends BaseEntity {

    @NotNull
    @Column(name = "activity_type")
    @JsonProperty(value = "activityType")
    private ActivityType activityType;

    @NotBlank
    @Column(name = "endpoint")
    @JsonProperty(value = "endpoint")
    private String endpoint;

    @Column(nullable = false, updatable = false, name = "event_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @JsonProperty(value = "eventDate")
    private Date eventDate;

    @NotBlank
    @Column(name = "user_login")
    @JsonProperty(value = "userLogin")
    private String userLogin;

    @OneToOne(mappedBy = "activityInfo")
    @JsonIgnore
    private Activity activity;

}
