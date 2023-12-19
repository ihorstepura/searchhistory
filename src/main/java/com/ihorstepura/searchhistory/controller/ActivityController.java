package com.ihorstepura.searchhistory.controller;

import com.ihorstepura.searchhistory.entity.Activity;
import com.ihorstepura.searchhistory.service.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@RestController
@RequestMapping("/activity")
@RequiredArgsConstructor
public class ActivityController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityController.class);

    private final ActivityService activityService;

    @Operation(
            summary = "Create an activity",
            description = "Returns created activity")
    @ApiResponse(responseCode = "201", description = "Successfully created")
    @PostMapping
    public ResponseEntity<Activity> createActivity(@Valid @RequestBody Activity activity) {
        Activity createdActivity = activityService.createActivity(activity);

        LOGGER.info("Activity created at {}", createdActivity.getActivityInfo().getEventDate());

        return new ResponseEntity<>(createdActivity, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get all available activities or activities by type",
            description = "Returns all activities or activities by their type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping
    public ResponseEntity<Page<Activity>> getUserActivities(@RequestParam(required = false)
                                                            @Parameter(
                                                                    name = "activityType",
                                                                    description = "Type of activity", example = "login")
                                                            String activityType,
                                                            Authentication authentication,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "3") int size) {
        try {
            Pageable paging = PageRequest.of(page, size);

            String userLogin = authentication.getName();

            Page<Activity> userActivities;
            if (activityType == null) {
                userActivities = activityService.getUserActivities(userLogin, paging);
            } else {
                userActivities = activityService.getUserActivitiesByType(userLogin, activityType, paging);
            }

            LOGGER.info("Get activity at {}", LocalTime.now());

            return new ResponseEntity<>(userActivities, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
