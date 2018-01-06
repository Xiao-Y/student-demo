package org.billow.business.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 航线
 *
 * @author liuyongtao
 * @create 2018-01-04 14:19
 */
@Entity
@Table(name = "sec_air_line")
public class AirLine {

    //用于标识该属性为实体的标识符，专门用于复合主键类
    @EmbeddedId
    private AirLinePK id;
    @Column(length = 20)
    private String name;

    public AirLine() {
    }

    public AirLine(String startCity, String endCity, String name) {
        this.id = new AirLinePK(startCity, endCity);
        this.name = name;
    }

    public AirLine(AirLinePK id, String name) {
        this.id = id;
        this.name = name;
    }

    public AirLinePK getId() {
        return id;
    }

    public void setId(AirLinePK id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AirLine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
