package de.nae.threads;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.control.RequestContextController;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
@Slf4j
public class JobService {

    @Resource
    private ManagedExecutorService executorService;

    @Inject
    private Instance<Job> jobInstance;

    @Inject
    private RequestContextController requestContextController;

    @Getter
    private final AtomicInteger threadCounter = new AtomicInteger(0);

    public void executeNewJob() {
        executorService.submit(() -> {
            requestContextController.activate();
            try {
                final Job job = jobInstance.get();
                job.work();
                jobInstance.destroy(job);
                threadCounter.getAndIncrement();
            } finally {
                requestContextController.deactivate();
            }
        });
    }

}
