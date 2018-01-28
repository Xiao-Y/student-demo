package org.billow.business.model.extended;

import org.hibernate.type.Type;

/**
 * @author liuyongtao
 * @create 2018-01-08 15:31
 */
public class Param {
    private Object o;
    private Type t;

    public Param(Object value, Type type) {
        this.o = value;
        this.t = type;
    }

    public Object getValue() {
        return this.o;
    }

    public void setValue(Object o) {
        this.o = o;
    }

    public Type getType() {
        return this.t;
    }

    public void setType(Type t) {
        this.t = t;
    }
}
