package com.artronics.satrapi.entities;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractNode
{
    protected Long id;
    protected Long address;

    @Transient
    protected Long deviceId;

    public AbstractNode()
    {
    }

    public AbstractNode(Long address, Long deviceId)
    {
        this.address = address;
        this.deviceId = deviceId;
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

    @Column(name = "address",nullable = false,unique = false)
    public Long getAddress()
    {
        return address;
    }


    public void setAddress(Long address)
    {
        this.address = address;
    }
}
