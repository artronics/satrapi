package com.artronics.senator.network.map;

import com.artronics.satrapi.entities.SdwnNode;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.List;

public interface NetworkMap
{
    void addNode(SdwnNode node);

    void removeNode(SdwnNode node);

    void addLink(SdwnNode source, SdwnNode target, double weight);

    boolean hasLink(SdwnNode source, SdwnNode target);

    boolean contains(SdwnNode node);

    List<SdwnNode> getAllNodes();

    Graph<SdwnNode, DefaultWeightedEdge> getNetworkGraph();

    void removeLink(SdwnNode srcNode, SdwnNode neighbor);
}
