package com.artronics.satrapi.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "packets")
public class PacketModel
{
    private Long id;

    private int netId;

    private List<Integer> payload;

    public PacketModel()
    {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public int getNetId()
    {
        return netId;
    }

    public void setNetId(int netId)
    {
        this.netId = netId;
    }


    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionType(type = "java.util.ArrayList")
    @CollectionTable(name = "packet_payload", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "content")
    public List<Integer> getPayload()
    {
        return payload;
    }

    public void setPayload(List<Integer> payload)
    {
        this.payload = payload;
    }
}
