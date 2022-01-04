package ua.yarynych.demoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.yarynych.demoapi.model.Worker;
import ua.yarynych.demoapi.repository.WorkerRepository;

@Service
public class WorkerService {

    @Autowired
    WorkerRepository workerRepository;

    public String findWorker() {
        Iterable<Worker> workers = workerRepository.findAll();
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        for(Worker worker: workers) {
            if(count < 1) {
                stringBuilder.append(worker.getName());
                count++;
            }
        }
        return new String(stringBuilder);
    }
}
