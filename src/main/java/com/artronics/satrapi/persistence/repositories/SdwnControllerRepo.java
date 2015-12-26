package com.artronics.satrapi.persistence.repositories;

import com.artronics.satrapi.entities.SdwnController;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SdwnControllerRepo extends PagingAndSortingRepository<SdwnController,Long>, SdwnControllerCustomRepo
{
}
