/* ===================
 * Orson Charts - Demo
 * ===================
 * 
 * Copyright (c) 2013, 2014, Object Refinery Limited.
 * All rights reserved.
 *
 * http://www.object-refinery.com/orsoncharts/index.html
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *   - Neither the name of the Object Refinery Limited nor the
 *     names of its contributors may be used to endorse or promote products
 *     derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL OBJECT REFINERY LIMITED BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * Note that the above terms apply to the demo source only, and not the 
 * Orson Charts library.
 * 
 */

package com.orsoncharts.demo;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import com.orsoncharts.ChartPanel3D;
import com.orsoncharts.Chart3D;
import com.orsoncharts.Chart3DFactory;
import com.orsoncharts.axis.ValueAxis3D;
import com.orsoncharts.data.function.Function3D;
import com.orsoncharts.graphics3d.Dimension3D;
import com.orsoncharts.graphics3d.ViewPoint3D;
import com.orsoncharts.graphics3d.swing.DisplayPanel3D;
import com.orsoncharts.plot.XYZPlot;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A demonstration of a scatter plot in 3D.
 */
public class AxisRangeDemo6 extends JFrame {

    static class CustomDemoPanel extends DemoPanel implements ChangeListener {
        
        private JSlider xslider1;
        
        private JSlider xslider2;
        
        private JSlider yslider1;
        
        private JSlider yslider2;

        private JSlider zslider1;
        
        private JSlider zslider2;

        public CustomDemoPanel(LayoutManager layout) {
            super(layout);
            this.xslider1 = new JSlider(0, 50);
            this.xslider1.setValue(0);
            this.xslider2 = new JSlider(50, 100);
            this.xslider2.setValue(100);
            this.xslider1.addChangeListener(this);
            this.xslider2.addChangeListener(this);
            
            this.yslider1 = new JSlider(0, 50);
            this.yslider1.setValue(0);
            this.yslider2 = new JSlider(50, 100);
            this.yslider2.setValue(100);
            this.yslider1.addChangeListener(this);
            this.yslider2.addChangeListener(this);

            this.zslider1 = new JSlider(0, 50);
            this.zslider1.setValue(0);
            this.zslider2 = new JSlider(50, 100);
            this.zslider2.setValue(100);
            this.zslider1.addChangeListener(this);
            this.zslider2.addChangeListener(this);

            JPanel rangePanel = new JPanel(new GridLayout(3, 1));
            JPanel xPanel = new JPanel(new FlowLayout());
            xPanel.add(new JLabel("X axis: "));
            xPanel.add(new JLabel("Lower bound: "));
            xPanel.add(this.xslider1);
            xPanel.add(new JLabel("Upper bound: "));
            xPanel.add(this.xslider2);
            rangePanel.add(xPanel);

            JPanel yPanel = new JPanel(new FlowLayout());
            yPanel.add(new JLabel("Y axis: "));
            yPanel.add(new JLabel("Lower bound: "));
            yPanel.add(this.yslider1);
            yPanel.add(new JLabel("Upper bound: "));
            yPanel.add(this.yslider2);
            rangePanel.add(yPanel);
            
            JPanel zPanel = new JPanel(new FlowLayout());
            zPanel.add(new JLabel("Z axis: "));
            zPanel.add(new JLabel("Lower bound: "));
            zPanel.add(this.zslider1);
            zPanel.add(new JLabel("Upper bound: "));
            zPanel.add(this.zslider2);
            rangePanel.add(zPanel);

            add(rangePanel, BorderLayout.SOUTH);
        }    

        @Override
        public void stateChanged(ChangeEvent e) {
            Chart3D chart = (Chart3D) getChartPanel().getDrawable();
            XYZPlot plot = (XYZPlot) chart.getPlot();
            ValueAxis3D xAxis = plot.getXAxis();
            double xmin = this.xslider1.getValue() / 10.0 - 5.0;
            double xmax = this.xslider2.getValue() / 10.0 - 5.0;
            if (xmin != xmax) {
                xAxis.setRange(xmin, xmax);
            }

            ValueAxis3D yAxis = plot.getYAxis();
            double ymin = this.yslider1.getValue() / 40.0 - 1.0;
            double ymax = this.yslider2.getValue() / 40.0 - 1.0;
            if (ymin != ymax) {
                yAxis.setRange(ymin, ymax);
            }
        
            ValueAxis3D zAxis = plot.getZAxis();
            double zmin = this.zslider1.getValue() / 10.0 - 5.0;
            double zmax = this.zslider2.getValue() / 10.0 - 5.0;
            if (zmin != zmax) {
                zAxis.setRange(zmin, zmax);
            }
        
        }
    }

    /**
     * Creates a new test app.
     *
     * @param title  the frame title.
     */
    public AxisRangeDemo6(String title) {
        super(title);
        addWindowListener(new ExitOnClose());
        getContentPane().add(createDemoPanel());
    }

    /**
     * Returns a panel containing the content for the demo.  This method is
     * used across all the individual demo applications to allow aggregation 
     * into a single "umbrella" demo (OrsonChartsDemo).
     * 
     * @return A panel containing the content for the demo.
     */
    public static JPanel createDemoPanel() {
        DemoPanel content = new CustomDemoPanel(new BorderLayout());
        content.setPreferredSize(OrsonChartsDemo.DEFAULT_CONTENT_SIZE);
        Function3D function = new Function3D() {
            @Override
            public double getValue(double x, double z) {
                return Math.cos(x) * Math.sin(z);
            }
        };

        Chart3D chart = Chart3DFactory.createSurfaceChart("AxisRangeDemo6", 
                "Chart created with Orson Charts", function, "X", "Y", "Z");
        XYZPlot plot = (XYZPlot) chart.getPlot();
        plot.setDimensions(new Dimension3D(10.0, 4.0, 10.0));
        plot.getXAxis().setRange(-5, 5);
        plot.getZAxis().setRange(-5, 5);
        chart.setViewPoint(ViewPoint3D.createAboveLeftViewPoint(40));
        ChartPanel3D chartPanel = new ChartPanel3D(chart);
        content.setChartPanel(chartPanel);
        chartPanel.zoomToFit(OrsonChartsDemo.DEFAULT_CONTENT_SIZE);
        content.add(new DisplayPanel3D(chartPanel));
        return content;
    }
    
    /**
     * Starting point for the app.
     *
     * @param args  command line arguments (ignored).
     */
    public static void main(String[] args) {
        AxisRangeDemo6 app = new AxisRangeDemo6(
                "OrsonCharts : AxisRangeDemo6.java");
        app.pack();
        app.setVisible(true);
    }
}
