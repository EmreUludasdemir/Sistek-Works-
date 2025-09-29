package com.sistek.sos.analysis_dashboard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class DbDiagnostics implements CommandLineRunner {
  private final DataSource dataSource;

  @Override
  public void run(String... args) throws Exception {
    try (Connection c = dataSource.getConnection();
         Statement s = c.createStatement()) {
      try (ResultSet rs = s.executeQuery(
          "select current_database() db, current_schema() schema, current_user usr")) {
        if (rs.next()) {
          log.info("DB: {}, schema: {}, user: {}", rs.getString("db"), rs.getString("schema"), rs.getString("usr"));
        }
      }
      try (ResultSet rs = s.executeQuery(
          "select table_schema, table_name from information_schema.tables " +
          "where table_name in ('barcodes','lines') order by 1,2")) {
        StringBuilder sb = new StringBuilder("Existing target tables:\n");
        boolean any = false;
        while (rs.next()) {
          any = true;
          sb.append("- ").append(rs.getString(1)).append(".").append(rs.getString(2)).append("\n");
        }
        if (!any) sb.append("- (none)\n");
        log.info(sb.toString());
      }
    }
  }
}

