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
@Table(name = "sensordata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sensordata.findAll", query = "SELECT s FROM Sensordata s"),
    @NamedQuery(name = "Sensordata.findById", query = "SELECT s FROM Sensordata s WHERE s.id = :id"),
    @NamedQuery(name = "Sensordata.findBySensorid", query = "SELECT s FROM Sensordata s WHERE s.sensorid = :sensorid"),
    @NamedQuery(name = "Sensordata.findByData", query = "SELECT s FROM Sensordata s WHERE s.data = :data"),
    @NamedQuery(name = "Sensordata.findByUpdatetime", query = "SELECT s FROM Sensordata s WHERE s.updatetime = :updatetime")})
public class Sensordata implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "sensorid")
    private String sensorid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "data")
    private BigDecimal data;
    @Column(name = "updatetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatetime;

    public Sensordata() {
    }

    public Sensordata(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSensorid() {
        return sensorid;
    }

    public void setSensorid(String sensorid) {
        this.sensorid = sensorid;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sensordata)) {
            return false;
        }
        Sensordata other = (Sensordata) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.farmon.entities.Sensordata[ id=" + id + " ]";
    }
    
}
