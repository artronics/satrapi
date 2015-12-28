package com.artronics.satrapi.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
        setFields(1L,1L,2L,2L);

        assertTrue(node.equals(anotherNode));
        //reflective
        assertTrue(anotherNode.equals(node));
    }

    @Test
    public void equal_should_false_if_deviceId_are_not_equal(){
        setFields(1L,1L,2L,34L);

        assertFalse(node.equals(anotherNode));
    }

    @Test
    public void equal_should_false_if_address_are_not_equal(){
        setFields(231L,1L,2L,2L);

        assertFalse(node.equals(anotherNode));
    }

    @Test
    public void if_equal_hashCode_must_be_the_same(){
        setFields(1L,1L,2L,2L);

        assertEquals(node.hashCode(),anotherNode.hashCode());
    }

    private void setFields(Long add1,Long add2,Long devId1,Long devId2){
        node.setAddress(add1);
        anotherNode.setAddress(add2);
        node.setDeviceId(devId1);
        anotherNode.setDeviceId(devId2);
    }

}