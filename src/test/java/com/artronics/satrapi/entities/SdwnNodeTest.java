package com.artronics.satrapi.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SdwnNodeTest
{
    /*
        Test for Hashcode and Equal
     */
    SdwnNode node = new SdwnNode();
    SdwnNode anotherNode = new SdwnNode();

    @Before
    public void setUp() throws Exception
    {
    }

    @Test
    public void it_should_be_equal_if_address_and_deviceId_are_equal(){
        node.setAddress(1L);
        anotherNode.setAddress(1L);
        node.setDeviceId(2L);
        anotherNode.setDeviceId(2L);

        assertTrue(node.equals(anotherNode));
    }

}