package com.artronics.senator.network.map;

import com.artronics.satrapi.entities.SdwnNode;

import java.util.List;

public interface ShortestPathFinder
{
    List<SdwnNode> getShortestPath(SdwnNode src, SdwnNode dst);
}
