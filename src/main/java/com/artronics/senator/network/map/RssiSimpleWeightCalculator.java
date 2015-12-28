package com.artronics.senator.network.map;

import com.artronics.satrapi.entities.SdwnNode;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class RssiSimpleWeightCalculator implements WeightCalculator
{

    @Override
    public double getWeight(SdwnNode node, SdwnNode neighbor)
    {
//        return (double) (256 - neighbor.getRssi());
        throw new NotImplementedException();
    }
}
