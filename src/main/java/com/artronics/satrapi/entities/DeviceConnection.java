package com.artronics.satrapi.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "device_connections")
public class DeviceConnection
{
    private Long id;

    private List<SdwnNode> nodes=new ArrayList<>();

    protected String connectionString;

    protected SdwnController sdwnController;

    protected Date created;
    protected Date updated;

    public DeviceConnection()
    {
    }

    public DeviceConnection(String connectionString)
    {
        this.connectionString = connectionString;
    }

    @GenericGenerator(name = "generator", strategy = "foreign",
            parameters = @Parameter(name = "property", value = "sdwnController"))
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id",nullable = false,unique = true)
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    public List<SdwnNode> getNodes()
    {
        return nodes;
    }

    public void setNodes(List<SdwnNode> nodes)
    {
        this.nodes = nodes;
    }

    @Column(name = "connection_string",nullable = false,unique = false)
    public String getConnectionString()
    {
        return connectionString;
    }

    public void setConnectionString(String connectionString)
    {
        this.connectionString = connectionString;
    }

    @OneToOne(fetch = FetchType.EAGER)//,mappedBy = "deviceConnection")
    @PrimaryKeyJoinColumn
    public SdwnController getSdwnController()
    {
        return sdwnController;
    }

    public void setSdwnController(SdwnController sdwnController)
    {
        this.sdwnController = sdwnController;
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

    public void addSdwnNode(SdwnNode node){
        nodes.add(node);
    }
}
