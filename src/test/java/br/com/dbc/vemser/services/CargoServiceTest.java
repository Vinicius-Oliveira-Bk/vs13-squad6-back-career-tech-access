package br.com.dbc.vemser.services;

import br.com.dbc.vemser.model.entities.Cargo;
import br.com.dbc.vemser.repository.CargoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CargoServiceTest {

    @Mock
    private CargoRepository cargoRepository;
    @InjectMocks
    private CargoService cargoService;

    Cargo cargo;

    @BeforeEach
    void beforeEach() {
        cargo = new Cargo(1, "ROLE_USUARIO", null);
    }

    @Test
    @DisplayName("Deve buscar o cargo por seu nome completo com sucesso.")
    public void deveBuscarOCargoPorNomeComSucesso() {
        when(cargoRepository.findByNome(anyString())).thenReturn(cargo);

        Cargo cargoMock = cargoService.getCargo(anyString());

        assertNotNull(cargoMock);
        assertEquals(cargo, cargoMock);
    }
}