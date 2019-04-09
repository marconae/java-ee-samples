package de.nae.rest;

import de.nae.threads.JobService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("job")
@Slf4j
public class JobResource {

    @Inject
    private JobService jobService;

    @GET
    public String startJob() {
        log.info("Starting Jobs...");
        for (int i = 0; i < 2; i++) {
            jobService.executeNewJob();
        }
        return "OK";
    }
}
