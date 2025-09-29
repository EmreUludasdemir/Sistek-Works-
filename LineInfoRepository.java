package com.sistek.sos.analysis_dashboard.repositories;

import com.sistek.sos.analysis_dashboard.entities.LineInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LineInfoRepository extends JpaRepository<LineInfo, String> {
    @Query("SELECT l FROM LineInfo l WHERE l.lastStatusDate = (SELECT MAX(li.lastStatusDate) FROM LineInfo li WHERE li.lineId = l.lineId) ORDER BY l.lineId ASC")
    List<LineInfo> findLatestStatusForAllLines();
}
