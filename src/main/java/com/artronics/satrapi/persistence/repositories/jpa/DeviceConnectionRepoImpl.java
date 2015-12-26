package com.artronics.satrapi.persistence.repositories.jpa;

import com.artronics.satrapi.entities.DeviceConnection;
import com.artronics.satrapi.entities.SdwnController;
import com.artronics.satrapi.persistence.repositories.DeviceConnectionCustomRepo;
import com.artronics.satrapi.persistence.repositories.SdwnControllerRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class DeviceConnectionRepoImpl implements DeviceConnectionCustomRepo
{
    private final static Logger log = Logger.getLogger(DeviceConnectionRepoImpl.class);

    @PersistenceContext
    EntityManager em;

    @Autowired
    SdwnControllerRepo controllerRepo;

    @Override
    //TODO change name to findByController(SdwnController,Long id)
    public DeviceConnection findByPath(Long networkId, Long controllerId, Long id)
    {
        SdwnController controller = controllerRepo.findOne(controllerId);
        if (controller == null) {
            return null;
        }

        DeviceConnection device = em.find(DeviceConnection.class, id);
        if (controller.getDeviceConnection().getId().equals(device.getId()))
            return null;

        return device;
    }
}
