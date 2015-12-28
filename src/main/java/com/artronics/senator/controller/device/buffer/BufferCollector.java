package com.artronics.senator.controller.device.buffer;

import java.util.List;

public interface BufferCollector
{
    List<List<Integer>> generateLists(List<Integer> receivedData);
}
