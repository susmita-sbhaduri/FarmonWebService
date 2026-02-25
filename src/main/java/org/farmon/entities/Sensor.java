/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.farmon.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author sb
 */
@Entity
@Table(name = "sensor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sensor.findAll", query = "SELECT s FROM Sensor s"),
    @NamedQuery(name = "Sensor.findByIdsensor", query = "SELECT s FROM Sensor s WHERE s.idsensor = :idsensor"),
    @NamedQuery(name = "Sensor.findByParameter", query = "SELECT s FROM Sensor s WHERE s.parameter = :parameter"),
    @NamedQuery(name = "Sensor.findByData", query = "SELECT s FROM Sensor s WHERE s.data = :data"),
    @NamedQuery(name = "Sensor.findByUpdatetime", query = "SELECT s FROM Sensor s WHERE s.updatetime = :updatetime")})
public class Sensor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idsensor")
    private Integer idsensor;
    @Size(max = 100)
    @Column(name = "parameter")
    private String parameter;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "data")
    private BigDecimal data;
    @Column(name = "updatetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatetime;

    public Sensor() {
    }

    public Sensor(Integer idsensor) {
        this.idsensor = idsensor;
    }

    public Integer getIdsensor() {
        return idsensor;
    }

    public void setIdsensor(Integer idsensor) {
        this.idsensor = idsensor;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public BigDecimal getData() {
        return data;
    }

    public void setData(BigDecimal data) {
        this.data = data;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsensor != null ? idsensor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sensor)) {
            return false;
        }
        Sensor other = (Sensor) object;
        if ((this.idsensor == null && other.idsensor != null) || (this.idsensor != null && !this.idsensor.equals(other.idsensor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.farmon.entities.Sensor[ idsensor=" + idsensor + " ]";
    }
    
}
