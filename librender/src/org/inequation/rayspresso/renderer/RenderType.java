package org.inequation.rayspresso.renderer;

/**
 * Render type enumeration - used to select between a regular, shaded render
 * and special types.
 * @author inequation
 * @version 08.11.2011
 */
public enum RenderType {
    SHADED,
    NORMALS,
    DEPTH;
    
    public static RenderType parseRenderType(String s)
        throws RenderTypeFormatException {
        for (RenderType r : RenderType.values()) {
            if (r.toString().equals(s))
                return r;
        }
        throw new RenderTypeFormatException();
    }
}
