package com.artronics.senator.network.map;

import com.artronics.satrapi.entities.SdwnNode;

public interface WeightCalculator
{
    double getWeight(SdwnNode node, SdwnNode neighbor);
}
