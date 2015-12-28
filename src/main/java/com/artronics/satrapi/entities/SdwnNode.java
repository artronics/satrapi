package com.artronics.satrapi.entities;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.Date;

@Entity
@Table(name = "nodes")
public class SdwnNode
{
    private Long id;
    private DeviceConnection device;

    private Long address;

    //Normal as default value
    private Type type=Type.NORMAL;
    private Status status = Status.ACTIVE;
    private int battery;

    protected Date created;
    protected Date updated;

    public SdwnNode()
    {
    }

    public SdwnNode(Long address)
    {
        this.address = address;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true)
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    public DeviceConnection getDevice()
    {
        return device;
    }

    public void setDevice(DeviceConnection device)
    {
        this.device = device;
    }

    @Column(name = "address",nullable = false,unique = false)
    public Long getAddress()
    {
        return address;
    }


    public void setAddress(Long address)
    {
        this.address = address;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "type",nullable = false)
    public Type getType()
    {
        return type;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    @Max(255)
    @Column(name = "battery")
    public int getBattery()
    {
        return battery;
    }

    public void setBattery(int battery)
    {
        this.battery = battery;
    }

    public Date getCreated()
    {
        return created;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }

    public Date getUpdated()
    {
        return updated;
    }

    public void setUpdated(Date updated)
    {
        this.updated = updated;
    }

    @PrePersist
    protected void onCreate()
    {
        created = new Date();
    }

    @PreUpdate
    protected void onUpdate()
    {
        updated = new Date();
    }

    public enum Type{
        SINK,
        NORMAL
    }
    public enum Status{
        ACTIVE,
        DISABLE,
    }
}
