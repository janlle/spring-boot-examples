package com.leone.boot.data.serialize.hessian;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.leone.boot.common.entity.User;
import com.leone.boot.common.util.EntityFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-05
 **/
public class HessianSerialize {

    public static void main(String[] args) {
        byte[] data = serialize(EntityFactory.getUsers(1000000));
        List<User> user = deserialize(data);
    }

    public static <T> byte[] serialize(T obj) {
        long start = System.currentTimeMillis();
        HessianOutput hessianOutput = null;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            // Hessian的序列化输出
            hessianOutput = new HessianOutput(bos);
            hessianOutput.writeObject(obj);
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("hessian serialize time: " + (System.currentTimeMillis() - start));
            if (Objects.nonNull(hessianOutput)) {
                try {
                    hessianOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static <T> T deserialize(byte[] data) {
        long start = System.currentTimeMillis();
        HessianInput hessianInput = null;
        try (ByteArrayInputStream bis = new ByteArrayInputStream(data)) {
            // Hessian的反序列化读取对象
            hessianInput = new HessianInput(bis);
            return (T) hessianInput.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("hessian deserialize time: " + (System.currentTimeMillis() - start));
            if (Objects.nonNull(hessianInput)) {
                hessianInput.close();
            }
        }
        return null;
    }


}
