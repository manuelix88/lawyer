package it.shiftlab.lawyer.controller;

import it.shiftlab.lawyer.dto.CodiceDto;
import it.shiftlab.lawyer.models.GenericResponseMessage;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BatchController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier("readCSVFileJobReportInfoAmm")
    private Job job1;
    @Autowired
    @Qualifier("readCSVFileJobReportPatronato")
    private Job job2;



    @GetMapping("public/batch/reportPatronato")
    public ResponseEntity<?> readCSVFileJobReportPatronato() {
        JobParameters jobParameters = new JobParameters();
        try {
            jobLauncher.run(job2, jobParameters);
            return ResponseEntity.ok(new GenericResponseMessage(HttpStatus.OK.toString(), "Batch eseguito correttamente"));
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
                | JobParametersInvalidException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new GenericResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage()));

        }
    }

    @GetMapping("public/batch/reportInfoAmm")
    public ResponseEntity<?> readCSVFileJobReportInfoAmm() {
        JobParameters jobParameters = new JobParameters();
        try {
            jobLauncher.run(job1, jobParameters);
            return ResponseEntity.ok(new GenericResponseMessage(HttpStatus.OK.toString(), "Batch eseguito correttamente"));
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
                | JobParametersInvalidException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new GenericResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage()));

        }
    }
}
