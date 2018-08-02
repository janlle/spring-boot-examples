package com.andy.design.structure.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 枝节点类
 *
 * @author lyon
 * @since: 2018-08-01
 */
public class ConcreteCompany extends Component {

    private List<Component> list;

    public ConcreteCompany(String name) {
        super(name);
        list = new ArrayList<>();
    }

    public ConcreteCompany() {
        list = new ArrayList<>();
    }

    @Override
    public void add(Component component) {
        list.add(component);
    }

    @Override
    public void remove(Component component) {
        list.remove(component);
    }

    @Override
    public void display(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("-");
        }
        System.out.println(sb.toString() + this.getName());
        for (Component c : list) {
            c.display(depth + 2);
        }
    }


}