package com.leone.boot.common.design.behavior.iterator;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-28
 **/
public class ConcreteAggregate extends Aggregate {

    private Object[] objArray;

    /**
     * 构造方法，传入聚合对象的具体内容
     */
    public ConcreteAggregate(Object[] objArray) {
        this.objArray = objArray;
    }

    @Override
    public Iterator createIterator() {
        return new ConcreteIterator(this);
    }

    /**
     * 取值方法：向外界提供聚集元素
     */
    public Object getElement(int index) {
        if (index < objArray.length) {
            return objArray[index];
        } else {
            return null;
        }
    }

    /**
     * 取值方法：向外界提供聚集的大小
     */
    public int size() {
        return objArray.length;
    }
}