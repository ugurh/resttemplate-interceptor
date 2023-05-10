package io.ugurh.sphub;

import org.jobrunr.storage.StorageProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author harun ugur
 * @project resttemplate-interceptor
 * @created 10.05.2023 - 23:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class JobEndpointTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    StorageProvider storageProvider;

    @Test
    @DisplayName("Test job scheduled.")
    void givenEndpoint_whenJobEnqueued_thenJobIsProcessedWithin30Seconds() {
        String response = runJobViaRest("from-test");
        assertEquals("Job is enqueued.", response);

        await()
                .atMost(30, TimeUnit.SECONDS)
                .until(() -> storageProvider.countRecurringJobs() == 1);
    }

    @Test
    @DisplayName("Test job scheduled.")
    void givenEndpoint_whenJobScheduled_thenJobIsScheduled() {
        String response = scheduleJobViaRest("from-test", Duration.ofHours(3));
        assertEquals("Job is scheduled.", response);

        await()
                .atMost(30, TimeUnit.SECONDS)
                .until(() -> storageProvider.countRecurringJobs() == 1);
    }

    private String runJobViaRest(String input) {
        return restTemplate.getForObject(
                "http://localhost:8081/run-job?name=" + input,
                String.class);
    }

    private String scheduleJobViaRest(String input, Duration duration) {
        return restTemplate.getForObject(
                "http://localhost:8081/schedule-job?name=" + input + "&when=" + duration.toString(), String.class);
    }

}
