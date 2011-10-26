package org.inequation.rayspresso;

/**
 * Main class.
 * @author inequation
 * @version 29.10.2011
 */
public class Rayspresso {

    /**
     * Main method.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Vec3 v1 = new Vec3();
        Vec3 v2 = new Vec3(1, 2, 3);
        v1.addAssign(v2);
        v1.multAssign(3.f);
        System.out.println(v1.dot(v2));
        System.out.println(v1.getX());
        System.out.println(v1.getY());
        System.out.println(v1.getZ());
    }
}
