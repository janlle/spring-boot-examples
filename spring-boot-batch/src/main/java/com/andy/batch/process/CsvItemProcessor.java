package com.andy.batch.process;

import com.andy.batch.entity.Person;
import org.springframework.batch.item.validator.ValidatingItemProcessor;

import javax.validation.ValidationException;

/**
 * <p>数据处理只需实现ItemProcessor接口，重写其process方法。方法输入的参数是从ItemReader读取到的数据，返回的数据给ItemWriter
 *
 * @author Leone
 * @since 2018-10-08
 **/
public class CsvItemProcessor extends ValidatingItemProcessor<Person> {

    @Override
    public Person process(Person item) throws ValidationException {

        // 需执行super.proces：（item）才会调用自定义校验器。
        super.process(item);

        // 对数据做简单的处理，若民族为汉族，则数据转换成01，其余转换成02。
        if (item.getNation().equals("汉族")) {
            item.setNation("01");
        } else {
            item.setNation("02");
        }
        return item;
    }
}
