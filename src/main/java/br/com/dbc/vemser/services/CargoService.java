package br.com.dbc.vemser.services;

import br.com.dbc.vemser.model.entities.Cargo;
import br.com.dbc.vemser.repository.CargoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CargoService {

    private final CargoRepository cargoRepository;

    public Cargo getCargo(String nomeCargo) {
        return cargoRepository.findByNome(nomeCargo);
    }
}