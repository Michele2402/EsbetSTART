package unisa.esbetstart.slipmanagment.infrastructure.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.slipmanagment.application.port.out.GetSlipPortOut;
import unisa.esbetstart.slipmanagment.application.port.out.PlaceBetPortOut;
import unisa.esbetstart.slipmanagment.application.port.out.UpdateSlipPortOut;
import unisa.esbetstart.slipmanagment.domain.model.BetPlaced;
import unisa.esbetstart.slipmanagment.domain.model.Slip;
import unisa.esbetstart.slipmanagment.infrastructure.entity.SlipEntity;
import unisa.esbetstart.slipmanagment.infrastructure.mapper.InfrastructureSlipMapper;
import unisa.esbetstart.slipmanagment.infrastructure.repository.SlipJpaRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SlipAdapterService implements GetSlipPortOut, UpdateSlipPortOut {

    private final SlipJpaRepository slipJpaRepository;
    private final InfrastructureSlipMapper infrastructureSlipMapper;

    @Override
    public Slip getSlipById(UUID slipId) {

        Optional<SlipEntity> slipEntity = slipJpaRepository.findSlipCompleteById(slipId);
        return slipEntity.map(infrastructureSlipMapper::toSlipModelComplete).orElse(null);

    }

    @Override
    public Slip getSlipCompleteById(UUID slipId) {

        Optional<SlipEntity> slipEntity = slipJpaRepository.findSlipCompleteById(slipId);
        return slipEntity.map(infrastructureSlipMapper::toSlipModelComplete).orElse(null);

    }

    @Override
    public void updateSlip(Slip slip) {

        slipJpaRepository.save(infrastructureSlipMapper.toSlipEntity(slip));

    }
}
