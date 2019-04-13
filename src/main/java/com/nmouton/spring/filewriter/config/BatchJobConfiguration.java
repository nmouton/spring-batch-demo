package com.nmouton.spring.filewriter.config;

import com.nmouton.spring.filewriter.InvoiceProcessor;
import com.nmouton.spring.filewriter.entity.CsvInputFormat;
import com.nmouton.spring.filewriter.entity.CsvOutputFormat;
import com.nmouton.spring.filewriter.service.ApiToCsvService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

@EnableBatchProcessing
@Configuration
public class BatchJobConfiguration {


    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    ApiToCsvService apiToCsvService;

    @Bean
    public FlatFileItemReader<CsvInputFormat> csvInvoiceReader(){
        FlatFileItemReader<CsvInputFormat> reader = new FlatFileItemReader<CsvInputFormat>();
        reader.setResource(new ClassPathResource("sample-input.csv"));
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<CsvInputFormat>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("invoiceId","invoiceNumber","invoiceDate","supplier","total");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<CsvInputFormat>() {{
                setTargetType(CsvInputFormat.class);
            }});
        }});
        return reader;
    }

    @Bean
    ItemProcessor<CsvInputFormat, CsvOutputFormat> invoiceLineProcessor() {
        return new InvoiceProcessor(apiToCsvService);
    }

    private Resource outputResource = new FileSystemResource("output/outputData.csv");

    @Bean
    public FlatFileItemWriter<CsvOutputFormat> writer()
    {
        FlatFileItemWriter<CsvOutputFormat> writer = new FlatFileItemWriter<>();
        writer.setResource(outputResource);
        writer.setAppendAllowed(false);

        writer.setLineAggregator(new DelimitedLineAggregator<CsvOutputFormat>() {
            {
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor<CsvOutputFormat>() {
                    {
                        setNames(new String[] {"invoiceNumber","invoiceDate","lines"});
                    }
                });
            }
        });
        return writer;
    }

    @Bean
    public Job readCSVFilesJob() {
        return jobBuilderFactory
                .get("readCSVFilesJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1").<CsvInputFormat, CsvOutputFormat>chunk(5)
                .reader(csvInvoiceReader())
                .processor(invoiceLineProcessor())
                .writer(writer())
                .build();
    }

}
