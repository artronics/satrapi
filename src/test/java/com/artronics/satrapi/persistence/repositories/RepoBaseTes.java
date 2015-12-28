package com.artronics.satrapi.persistence.repositories;

import com.artronics.satrapi.entities.DeviceConnection;
import com.artronics.satrapi.entities.SdwnController;
import com.artronics.satrapi.entities.SdwnNetwork;
import com.artronics.satrapi.entities.SdwnNode;
import com.artronics.satrapi.helper.CreateEntities;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.artronics.satrapi.helper.CreateEntities.createCtrl;
import static com.artronics.satrapi.helper.CreateEntities.createNet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        RepositoryConfigTest.class,
//        SatrapiApplication.class
})
@ActiveProfiles("test")
public class RepoBaseTes
{
    private final static Logger log = Logger.getLogger(RepoBaseTes.class);

    @Autowired
    protected SdwnNetworkRepo networkRepo;

    @Autowired
    protected SdwnControllerRepo controllerRepo;

    @Autowired
    protected DeviceConnectionRepo connectionRepo;

    @Autowired
    protected PacketRepo packetRepo;

    @Autowired
    protected SdwnNodeRepo nodeRepo;

    //net that is persisted with someIp
    protected String someIp = "12.34.55.234";
    protected SdwnNetwork persistedNet;
    protected Long netId;

    //first controller which is associated with persistedNet
    protected SdwnController persistedCtrl;
    protected Long ctrlId;

    //it is associated with persistedCtrl
    protected DeviceConnection persistedDev;
    protected Long devId;

    protected int numOfCtrl = 5;
    protected int numOfNodes = 7;

    /**
     * Before each test we persist a SdwnNetwork with ip address of someIp, netId is the Id of this
     * network. For this entity we persist numOfCtrl. persistedCtrl is the first SdwnController
     * (index 0)
     * <p/>
     * @throws Exception
     */
    @Before
    @Transactional
    public void setUp() throws Exception
    {
        //creates one SdwnNetwork with 5 controllers
        SdwnNetwork net = persisControllers(5);
        netId = net.getId();
        persistedNet = networkRepo.findOne(netId);

        ctrlId = persistedNet.getControllers().get(0).getId();
        persistedCtrl = controllerRepo.findOne(ctrlId);
        DeviceConnection device = new DeviceConnection("foo string");
        device.setSdwnController(persistedCtrl);
        persistSdwnNodes(device,numOfNodes);

        persistedDev = connectionRepo.findOne(device.getId());
        devId = persistedDev.getId();
    }

    //    @Ignore("This is a test in RepoBaseTest which should be run for debugging base class")
    @Test
    public void run_this_test_for_debugging_this_base_class()
    {

    }

    @After
    public void after() throws Exception
    {
        networkRepo.deleteAll();
    }


    private SdwnNetwork persisControllers(int num)
    {
        return persisControllers(num, someIp);
    }

    protected SdwnNetwork persisControllers(int num, String ip)
    {
        SdwnNetwork network = createNet(ip);

        List<SdwnController> controllers = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            SdwnController e = createCtrl();
            e.setSdwnNetwork(network);
            e.setSinkAddress(4L);
            controllers.add(e);
        }
        network.setControllers(controllers);
        networkRepo.save(network);

        return network;
    }

    protected SdwnNetwork persistANet(String ip)
    {
        SdwnNetwork net = createNet(ip);

        return networkRepo.save(net);
    }
    
    protected DeviceConnection persistSdwnNodes(DeviceConnection device,int num){
        List<SdwnNode> nodes = CreateEntities.createNodes(num);
        for (SdwnNode node:nodes){
            node.setDevice(device);
        }
        device.setNodes(nodes);
        return connectionRepo.save(device);
    }
}
