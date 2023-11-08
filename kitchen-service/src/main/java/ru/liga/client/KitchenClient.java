package ru.liga.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.liga.dto.OrderActionDto;

@FeignClient(name = "delivery-service", url = "http://localhost:8050")
public interface KitchenClient {

    @PostMapping(value = "/delivery")
    void updateOrderStatus(@RequestBody OrderActionDto dto);
}
