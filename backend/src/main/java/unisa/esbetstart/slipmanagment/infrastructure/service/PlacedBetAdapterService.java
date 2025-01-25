package unisa.esbetstart.slipmanagment.infrastructure.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.slipmanagment.application.port.out.PlaceBetPortOut;
import unisa.esbetstart.slipmanagment.domain.model.BetPlaced;
import unisa.esbetstart.slipmanagment.infrastructure.entity.BetPlacedEntity;
import unisa.esbetstart.slipmanagment.infrastructure.mapper.InfrastructureBetPlacedMapper;
import unisa.esbetstart.slipmanagment.infrastructure.repository.BetPlacedJpaRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlacedBetAdapterService implements PlaceBetPortOut {

    private final BetPlacedJpaRepository betPlacedJpaRepository;
    private final InfrastructureBetPlacedMapper infrastructureBetPlacedMapper;

    @Override
    public void placeBet(BetPlaced betPlaced) {

        betPlacedJpaRepository.save(infrastructureBetPlacedMapper.toBetPlacedEntity(betPlaced));


    }

}
