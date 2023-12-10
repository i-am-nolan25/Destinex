package com.team08.csci205_final_project.service;

import com.team08.csci205_final_project.model.Auth.Role;
import com.team08.csci205_final_project.model.DTO.Provider.ProviderRegister;
import com.team08.csci205_final_project.model.Provider.Provider;
import com.team08.csci205_final_project.model.User.User;
import com.team08.csci205_final_project.repository.ProviderRepository;
import com.team08.csci205_final_project.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class ProviderServiceTest {

    @Mock
    private ProviderRepository providerRepository;

    @Mock
    private MongoTemplate mongoTemplate;

    @Mock
    private JobService jobService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private ProviderService providerService;

    @Test
    public void testProviderRegister() {
        ProviderRegister providerRegister = new ProviderRegister();
        // set properties for providerRegister

        String currentUserId = "userId";
        User user = new User();
        user.setId(currentUserId);
        user.setRole(Role.ROLE_USER);

        when(userService.getCurrentUserId()).thenReturn(currentUserId);
        when(userRepository.existsById(currentUserId)).thenReturn(true);
        when(userRepository.findById(currentUserId)).thenReturn(Optional.of(user));

        Provider provider = new Provider();
        when(providerRepository.save(any(Provider.class))).thenReturn(provider);

        Provider result = providerService.providerRegister(providerRegister);

        assertNotNull(result);
        verify(providerRepository).save(any(Provider.class));
    }

    @Test
    public void testFindProviderById() {
        String providerId = "providerId";
        Optional<Provider> mockProvider = Optional.of(new Provider());
        when(providerRepository.findById(providerId)).thenReturn(mockProvider);

        Optional<Provider> result = providerService.findProviderById(providerId);

        assertTrue(result.isPresent());
        assertEquals(mockProvider.get(), result.get());
        verify(providerRepository).findById(providerId);
    }


    @Test
    public void testUpdateProvider() {
        Provider providerRegister = new Provider();
        providerRegister.setProviderId("providerId");

        Provider provider = new Provider();
        when(providerRepository.findById(providerRegister.getProviderId())).thenReturn(Optional.of(provider));
        when(providerRepository.save(any(Provider.class))).thenReturn(provider);

        Provider result = providerService.updateProvider(providerRegister);

        assertNotNull(result);
        verify(providerRepository).save(any(Provider.class));
    }

    @Test
    public void testDeleteProvider() {
        String userId = "userId";
        when(providerRepository.existsById(userId)).thenReturn(true);

        providerService.deleteProvider(userId);

        verify(providerRepository).deleteById(userId);
    }

}
