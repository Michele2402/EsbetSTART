package unisa.esbetstart.slipmanagment.infrastructure.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.slipmanagment.application.port.out.GetOddStaticPortOut;
import unisa.esbetstart.slipmanagment.domain.model.OddStatic;
import unisa.esbetstart.slipmanagment.infrastructure.entity.OddStaticEntity;
import unisa.esbetstart.slipmanagment.infrastructure.mapper.InfrastructureOddStaticMapper;
import unisa.esbetstart.slipmanagment.infrastructure.repository.OddStaticJpaRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OddStaticAdapterService implements GetOddStaticPortOut {

    private final OddStaticJpaRepository oddStaticJpaRepository;
    private final InfrastructureOddStaticMapper infrastructureOddStaticMapper;

    /**
     * This method gets an OddStatic by its id
     * @param oddStaticId
     * @return OddStatic
     */
    @Override
    public OddStatic getOddStaticToGamblerById(UUID oddStaticId) {

        Optional<OddStaticEntity> oddStatic = oddStaticJpaRepository.findOddStaticEntityToGambler(oddStaticId);
        return oddStatic.map(infrastructureOddStaticMapper::toOddStaticModelUpToGambler).orElse(null);

    }
}
