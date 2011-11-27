package org.inequation.rayspresso;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.inequation.rayspresso.renderer.*;
import org.jdesktop.application.SingleFrameApplication;

/**
 * Main application class.
 * @author inequation
 * @version 24.11.2011
 */
public class Rayspresso extends SingleFrameApplication {
    @Override
    protected void startup() {
        BlinnPhongShader yellow = new BlinnPhongShader(
                new Colour(1.f, 0.5f, 0.f),
                new Colour(0.3f, 0.3f, 0.3f, 2.f));
        Plane floor = new Plane(new Vec3(0, 0, 1), -3.f, yellow);
	Plane wallL = new Plane(new Vec3(0, 1, 0), -10.f, yellow);
	Plane wallR = new Plane(new Vec3(0, -1, 0), -10.f, yellow);
	Plane wallB = new Plane(new Vec3(-1, 0, 0), -10.f, yellow/*mirror*/);
	Plane wallF = new Plane(new Vec3(1, 0, 0), -10.f, yellow);
	Plane ceil = new Plane(new Vec3(0, 0, -1), -10.f, yellow);
        
        Scene.getInstance().getTraceables().add(floor);
        Scene.getInstance().getTraceables().add(wallL);
        Scene.getInstance().getTraceables().add(wallR);
        Scene.getInstance().getTraceables().add(wallB);
        Scene.getInstance().getTraceables().add(wallF);
        Scene.getInstance().getTraceables().add(ceil);
        
        PointLight p1 = new PointLight(new Vec3(-2f, 3f, 2f), new Colour(1.f, 1.f, 1.f), 400.f);
	PointLight p2 = new PointLight(new Vec3(-3.f, -2.f, -2.5f), new Colour(0.8f, 1.f, 1.f), 100.f);
        
        Scene.getInstance().getLights().add(p1);
        Scene.getInstance().getLights().add(p2);
        
        show(new MainFrameView(this));
    }
    
    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(Rayspresso.class, args);
    }
    
    /**
     * This function fires off rendering.
     * @param zNear near plane Z
     * @param zFar  far plane Z
     * @param rt    render type
     * @param bi    image to render to
     */
    public static void render(float zNear, float zFar, RenderType rt, BufferedImage bi) {
        Material override = null;
        if (rt == RenderType.NORMALS)
            override = new DebugNormalsMaterial();
        else if (rt == RenderType.DEPTH)
            override = new DebugDepthMaterial();
        
        Camera cam = new Camera(new Vec3(-2.5f, 0.8f, 1.5f),
                new Vec3(1.f, 0.f, 0.f),
                new Vec3(0.f, 1.f, 0.f),
                new Vec3(0.f, 0.f, 1.f),
                90.f / 180.f * (float)Math.PI, zNear, zFar);
        
        cam.render(bi, override);
    }
    
    public static void confirmQuit() {
        if (JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?",
            "Rayspresso - Question", JOptionPane.YES_NO_OPTION) == 0)
            System.exit(0);
    }
    
    /**
     * Main method.
     * @param args the command line arguments
     */
    @Deprecated
    public static void mainOld(String[] args) {
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
        
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        /*MainFrame f = new MainFrame(bi);
        f.setVisible(true);
        f.updateFrameDimensions(new Dimension(w, h));*/
        
        Material override = null;
        if (rt == RenderType.NORMALS)
            override = new DebugNormalsMaterial();
        else if (rt == RenderType.DEPTH)
            override = new DebugDepthMaterial();
        
        BlinnPhongShader yellow = new BlinnPhongShader(
                new Colour(1.f, 0.5f, 0.f),
                new Colour(0.3f, 0.3f, 0.3f, 2.f));
        Plane floor = new Plane(new Vec3(0, 0, 1), -3.f, yellow);
	Plane wallL = new Plane(new Vec3(0, 1, 0), -10.f, yellow);
	Plane wallR = new Plane(new Vec3(0, -1, 0), -10.f, yellow);
	Plane wallB = new Plane(new Vec3(-1, 0, 0), -10.f, yellow/*mirror*/);
	Plane wallF = new Plane(new Vec3(1, 0, 0), -10.f, yellow);
	Plane ceil = new Plane(new Vec3(0, 0, -1), -10.f, yellow);
        
        Scene.getInstance().getTraceables().add(floor);
        Scene.getInstance().getTraceables().add(wallL);
        Scene.getInstance().getTraceables().add(wallR);
        Scene.getInstance().getTraceables().add(wallB);
        Scene.getInstance().getTraceables().add(wallF);
        Scene.getInstance().getTraceables().add(ceil);
        
        PointLight p1 = new PointLight(new Vec3(-2f, 3f, 2f), new Colour(1.f, 1.f, 1.f), 400.f);
	PointLight p2 = new PointLight(new Vec3(-3.f, -2.f, -2.5f), new Colour(0.8f, 1.f, 1.f), 100.f);
        
        Scene.getInstance().getLights().add(p1);
        Scene.getInstance().getLights().add(p2);
        
        Camera cam = new Camera(new Vec3(-2.5f, 0.8f, 1.5f),
                new Vec3(1.f, 0.f, 0.f),
                new Vec3(0.f, 1.f, 0.f),
                new Vec3(0.f, 0.f, 1.f));
        
        cam.render(bi, override);
        
        try {            
            File outputfile = new File("saved.png");
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException e) {
            System.err.printf("Failed to write scene image!");
        }
        
        /*while (f.isVisible())
        {}*/
        
        System.out.printf("Done!\n");
        System.exit(0);
    }
}
