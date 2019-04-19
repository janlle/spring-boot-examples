package com.leone.batch.process;

import com.leone.batch.entity.Person;
import org.springframework.batch.item.file.LineMapper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *
 * @author leone
 * @since 2019-01-21
 **/
public class CsvLineMapper implements LineMapper<Person> {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    public Person mapLine(String line, int lineNumber) throws Exception {
        String[] lines = line.split(",");
        Date date = sdf.parse(lines[4]);
        return new Person(lines[0], Integer.valueOf(lines[1]), lines[2], lines[3], date);
    }

}