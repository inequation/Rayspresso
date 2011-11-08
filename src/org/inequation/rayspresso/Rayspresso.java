package org.inequation.rayspresso;

import org.inequation.rayspresso.renderer.*;

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
        if (args.length < 2) {
            System.out.println("No arguments specified, exiting.");
            return;
        }
        int w = 0, h = 0;
        try {
            w = Integer.parseInt(args[0]);
            h = Integer.parseInt(args[1]);
        } catch (NumberFormatException ex) {
            System.err.println("Invalid number format, please check your arguments.");
            return;
        } finally {
            w = Math.max(0, w);
            h = Math.max(0, h);
        }
        RenderType rt = RenderType.SHADED;
        if (args.length > 2) {
            try {
                rt = RenderType.parseRenderType(args[2]);
            } catch (RenderTypeFormatException ex) {
                System.err.printf("Invalid render type format %s, please check your arguments.\n", args[2]);
                return;
            }
        }
        System.out.println("Will render " + rt + " at " + w + "x" + h + " resolution.");
    }
}
