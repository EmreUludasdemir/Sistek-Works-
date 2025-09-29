package com.sistek.sos.analysis_dashboard.services;

import com.sistek.sos.analysis_dashboard.entities.PlcInfo;
import com.sistek.sos.analysis_dashboard.repositories.PlcInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlcInfoService {
    @Autowired
    private PlcInfoRepository plcInfoRepository;

    public PlcInfo getLatestActivePlc() {
        List<PlcInfo> list = plcInfoRepository.findLatestActivePlcList();
        return list.isEmpty() ? null : list.get(0);
    }

    public List<PlcInfo> getAllPlcInfo() {
        return plcInfoRepository.findAll();
    }
}
