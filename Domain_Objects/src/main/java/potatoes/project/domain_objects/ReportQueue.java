/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.project.domain_objects;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.Queue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author kdill
 */

@Entity
public class ReportQueue {
	@OneToMany
    private static Queue<Report> reportQueue;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int rqID;
	
	public ReportQueue() {
		reportQueue = new LinkedBlockingQueue<Report>();
	}
    
    public static int queueReport(Report r){
        reportQueue.add(r);
        return reportQueue.size();
    }
    
    public static Report dequeueReport(){
        return reportQueue.remove();
    }
}
