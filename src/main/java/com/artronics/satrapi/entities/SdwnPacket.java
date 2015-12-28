package com.artronics.satrapi.entities;

import java.util.List;

import static com.artronics.satrapi.entities.Packet.Direction;
import static com.artronics.satrapi.entities.Packet.Type;

public class SdwnPacket
{
    private Long srcDeviceId;
    private Long srcControllerId;
    private Direction dir;

    private int len;
    private int netId;

    private int srcShortAdd;
    private int dstShortAdd;

    private Type type;

    private int nextHop;

    public static SdwnPacket create(List<Integer> content){
        SdwnPacket packet = new SdwnPacket();

        packet.dir = Direction.RX;

        packet.len = SdwnPacketHelper.getSize(content);
        packet.netId = SdwnPacketHelper.getNetId(content);

        packet.srcShortAdd = SdwnPacketHelper.getSourceAddress(content);
        packet.dstShortAdd = SdwnPacketHelper.getDestinationAddress(content);

        packet.type = SdwnPacketHelper.getType(content);

        packet.nextHop = SdwnPacketHelper.getNextHopAddress(content);

        return packet;
    }
}
