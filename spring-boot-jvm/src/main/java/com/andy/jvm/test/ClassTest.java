package com.andy.jvm.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ClassTest extends FileInputStream implements Runnable, ActionListener {

    public ClassTest(String name) throws FileNotFoundException {
        super(name);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void run() {

    }

}
