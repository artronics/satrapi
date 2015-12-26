package com.artronics.satrapi.persistence.repositories;

import com.artronics.satrapi.entities.SdwnController;

import java.util.List;

public interface SdwnControllerCustomRepo
{
    SdwnController findByNetwork(Long networkId, Long id);

    List<SdwnController> findByNetwork(Long networkId);
}
