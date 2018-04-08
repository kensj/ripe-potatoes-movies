/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.project.domain_objects;

import java.util.concurrent.LinkedBlockingQueue;

import javax.persistence.Entity;

/**
 *
 * @author kdill
 */

public class ReportQueue {
    private static LinkedBlockingQueue<Report> reportQueue;
    
    public static int queueReport(Report r){
        reportQueue.add(r);
        return reportQueue.size();
    }
    
    public static Report dequeueReport(){
        return reportQueue.remove();
    }
}
