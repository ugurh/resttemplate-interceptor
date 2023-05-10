package io.ugurh.sphub.jobrunr;

/**
 * @author harun ugur
 * @project resttemplate-interceptor
 * @created 10.05.2023 - 23:20
 */

import org.jobrunr.scheduling.JobScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;

@RestController
public class JobController {

    private final JobScheduler jobScheduler;
    private final SampleJobService sampleJobService;

    public JobController(JobScheduler jobScheduler, SampleJobService sampleJobService) {
        this.jobScheduler = jobScheduler;
        this.sampleJobService = sampleJobService;
    }

    @GetMapping("/run-job")
    public String runJob(@RequestParam(value = "name", defaultValue = "Hello World") String name) {
        jobScheduler.enqueue(() -> sampleJobService.execute(name));
        return "Job is enqueued.";
    }

    @GetMapping("/schedule-job")
    public String scheduleJob(@RequestParam(value = "name", defaultValue = "Hello World") String name,
                              @RequestParam(value = "when", defaultValue = "PT3H") String when) {

        jobScheduler.schedule(
                Instant.now().plus(Duration.parse(when)),
                () -> sampleJobService.execute(name)
        );

        return "Job is scheduled.";
    }

}
