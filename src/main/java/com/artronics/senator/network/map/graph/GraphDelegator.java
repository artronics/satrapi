package com.artronics.senator.network.map.graph;

import com.artronics.satrapi.entities.SdwnNode;
import org.jgrapht.Graph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.*;

public class GraphDelegator
{
    private final Graph<SdwnNode, DefaultWeightedEdge> graph;

    public GraphDelegator(Graph graph)
    {
        this.graph = graph;
    }

    public List<SdwnNode> getShortestPath(SdwnNode source, SdwnNode target)
    {
        DijkstraShortestPath dijkstra = new DijkstraShortestPath(graph, source, target);
        List<DefaultWeightedEdge> links = dijkstra.getPathEdgeList();

        /*
            Dijkstra returns a list of all links but
            we need a list of nodes. A LinkedHashSet
            preserve the ordering of element and also
            ignore duplicated nodes. At the end we remove
            the source from set. This is because in case
            reversed direction from source to target
            we'll get a wrong order. We'll add source to
            final list.
        */
        Set<SdwnNode> nodes = new LinkedHashSet<>();

        for (DefaultWeightedEdge link : links) {
            nodes.add(graph.getEdgeSource(link));
            nodes.add(graph.getEdgeTarget(link));
        }
        nodes.remove(source);

        List<SdwnNode> nodesList = new ArrayList<>(nodes);
        nodesList.add(0, source);

        return nodesList;
    }

    public Set<SdwnNode> getNeighbors(SdwnNode node)
    {
        if (!graph.containsVertex(node))
            return null;

        Set<SdwnNode> nodes = new HashSet<>();

        Set<DefaultWeightedEdge> edges = graph.edgesOf(node);

        for (DefaultWeightedEdge edge : edges) {
            nodes.add(graph.getEdgeSource(edge));
            nodes.add(graph.getEdgeTarget(edge));
        }

        //remove node from set. we just need its neighbors
        nodes.remove(node);

        return nodes;
    }

    public boolean isIsland(SdwnNode node)
    {
        return graph.edgesOf(node).isEmpty();
    }
}
