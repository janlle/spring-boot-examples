package com.andy.batch.tash;

import com.andy.batch.entity.Person;
import org.springframework.batch.item.validator.ValidatingItemProcessor;

import javax.validation.ValidationException;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-10-08
 **/
public class CsvItemProcessor extends ValidatingItemProcessor<Person> {

    @Override
    public Person process(Person item) throws ValidationException {

        //1需执行super.proces：（item）才会调用自定义校验器。
        super.process(item);

        //2对数据做简单的处理，若民族为汉族，则数据转换成01，其余转换成02。
        if (item.getNation().equals("汉族")) {
            item.setNation("01");
        } else {
            item.setNation("02");
        }
        return item;
    }
}
