package com.andy.batch.process;

import com.andy.batch.entity.Person;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;

import javax.validation.ValidationException;
import java.text.SimpleDateFormat;

/**
 * <p>数据处理只需实现ItemProcessor接口，重写其process方法。方法输入的参数是从ItemReader读取到的数据，返回的数据给ItemWriter
 *
 * @author Leone
 * @since 2018-10-08
 **/
public class CsvValidatingItemProcessor extends ValidatingItemProcessor<Person> {

    @Override
    public Person process(Person item) throws ValidationException {
        // 需执行super.process (item) 才会调用自定义校验器
        super.process(item);
        // 对数据做简单的处理 将性别装换为中文
        if (item.getSex().equals("1")) {
            item.setSex("男");
        } else {
            item.setSex("女");
        }
        return item;
    }
}
