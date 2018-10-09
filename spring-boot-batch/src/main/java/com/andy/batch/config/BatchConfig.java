package com.andy.batch.config;

import com.andy.batch.entity.Person;
import com.andy.batch.listener.CsvJobListener;
import com.andy.batch.tash.CsvBeanValidator;
import com.andy.batch.tash.CsvItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


/**
 * <p>Spring Batch的主要组成部分只需注册成Spring的bean即可。若想开启批处理的支持还需在配置类上使用@EnableBatchProcessing
 *
 * @author Leone
 * @since 2018-10-08
 **/
@Configuration
@EnableBatchProcessing
public class BatchConfig {

    /**
     * JobRepository：用户注册Job的容器
     *
     * @param dataSource
     * @param transactionManager
     * @return
     * @throws Exception
     */
    @Bean
    public JobRepository jobRepository(DataSource dataSource, PlatformTransactionManager transactionManager)
            throws Exception {
        //jobRepository的定义需要dataSource和transactionManager，Spring Boot已为我们自动配置了这两个类，Spring可通过方法注入已有的Bean。
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
        jobRepositoryFactoryBean.setDatabaseType("mysql");
        return jobRepositoryFactoryBean.getObject();
    }

    /**
     * JobLauncher：用来启动Job的接口
     *
     * @param dataSource
     * @param transactionManager
     * @return
     * @throws Exception
     */
    @Bean
    public SimpleJobLauncher jobLauncher(DataSource dataSource, PlatformTransactionManager transactionManager) throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository(dataSource, transactionManager));
        return jobLauncher;
    }


    /**
     * Job：我们要实际执行的任务，包含一个或多个Step
     *
     * @param jobBuilderFactory
     * @param step
     * @return
     */
    @Bean
    public Job importJob(JobBuilderFactory jobBuilderFactory, Step step) {
        return jobBuilderFactory
                .get("importJob")
                .incrementer(new RunIdIncrementer())
                // 为Job指定Step
                .flow(step)
                .end()
                .build();
    }


    /**
     * Step：Step-步骤包含ItemReader，ItemProcessor和ItemWriter
     *
     * @param stepBuilderFactory
     * @param reader
     * @param writer
     * @param processor
     * @return
     */
    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory,
                      ItemReader<Person> reader,
                      ItemWriter<Person> writer,
                      ItemProcessor<Person, Person> processor) {
        //1批处理每次提交65000条数据。
        return stepBuilderFactory.get("step1").<Person, Person>chunk(65000)
                //2给step绑定reader
                .reader(reader)
                //3给step绑定processor
                .processor(processor)
                //4给step绑定writer
                .writer(writer)
                .build();
    }


    /**
     * ItemReader：用来读取数据的接口
     *
     * @return
     * @throws Exception
     */
    @Bean
    public ItemReader<Person> reader() throws Exception {
        //1使用FlatFileItemReader读取文件
        FlatFileItemReader<Person> reader = new FlatFileItemReader<Person>();
        //2使用FlatFileItemReader的setResource方法设置csv文件的路径
        reader.setResource(new ClassPathResource("people.csv"));
        //3在此处对cvs文件的数据和领域模型类做对应映射
        reader.setLineMapper(new DefaultLineMapper<Person>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("name", "age", "nation", "address");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
                setTargetType(Person.class);
            }});
        }});
        return reader;
    }

    /**
     * ItemProcessor：用来处理数据的接口
     *
     * @return
     */
    @Bean
    public ItemProcessor<Person, Person> processor() {
        // 使用我们自己定义的ItemProcessor的实现CsvItemProcessor。
        CsvItemProcessor processor = new CsvItemProcessor();
        // 为processor指定校验器为CsvBeanValidator；
        processor.setValidator(csvBeanValidator());
        return processor;
    }

    /**
     * ItemWriter：用来输出数据的接口
     *
     * @param dataSource
     * @return
     */
    @Bean
    public ItemWriter<Person> writer(DataSource dataSource) {
        // 我们使用JDBC批处理的JdbcBatchItemWriter来写数据到数据库。
        JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<Person>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        String sql = "insert into person (name,age,nation,address) values ( :name, :age, :nation,:address)";
        // 在此设置要执行批处理的SQL语句。
        writer.setSql(sql);
        writer.setDataSource(dataSource);
        return writer;
    }


    @Bean
    public CsvJobListener csvJobListener() {
        return new CsvJobListener();
    }

    @Bean
    public Validator<Person> csvBeanValidator() {
        return new CsvBeanValidator<Person>();
    }

}
