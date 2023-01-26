package com.mscourse.hrpayroll.services;

import com.mscourse.hrpayroll.entities.Payment;
import com.mscourse.hrpayroll.entities.Worker;
import com.mscourse.hrpayroll.feignclients.WorkerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {
    //pegar o valor do aplication properties
//    @Value("${hr-worker.host}")
//    private String workerHost;

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private WorkerFeignClient feignClient;

    public Payment getPayment(long workerId, int days){
//        Map<String, String> uriVariables = new HashMap<>();
//        uriVariables.put("id", "" + workerId);

        //Worker worker = restTemplate.getForObject(workerHost + "/workers/{id}", Worker.class, uriVariables);

        Worker worker = feignClient.findById(workerId).getBody();
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
