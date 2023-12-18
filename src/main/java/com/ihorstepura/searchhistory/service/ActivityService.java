package com.ihorstepura.searchhistory.service;

import com.ihorstepura.searchhistory.entity.Activity;
import com.ihorstepura.searchhistory.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;

    public Activity createActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public Page<Activity> getUserActivities(Pageable pageable) {
        return activityRepository.findAll(pageable);
    }

    public Page<Activity> getUserActivitiesByType(String activityType, Pageable pageable) {
        return activityRepository.findAllByActivityType(activityType, pageable);
    }
}
