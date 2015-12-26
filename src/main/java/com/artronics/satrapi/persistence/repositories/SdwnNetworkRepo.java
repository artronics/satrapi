package com.artronics.satrapi.persistence.repositories;

import com.artronics.satrapi.entities.SdwnNetwork;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SdwnNetworkRepo extends PagingAndSortingRepository<SdwnNetwork,Long>,SdwnNetworkCustomRepo
{
}
