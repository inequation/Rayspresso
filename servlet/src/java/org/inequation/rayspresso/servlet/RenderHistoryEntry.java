/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inequation.rayspresso.servlet;

import java.sql.Timestamp;
import org.inequation.rayspresso.renderer.*;

/**
 * RenderHistory bean.
 * @author inequation
 * @version 21.01.2012
 */
public class RenderHistoryEntry {
    public RenderHistoryEntry(Timestamp timestamp, RenderType rendermode, float zNear,
        float zFar, int width, int height) {
        m_timestamp = timestamp;
        m_rendermode = rendermode;
        m_zNear = zNear;
        m_zFar = zFar;
        m_width = width;
        m_height = height;
    }
    
    public Timestamp getTimestamp() { return m_timestamp; }
    public RenderType getRenderMode() { return m_rendermode; }
    public float getZNear() { return m_zNear; }
    public float getZFar() { return m_zFar; }
    public int getWidth() { return m_width; }
    public int getHeight() { return m_height; }
    
    private Timestamp m_timestamp;
    private RenderType m_rendermode;
    private float m_zNear;
    private float m_zFar;
    private int m_width;
    private int m_height;
}
