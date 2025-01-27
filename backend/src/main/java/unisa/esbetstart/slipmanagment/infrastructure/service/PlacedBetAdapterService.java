package unisa.esbetstart.slipmanagment.infrastructure.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.slipmanagment.application.port.out.GetBetPlacedPortOut;
import unisa.esbetstart.slipmanagment.application.port.out.PlaceBetPortOut;
import unisa.esbetstart.slipmanagment.application.port.out.UpdateBetPlacedPortOut;
import unisa.esbetstart.slipmanagment.domain.model.BetPlaced;
import unisa.esbetstart.slipmanagment.infrastructure.entity.BetPlacedEntity;
import unisa.esbetstart.slipmanagment.infrastructure.mapper.InfrastructureBetPlacedMapper;
import unisa.esbetstart.slipmanagment.infrastructure.repository.BetPlacedJpaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlacedBetAdapterService implements PlaceBetPortOut, UpdateBetPlacedPortOut {

    private final BetPlacedJpaRepository betPlacedJpaRepository;
    private final InfrastructureBetPlacedMapper infrastructureBetPlacedMapper;

    @Override
    public void placeBet(BetPlaced betPlaced) {

        betPlacedJpaRepository.save(infrastructureBetPlacedMapper.toBetPlacedEntity(betPlaced));

    }

    @Override
    public void updateBetPlaced(BetPlaced betPlaced) {

            betPlacedJpaRepository.save(infrastructureBetPlacedMapper.toBetPlacedEntityToGambler(betPlaced));

    }
}
