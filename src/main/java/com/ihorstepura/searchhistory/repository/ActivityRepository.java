package com.ihorstepura.searchhistory.repository;

import com.ihorstepura.searchhistory.entity.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    @Query(value = "SELECT a.id, a.activities_info_id, a.activity_type FROM activities a " +
            "LEFT JOIN activities_info ai ON a.activities_info_id = ai.id " +
            "WHERE a.activity_type = :activityType",
            countQuery = "SELECT count(*) FROM activities WHERE activity_type = :activityType",
            nativeQuery = true)
    Page<Activity> findAllByActivityType(@Param("activityType") String activityType, Pageable pageable);
}
