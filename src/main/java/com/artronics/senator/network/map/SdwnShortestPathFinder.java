package com.artronics.senator.network.map;


import com.artronics.satrapi.entities.SdwnNode;
import com.artronics.senator.network.map.graph.GraphDelegator;

import java.util.List;

public class SdwnShortestPathFinder implements ShortestPathFinder
{
    private final NetworkMap networkMap;
    private final GraphDelegator graphDelegator;

    public SdwnShortestPathFinder(NetworkMap networkMap)
    {
        this.networkMap = networkMap;
        this.graphDelegator = new GraphDelegator(networkMap.getNetworkGraph());
    }

    @Override
    public List<SdwnNode> getShortestPath(SdwnNode src, SdwnNode dst)
    {
        return graphDelegator.getShortestPath(src, dst);
    }
}
