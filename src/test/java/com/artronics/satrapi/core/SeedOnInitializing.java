package com.artronics.satrapi.core;

import com.artronics.satrapi.helper.TestConstants;
import com.artronics.satrapi.persistence.repositories.SdwnNetworkRepo;
import com.artronics.satrapi.persistence.seed.Seeder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.*;

public class SeedOnInitializing
{
    @InjectMocks
    SatrapiInitializer initializer;

    @Mock
    Seeder seeder;

    @Mock
    SdwnNetworkRepo networkRepo;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(initializer, "ip", TestConstants.OUR_IP);
    }

    @Test
    public void it_should_seed_db_if_seed_is_true()
    {
        ReflectionTestUtils.setField(initializer, "seedDb", true);
        when(networkRepo.findByIp(anyString())).thenReturn(null);
        ContextRefreshedEvent e = mock(ContextRefreshedEvent.class);

        initializer.onApplicationEvent(e);

        verify(seeder,times(1)).seedNetwork(anyString(),anyString());
    }

    @Test
    public void it_should_not_seed_db_if_seed_is_false()
    {
        ReflectionTestUtils.setField(initializer, "seedDb", false);
        when(networkRepo.findByIp(anyString())).thenReturn(null);
        ContextRefreshedEvent e = mock(ContextRefreshedEvent.class);

        initializer.onApplicationEvent(e);

        verify(seeder,times(0)).seedNetwork(anyString(),anyString());
    }
}
