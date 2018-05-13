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
public enum Rank {
    LEVEL0(0),
    LEVEL1(1),
    LEVEL2(5),
    LEVEL3(10),
    LEVEL4(25),
    LEVEL5(50),
    LEVEL6(100),
    LEVEL7(500),
    LEVEL8(1000)
    ;
    
    private final int threshold;
    Rank(int threshold){this.threshold=threshold;}
    
    public int getValue() {
        return this.threshold;
    }
}
