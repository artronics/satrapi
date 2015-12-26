package com.artronics.satrapi.persistence.repositories.jpa;

import com.artronics.satrapi.entities.SdwnController;
import com.artronics.satrapi.entities.SdwnNetwork;
import com.artronics.satrapi.persistence.repositories.SdwnNetworkCustomRepo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class SdwnNetworkRepoImpl implements SdwnNetworkCustomRepo
{
    private final static Logger log = Logger.getLogger(SdwnNetworkRepoImpl.class);

    @PersistenceContext
    EntityManager em;

    @Override
    public SdwnNetwork findByIp(String ip)
    {
        Query q = em.createQuery("FROM com.artronics.satrapi.entities.SdwnNetwork n where " +
                                         "n.ip=?1");
        q.setParameter(1,ip);

        SdwnNetwork singleResult = null;
        try {
            singleResult = (SdwnNetwork) q.getSingleResult();
        }catch (NoResultException e) {
            singleResult = null;
        }

        return singleResult;
    }

    @Override
    public SdwnController addSdwnController(Long netId, SdwnController con)
    {
        SdwnNetwork net = em.find(SdwnNetwork.class,netId);
        net.addSdwnController(con);

        con.setSdwnNetwork(net);

        em.persist(net);

        return con;
    }
}
