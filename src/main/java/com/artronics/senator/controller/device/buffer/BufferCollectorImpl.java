package com.artronics.senator.controller.device.buffer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class BufferCollectorImpl implements BufferCollector
{
    private final static Logger log = Logger.getLogger(BufferCollectorImpl.class);

    private Integer startByte;
    private Integer stopByte;

    private final LinkedList<Integer> dataQueue = new LinkedList<>();
    private final ArrayList<Integer> thisPacket = new ArrayList<>();
    private final List<List<Integer>> generatedPcks = new ArrayList<>();
    private int thisPacketExpectedSize = 0;
    private boolean isStarted = false;

    @Autowired
    Environment env;

    public BufferCollectorImpl()
    {
    }

    @PostConstruct
    public void initBean()
    {
        startByte = Integer.parseInt(env.getProperty(
                "com.artronics.senator.controller.device.buffer.startByte"));
        stopByte = Integer.parseInt(env.getProperty(
                "com.artronics.senator.controller.device.buffer.stopByte"));
    }

    @Override
    public List<List<Integer>> generateLists(List<Integer> receivedData)
    {
        return createRawLists(receivedData);
    }

    protected List<List<Integer>> createRawLists(List receivedData)
    {
        dataQueue.addAll(receivedData);
        while (!dataQueue.isEmpty()) {
            int thisData = dataQueue.removeFirst();

            if (thisData == startByte
                    && !isStarted
                    && thisPacketExpectedSize == 0) {
                isStarted = Boolean.TRUE;

            }else if (isStarted) {
                if (thisPacketExpectedSize == 0) {
                    thisPacket.add(thisData);
                    thisPacketExpectedSize = thisData;

                }else if (thisPacket.size() < thisPacketExpectedSize) {
                    thisPacket.add(thisData);

                }else if (thisData == stopByte
                        && thisPacket.size() == thisPacketExpectedSize) {
                    ArrayList<Integer> newPacket = new ArrayList<>(thisPacket);
                    generatedPcks.add(newPacket);
                    thisPacket.clear();
                    isStarted = Boolean.FALSE;
                    thisPacketExpectedSize = 0;
                }
            }
        }

        List<List<Integer>> tmp = new ArrayList<>(generatedPcks);
        generatedPcks.clear();

        return tmp;
    }

    public void setStartByte(int startByte)
    {
        this.startByte = startByte;
    }

    public void setStopByte(int stopByte)
    {
        this.stopByte = stopByte;
    }
}
