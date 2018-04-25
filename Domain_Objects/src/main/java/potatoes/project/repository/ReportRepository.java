package potatoes.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import potatoes.project.domain_objects.Report;
import potatoes.project.domain_objects.User;

public interface ReportRepository extends JpaRepository<Report, Integer>{
	Report findByReportID(int reportID);
	
	List<Report> findByReporter(User reporter);
	
	@Query(value="select * from report r where r.resolved = false order by r.report_date asc limit 5", nativeQuery = true)
	Report getNext5Unresolved();
}
