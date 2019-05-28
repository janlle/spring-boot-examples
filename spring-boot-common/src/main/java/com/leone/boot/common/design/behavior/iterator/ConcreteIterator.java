package com.leone.boot.common.design.behavior.iterator;

/**
 * <p>具体迭代子角色类
 *
 * @author leone
 * @since 2019-05-28
 **/
public class ConcreteIterator implements Iterator {

    // 持有被迭代的具体的聚合对象
    private ConcreteAggregate agg;

    // 内部索引，记录当前迭代到的索引位置
    private int index;

    // 记录当前聚集对象的大小
    private int size;

    public ConcreteIterator(ConcreteAggregate agg) {
        this.agg = agg;
        this.size = agg.size();
    }

    /**
     * 迭代方法：返还当前元素
     */
    @Override
    public Object currentItem() {
        return agg.getElement(index);
    }

    /**
     * 迭代方法：移动到第一个元素
     */
    @Override
    public void first() {
        index = 0;
    }

    /**
     * 迭代方法：是否为最后一个元素
     */
    @Override
    public boolean isDone() {
        return (index >= size);
    }

    /**
     * 迭代方法：移动到下一个元素
     */
    @Override
    public void next() {
        if (index < size) {
            index++;
        }
    }

}