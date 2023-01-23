package com.example.project.service;

import com.example.project.model.Studio;
import com.example.project.repository.StudioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudioServiceTests {

    @InjectMocks
    private StudioService studioService;

    @Mock
    private StudioRepository studioRepository;
    @Mock
    private MovieService movieService;

    @Test
    void getAllStudios() {
        Studio studio = new Studio("StudioTest", "LocationTest");

        when(studioRepository.findAll()).thenReturn(List.of(studio));
        List<Studio> result = studioService.getAllStudios();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(studio, result.get(0));

        verify(studioRepository).findAll();
    }
}
