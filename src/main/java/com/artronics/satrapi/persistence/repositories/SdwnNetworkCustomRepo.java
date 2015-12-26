package com.artronics.satrapi.persistence.repositories;

import com.artronics.satrapi.entities.SdwnController;
import com.artronics.satrapi.entities.SdwnNetwork;

public interface SdwnNetworkCustomRepo
{
    SdwnNetwork findByIp(String ip);

    SdwnController addSdwnController(Long netId, SdwnController controller);
}
