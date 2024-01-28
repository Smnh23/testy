package com.holitor.holitorservice.batch;

// import org.springframework.batch.core.Job;
// import org.springframework.batch.core.JobParameters;
// import org.springframework.batch.core.JobParametersBuilder;
// import org.springframework.batch.core.launch.JobLauncher;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BatchLauncher {

  // private @Autowired JobLauncher jobLauncher;

  // private @Autowired Job job;

  public void perform() throws Exception { 
    // JobParameters parameters = new JobParametersBuilder().toJobParameters();
    // jobLauncher.run(job, parameters);
  }

}