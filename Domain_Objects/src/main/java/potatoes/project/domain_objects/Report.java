/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.project.domain_objects;

/**
 *
 * @author kdill
 */
public class Report {
    
    private String title;
    private String description;
    private User reporter;
    private User reported;
    private Review context;
    private int id;
    
    public Report(String title,String description, User reporter, Review context, int id){
        this.title=title;
        this.description=description;
        this.reporter=reporter;
        this.reported=context.getAuthor();
        this.id=id;
    }
}
