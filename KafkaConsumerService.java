package com.ctb.campservice.config;

import com.ctb.campservice.entities.CampEntity;
import com.ctb.campservice.services.CampService;
import com.ctb.campservice.utils.FindNearestCamp;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service @AllArgsConstructor
public class KafkaConsumerService {

    private CampService campService;
    private FindNearestCamp findNearestCamp;
    @KafkaListener(topics = "createAccidentTopic", containerFactory = "kafkaListenerContainerFactory")
    public void consume(HashMap<String,Object> accident) {
        List<CampEntity> camps= campService.gettAllCamp();
        CampEntity camp= findNearestCamp.getNearestCamp((Double) accident.get("latitude"), (Double) accident.get("longitude"),camps);
        System.out.println(camp.toString());
    }
}
