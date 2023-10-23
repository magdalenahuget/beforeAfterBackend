package com.mpm.beforeandafter.status.service;

import com.mpm.beforeandafter.status.model.Status;
import com.mpm.beforeandafter.status.repository.StatusDAO;
import com.mpm.beforeandafter.status.type.StatusesType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService {

    private final StatusDAO statusDAO;

    @Autowired
    public StatusService(StatusDAO statusDAO) {
        this.statusDAO = statusDAO;
    }

    public void createStatuses(){
        for (StatusesType statusType : StatusesType.values()){
            Status status = new Status(statusType.getStatusName());
            statusDAO.save(status);
        }
    }
}
