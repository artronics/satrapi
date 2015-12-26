package com.artronics.satrapi.persistence.seed;

import com.artronics.satrapi.entities.DeviceConnection;
import com.artronics.satrapi.entities.SdwnController;
import com.artronics.satrapi.entities.SdwnNetwork;
import com.artronics.satrapi.persistence.repositories.DeviceConnectionRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Seeder
{
    private final static Logger log = Logger.getLogger(Seeder.class);

    @Autowired
    DeviceConnectionRepo connectionRepo;

    public void seedNetwork(String ip,String connectionString){
        SdwnNetwork net = new SdwnNetwork(ip);

        SdwnController ctrl = new SdwnController();
        ctrl.setDescription("This is a controller created by seeder mainly for test purposes");
        ctrl.setSdwnNetwork(net);
        ctrl.setSinkAddress(0L);

        net.addSdwnController(ctrl);

        DeviceConnection dev = new DeviceConnection();
        dev.setSdwnController(ctrl);
        dev.setConnectionString(connectionString);

        ctrl.setDeviceConnection(dev);

        connectionRepo.save(dev);

        log.debug("create a network with one controller and a device connection");
    }
}
