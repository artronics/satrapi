package com.artronics.satrapi.persistence.repositories;

import com.artronics.satrapi.entities.SdwnNode;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SdwnNodeRepo extends PagingAndSortingRepository<SdwnNode,Long>
{
}
