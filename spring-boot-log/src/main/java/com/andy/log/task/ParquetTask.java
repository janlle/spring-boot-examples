package com.andy.log.task;

import org.apache.hadoop.fs.Path;
import org.apache.parquet.column.ParquetProperties;
import org.apache.parquet.example.data.Group;
import org.apache.parquet.example.data.simple.SimpleGroupFactory;
import org.apache.parquet.hadoop.ParquetFileWriter;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.example.ExampleParquetWriter;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.OriginalType;
import org.apache.parquet.schema.PrimitiveType;
import org.apache.parquet.schema.Types;

/**
 * <p>
 *
 * @author leone
 * @since 2019-03-21
 **/
public class ParquetTask {

    private static MessageType schema = Types.buildMessage()
            .required(PrimitiveType.PrimitiveTypeName.INT64).named("user_id")
            .required(PrimitiveType.PrimitiveTypeName.BINARY).as(OriginalType.UTF8).named("username")
            .required(PrimitiveType.PrimitiveTypeName.INT32).named("age")
            .repeatedGroup()
            .required(PrimitiveType.PrimitiveTypeName.INT32).named("order_id")
            .required(PrimitiveType.PrimitiveTypeName.BINARY).as(OriginalType.UTF8).named("create_time")
            .named("Order")
            .named("User");

    public static void main(String[] args) {
        System.out.println(schema.toString());
    }

    public void writeParquet() throws Exception {
        Path file = new Path("file:///e:\\tmp\\input\\parquet\\test.parquet");
        ExampleParquetWriter.Builder builder = ExampleParquetWriter
                .builder(file)
                .withWriteMode(ParquetFileWriter.Mode.CREATE)
                .withWriterVersion(ParquetProperties.WriterVersion.PARQUET_1_0)
                .withCompressionCodec(CompressionCodecName.SNAPPY)
                .withType(schema);

        ParquetWriter<Group> writer = builder.build();
        SimpleGroupFactory groupFactory = new SimpleGroupFactory(schema);
        String[] userLog = {"1001", "james", "6265548", "18", "1", "good man", "false", "2019-02-06 00:00:00"};

        for (int i = 0; i < 1000; i++) {
            writer.write(groupFactory.newGroup()
                    .append("userId", Long.parseLong(userLog[0]))
                    .append("account", userLog[1] + i)
                    .append("password", userLog[2])
                    .append("age", Integer.parseInt(userLog[3]))
                    .append("sex", Integer.parseInt(userLog[4]))
                    .append("description", userLog[5])
                    .append("deleted", Boolean.parseBoolean(userLog[6]))
                    .append("createTime", userLog[7]));
        }
        writer.close();
    }


}
