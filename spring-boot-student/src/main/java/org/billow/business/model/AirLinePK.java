package org.billow.business.model;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * 联合主键。一般使用PK 只需要定义用作主键的字段
 * <p>
 * 联合主键类必须遵守的JPA规范：<br>
 * 1、必须要提供一个public的无参数的构造方法<br>
 * 2、必须要实现序列化接口<br>
 * 3、必须重写hashCode（）与equals（）方法<br/>
 * -@Embeddable 该类中的属性用在持久化的类中的字段
 *
 * @author liuyongtao
 * @create 2018-01-04 14:14
 */
@Embeddable
public class AirLinePK implements Serializable {

    @Column(length = 30, nullable = false)
    private String startCity;// PEK，北京 CAN广州，SHA上海
    @Column(length = 30, nullable = false)
    private String endCity;

    public AirLinePK() {
    }

    public AirLinePK(String startCity, String endCity) {
        this.startCity = startCity;
        this.endCity = endCity;
    }


    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }


    public String getEndCity() {
        return endCity;
    }

    public void setEndCity(String endCity) {
        this.endCity = endCity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AirLinePK airLinePK = (AirLinePK) o;

        if (startCity != null ? !startCity.equals(airLinePK.startCity) : airLinePK.startCity != null) return false;
        return endCity != null ? endCity.equals(airLinePK.endCity) : airLinePK.endCity == null;
    }

    @Override
    public int hashCode() {
        int result = startCity != null ? startCity.hashCode() : 0;
        result = 31 * result + (endCity != null ? endCity.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AirLinePK{" +
                "startCity='" + startCity + '\'' +
                ", endCity='" + endCity + '\'' +
                '}';
    }
}
