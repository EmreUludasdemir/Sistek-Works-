package com.sistek.sos.analysis_dashboard.repositories;

import com.sistek.sos.analysis_dashboard.entities.PlcInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlcInfoRepository extends JpaRepository<PlcInfo, String> {
    @Query("SELECT p FROM PlcInfo p WHERE p.status = 'ACTIVE' ORDER BY p.lastStatusDate DESC")
    List<PlcInfo> findLatestActivePlcList();
}
