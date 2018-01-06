package org.billow.business.model;

import org.billow.business.model.AirLinePK;

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
    private AirLinePK airLinePK;
    @Column(length = 20)
    private String name;

    public AirLine() {
    }

    public AirLine(String startCity, String endCity, String name) {
        this.airLinePK = new AirLinePK(startCity, endCity);
        this.name = name;
    }

    public AirLine(AirLinePK airLinePK, String name) {
        this.airLinePK = airLinePK;
        this.name = name;
    }

    public AirLinePK getAirLinePK() {
        return airLinePK;
    }

    public void setAirLinePK(AirLinePK airLinePK) {
        this.airLinePK = airLinePK;
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
                "airLinePK=" + airLinePK +
                ", name='" + name + '\'' +
                '}';
    }
}
