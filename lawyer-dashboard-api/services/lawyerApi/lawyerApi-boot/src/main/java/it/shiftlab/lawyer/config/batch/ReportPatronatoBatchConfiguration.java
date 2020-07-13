package it.shiftlab.lawyer.config.batch;

import it.shiftlab.lawyer.batch.model.BatchreportPatronatoDto;
import it.shiftlab.lawyer.batch.writer.ReportPatronatoWriter;
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
public class ReportPatronatoBatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean(name = "readCSVFileJobReportPatronato")
    public Job readCSVFileJobReportPatronato() {
        return jobBuilderFactory
                .get("readCSVFileJobReportPatronato")
                .incrementer(new RunIdIncrementer())
                .flow(step1())
                .end()
                .build();
    }
    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<BatchreportPatronatoDto, BatchreportPatronatoDto>chunk(5)
                .reader(patronatoItemReader())
                .writer(patronatoItemWrite())
                .build();
    }

    @Bean
    public FlatFileItemReader<BatchreportPatronatoDto> patronatoItemReader() {
        FlatFileItemReader<BatchreportPatronatoDto> itemReader = new FlatFileItemReader<BatchreportPatronatoDto>();
        itemReader.setLineMapper(lineMapper2());
        itemReader.setLinesToSkip(1);
        itemReader.setResource(new ClassPathResource("patronati.csv"));
        return itemReader;
    }
    @Bean
    public LineMapper<BatchreportPatronatoDto> lineMapper2() {
        DefaultLineMapper<BatchreportPatronatoDto> lineMapper = new DefaultLineMapper<BatchreportPatronatoDto>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(";");
        lineTokenizer.setNames(new String[] {"nome", "cod", "status", "convenzione", "spese",
                "decorrenzaSuccessiva", "tipoPratica", "tribunale",
                "ruoloGenerale",
                "giudice",
                "dateUdienza",
                "note",
                "patronatoProvenienza",
                "avvocatoDelegato"
        });
//        lineTokenizer.setIncludedFields(new int[] { 0, 1, 2, 3,4, 5,6,7,8,9 });
        BeanWrapperFieldSetMapper<BatchreportPatronatoDto> fieldSetMapper = new BeanWrapperFieldSetMapper<BatchreportPatronatoDto>();
        fieldSetMapper.setTargetType(BatchreportPatronatoDto.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    @Bean
    ItemWriter<BatchreportPatronatoDto> patronatoItemWrite() {
        return new ReportPatronatoWriter();
    }
}
