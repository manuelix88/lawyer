package it.shiftlab.lawyer.config.batch;

import it.shiftlab.lawyer.batch.model.ReportInfoAmministrativeDto;
import it.shiftlab.lawyer.batch.writer.ReportInfoAmministrativeWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class ReportAmministrativeBatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean(name = "readCSVFileJobReportInfoAmm")
    public Job readCSVFileJobReportInfoAmm() {
        return jobBuilderFactory
                .get("readCSVFileJobReportInfoAmm")
                .incrementer(new RunIdIncrementer())
                .flow(step())
                .end()
                .build();
    }
    @Bean
    public Step step() {
        return stepBuilderFactory.get("step")
                .<ReportInfoAmministrativeDto, ReportInfoAmministrativeDto>chunk(20)
                .reader(employeeItemReader())
                .writer(anagraficaItemWrite())
                .build();
    }


    @Bean
    public FlatFileItemReader<ReportInfoAmministrativeDto> employeeItemReader() {
        FlatFileItemReader<ReportInfoAmministrativeDto> itemReader = new FlatFileItemReader<ReportInfoAmministrativeDto>();
        itemReader.setLineMapper(lineMapper());
        itemReader.setLinesToSkip(1);
        itemReader.setResource(new ClassPathResource("employees.csv"));
        return itemReader;
    }

    @Bean
    public LineMapper<ReportInfoAmministrativeDto> lineMapper() {
        DefaultLineMapper<ReportInfoAmministrativeDto> lineMapper = new DefaultLineMapper<ReportInfoAmministrativeDto>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(";");
        lineTokenizer.setNames(new String[] {"provincia", "nome", "fiscale", "ricorso", "documentazione",
                "pagamento", "qualifica", "faldone", "altro", "note"});
//        lineTokenizer.setIncludedFields(new int[] { 0, 1, 2, 3,4, 5,6,7,8,9 });
        BeanWrapperFieldSetMapper<ReportInfoAmministrativeDto> fieldSetMapper = new BeanWrapperFieldSetMapper<ReportInfoAmministrativeDto>();
        fieldSetMapper.setTargetType(ReportInfoAmministrativeDto.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    @Bean
    ItemWriter<ReportInfoAmministrativeDto> anagraficaItemWrite() {
        return new ReportInfoAmministrativeWriter();
    }


}
