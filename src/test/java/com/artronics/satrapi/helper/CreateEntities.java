package com.artronics.satrapi.helper;

import com.artronics.satrapi.entities.DeviceConnection;
import com.artronics.satrapi.entities.SdwnController;
import com.artronics.satrapi.entities.SdwnNetwork;
import com.artronics.satrapi.entities.SdwnNode;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class CreateEntities
{
    private final static Logger log = Logger.getLogger(CreateEntities.class);

    private static List<SdwnController> createController(int num){
        List<SdwnController> controllers = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            controllers.add(createCtrl());
        }

        return controllers;
    }

    public static SdwnNetwork createNet(String ip)
    {
        SdwnNetwork net = new SdwnNetwork(ip);

        return net;
    }

    public static DeviceConnection createDevCon(String conStr)
    {
        DeviceConnection dev = new DeviceConnection(conStr);

        return dev;
    }
    public static DeviceConnection createDevCon(Long id,String conStr)
    {
        DeviceConnection dev = new DeviceConnection(conStr);
        dev.setId(id);

        return dev;
    }

    public static SdwnController createCtrl()
    {
        SdwnController con = new SdwnController();
        con.setSinkAddress(43L);

        return con;
    }

    public static SdwnController createCtrl(Long id,Long sinkAdd)
    {
        SdwnController con = new SdwnController();
        con.setId(id);
        con.setSinkAddress(sinkAdd);

        return con;
    }

    public static List<SdwnNode> createNodes(int num){
        List<SdwnNode> nodes = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            SdwnNode node = new SdwnNode(Integer.toUnsignedLong(i));
            nodes.add(node);
        }

        return nodes;
    }
}
