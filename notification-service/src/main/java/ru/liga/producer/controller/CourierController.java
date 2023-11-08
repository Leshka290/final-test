package ru.liga.producer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.dto.enums.OrderAction;
import ru.liga.producer.service.CourierService;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/courier")
@RestController
public class CourierController {
    private final CourierService courierService;

    @PostMapping("/send")
    public void sendMessageToRabbit(@RequestBody OrderAction orderAction) {
        courierService.sendOrderStatus(orderAction);
    }
}
