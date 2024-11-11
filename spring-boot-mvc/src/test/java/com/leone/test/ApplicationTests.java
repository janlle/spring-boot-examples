package com.leone.test;

import com.leone.boot.mvc.MvcApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = MvcApplication.class)
public class ApplicationTests {


    public static void main(String[] args) {
        System.out.println(switchTest(2));
        switch (6) {
            case 6:
                System.out.println("及格");
            case 7:
                System.out.println("70分");
            case 8:
                System.out.println("80分");
            case 9:
                System.out.println("90分");
            case 10:
                System.out.println("恭喜您，满分！");
            default:
                System.out.println("default");
        }
    }

    public static int switchTest(int a) {
        int result = 0;
        switch (a) {
            case 1:
                result = result + result + a * 2;
            case 2:
                result = result + result + a * 2;
            case 3:
                result = result + result + a * 2;
        }
        return result;
    }


}