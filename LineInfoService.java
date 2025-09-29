package com.sistek.sos.analysis_dashboard.services;

import com.sistek.sos.analysis_dashboard.entities.LineInfo;
import com.sistek.sos.analysis_dashboard.repositories.LineInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LineInfoService {
    @Autowired
    private LineInfoRepository lineInfoRepository;

    public List<LineInfo> getLatestStatusForAllLines() {
        return lineInfoRepository.findLatestStatusForAllLines();
    }
}

