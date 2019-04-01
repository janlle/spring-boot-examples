package com.andy.log.util;

import org.apache.hadoop.fs.Path;
import org.apache.parquet.column.ParquetProperties;
import org.apache.parquet.example.data.Group;
import org.apache.parquet.example.data.simple.SimpleGroupFactory;
import org.apache.parquet.hadoop.ParquetFileWriter;
import org.apache.parquet.hadoop.ParquetReader;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.example.ExampleParquetWriter;
import org.apache.parquet.hadoop.example.GroupReadSupport;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.MessageTypeParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * <p>
 *
 * @author leone
 * @since 2019-03-21
 **/
public class ParquetUtil {

    private static Logger logger = LoggerFactory.getLogger(ParquetUtil.class);


    private static String schemaStr = "message schema {"
            + "optional int64 user_id;"
            + "optional binary account (UTF8);"
            + "optional int32 age;"
            + "optional int32 sex;"
            + "optional binary description (UTF8);"
            + "optional boolean deleted;"
            + "optional binary create_time (UTF8);}";

    private static MessageType schema = MessageTypeParser.parseMessageType(schemaStr);

    public static void main(String[] args) {
        String inputPath = "e:\\tmp\\input\\parquet\\user.parquet";
        String outputPath = "e:\\tmp\\input\\parquet\\user.parquet";
    }

    /**
     * 生成parquet文件
     *
     * @throws IOException
     */
    public static void parquetWriter(Long count, String outputPath) throws IOException {
        ExampleParquetWriter.Builder builder = ExampleParquetWriter
                .builder(new Path(outputPath))
                .withWriteMode(ParquetFileWriter.Mode.CREATE)
                .withWriterVersion(ParquetProperties.WriterVersion.PARQUET_1_0)
                .withCompressionCodec(CompressionCodecName.SNAPPY)
                .withType(schema);
        ParquetWriter<Group> writer = builder.build();
        SimpleGroupFactory groupFactory = new SimpleGroupFactory(schema);
        for (long i = 0; i < count; i++) {
            writer.write(groupFactory.newGroup()
                    .append("user_id", i + RandomValue.random.nextInt(1000000000))
                    .append("account", RandomValue.randomNum(16))
                    .append("age", (RandomValue.random.nextInt(60) + 12))
                    .append("sex", RandomValue.random.nextInt(2))
                    .append("description", RandomValue.randomMessage())
                    .append("deleted", RandomValue.random.nextBoolean())
                    .append("create_time", RandomValue.randomTime()));
        }
        writer.close();
    }

    /**
     * 读取parquet文件
     *
     * @throws IOException
     */
    private static void parquetReader(String inputPath) throws IOException {
        GroupReadSupport readSupport = new GroupReadSupport();
        ParquetReader<Group> reader = new ParquetReader<>(new Path(inputPath), readSupport);
        Group line;
        while ((line = reader.read()) != null) {
            System.out.println(line.toString());
        }
        reader.close();
    }

}
