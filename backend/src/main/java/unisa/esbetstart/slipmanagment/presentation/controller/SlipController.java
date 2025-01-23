package unisa.esbetstart.slipmanagment.presentation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/slip")
@CrossOrigin("*")
public class SlipController {

    @PostMapping("/save")
    public void saveSlip() {

        log.info("Slip saved");
    }
}
