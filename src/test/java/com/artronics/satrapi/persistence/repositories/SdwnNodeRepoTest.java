package com.artronics.satrapi.persistence.repositories;

import com.artronics.satrapi.entities.SdwnNode;
import org.hibernate.LazyInitializationException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class SdwnNodeRepoTest extends RepoBaseTes
{

    @Override
    @Before
    public void setUp() throws Exception
    {
        super.setUp();

    }

    //Since this method is not Transactional any attempt to loading
    //fields with lazy loading will throw exp
    @Test(expected = LazyInitializationException.class)
    public void it_should_LAZY_loaded_from_DeviceConnection(){
        assertThat(persistedDev.getNodes().size(),equalTo(0));
    }

    //TODO Write test for disabling cascade on device and node rel
    @Ignore("I don't know how to test it")
    @Test
    @Transactional
    public void it_should_not_cascade_on_delete_deviceConnection(){
//        List<SdwnNode> nodes = persistedDev.getNodes();
        connectionRepo.deleteAll();

        Iterable<SdwnNode> actNodes=nodeRepo.findAll();
//        SdwnNode aNode = nodeRepo.findOne(nodes.get(0).getId());
        SdwnNode aNode = nodeRepo.findOne(1L);

        assertNotNull(aNode);
    }
}