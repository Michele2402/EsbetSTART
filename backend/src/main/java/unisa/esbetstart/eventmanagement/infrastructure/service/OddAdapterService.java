package unisa.esbetstart.eventmanagement.infrastructure.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import unisa.esbetstart.eventmanagement.application.port.out.GetOddPortOut;
import unisa.esbetstart.eventmanagement.application.port.out.UpdateOddPortOut;
import unisa.esbetstart.eventmanagement.domain.model.Odd;
import unisa.esbetstart.eventmanagement.infrastructure.entity.OddEntity;
import unisa.esbetstart.eventmanagement.infrastructure.mapper.InfrastructureOddMapper;
import unisa.esbetstart.eventmanagement.infrastructure.repository.OddJpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OddAdapterService implements GetOddPortOut, UpdateOddPortOut {

    private final InfrastructureOddMapper infrastructureOddMapper;
    private final OddJpaRepository oddJpaRepository;

    @Override
    public Odd getOddByIdWithSimpleDetails(UUID oddId) {

        Optional<OddEntity> oddEntity = oddJpaRepository.findByIdWithEvent(oddId);
        return oddEntity.map(infrastructureOddMapper::toOddModelWithSimpleDetails).orElse(null);

    }

    @Override
    public void updateOdd(Odd odd) {

        oddJpaRepository.save(infrastructureOddMapper.toOddEntity(odd));

    }
}
