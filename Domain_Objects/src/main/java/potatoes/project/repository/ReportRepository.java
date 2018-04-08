package potatoes.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import potatoes.project.domain_objects.Report;

public interface ReportRepository extends JpaRepository<Report, Integer>{
	Report findByReportID(int reportID);
	
	@Query(value="select * from report r where r.resolved = true order by r.report_date asc limit 1", nativeQuery = true)
	Report getNextUnresolved();
}
