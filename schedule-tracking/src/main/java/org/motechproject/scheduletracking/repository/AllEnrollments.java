package org.motechproject.scheduletracking.repository;

import org.joda.time.DateTime;
import org.motechproject.scheduletracking.domain.Enrollment;
import org.motechproject.scheduletracking.domain.EnrollmentStatus;
import org.motechproject.scheduletracking.repository.dataservices.EnrollmentDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Repository
public class AllEnrollments {

    @Autowired
    private EnrollmentDataService enrollmentDataService;

    @Transactional
    public List<Enrollment> retrieveAll() {
        return enrollmentDataService.retrieveAll();
    }

    @Transactional
    public List<Enrollment> findByExternalId(String externalId) {
        return enrollmentDataService.findByExternalId(externalId);
    }

    @Transactional
    public List<Enrollment> findByCurrentMilestone(String currentMilestone) {
        return enrollmentDataService.findByMilestoneName(currentMilestone);
    }

    @Transactional
    public List<Enrollment> findByStatus(EnrollmentStatus status) {
        return enrollmentDataService.findByStatus(status);
    }

    @Transactional
    public List<Enrollment> findByMetadataProperty(String property, String value) {
        return enrollmentDataService.executeQuery(new MapQueryExecution(property, value));
    }

    @Transactional
    public List<Enrollment> findBySchedule(List<String> scheduleNames) {
        return enrollmentDataService.findByScheduleName(new HashSet<>(scheduleNames));
    }

    @Transactional
    public List<Enrollment> completedDuring(DateTime start, DateTime end) {
        return enrollmentDataService.executeQuery(new MilestoneRangeQueryExecution(start, end));
    }
}
