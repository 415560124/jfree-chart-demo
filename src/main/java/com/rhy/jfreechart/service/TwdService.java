package com.rhy.jfreechart.service;

import com.rhy.jfreechart.bean.ChartBean;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rhy
 * @title 体温单服务类
 * @description 体温单服务类
 * @createTime 2020年09月29日 14:14:00
 * @modifier：Rhy
 * @modification_time：2020-09-29 14:14
 */
public class TwdService {
    private List<ChartBean> chartBeanList = new ArrayList<ChartBean>();
    /**
     * 绘制体温单
     */
    public void run() throws IOException {
        //这个就可以理解为线的List集合
        XYSeriesCollection dataset = new XYSeriesCollection();
        //画X轴线
        drawingXLine(dataset);
        //画Y轴线
        drawingYLine(dataset);
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                "",
                "",
                "",
                dataset,
                PlotOrientation.VERTICAL,
                false, false, false);
        //获得轴对象
        XYPlot xYPlot = (XYPlot) xylineChart.getPlot();
        xYPlot.setDataset(0, dataset);

        //线条涂色
        paintingLine(xYPlot);

//        xYPlot.getRenderer().setSeriesPaint(0, new Color(0, 0, 0));
        //设置画板 以这个为框体开始画
        setDrawingBoard(xylineChart);


        //保存的图片位置
        File XYChart = new File(System.getProperty("user.dir") + "\\img\\self.jpeg");
        //就是一个工具类  固定用法  将图片保存到本地
        //图片的宽度
        int width = 960; /* Width of the image */
        //图片的高度
        int height = 1280; /* Height of the image */
        ChartUtilities.saveChartAsJPEG(XYChart, xylineChart, width, height);
    }
    /**
     * 给线条涂色
     */
    private void paintingLine(XYPlot xYPlot) {
        xYPlot.getRangeAxis().setAxisLineVisible(false);
        xYPlot.getDomainAxis().setAxisLineVisible(false);

        //获得渲染器这个意思
        XYLineAndShapeRenderer xyLineAndShapeRenderer = (XYLineAndShapeRenderer) xYPlot.getRenderer();
        for(int i=0;i<119;i++){
            xyLineAndShapeRenderer.setSeriesPaint(i, Color.BLACK);
        }

    }

    /**
     * 设置画板
     *
     * @param xylineChart
     */
    private void setDrawingBoard(JFreeChart xylineChart) {
        // 设置背景色
        GradientPaint bg = new GradientPaint(0, 1000, Color.white, 0, 800, Color.white);
        //获得轴对象
        XYPlot xYPlot = (XYPlot) xylineChart.getPlot();
        //设置背景色
        xYPlot.setBackgroundPaint(bg);
//        xYPlot.setRangeGridlinePaint(Color.BLACK);
//        xYPlot.setDomainGridlinePaint(Color.BLACK);

        //获得Y轴轴线对象
        NumberAxis rangeAxis = (NumberAxis) xYPlot.getRangeAxis();
        //Y轴轴线不自动宽度
        rangeAxis.setAutoRangeIncludesZero(false);
        //Y轴轴线是否显示
        rangeAxis.setAxisLineVisible(false);
        //Y轴轴线刻度
        rangeAxis.setRange(0, 91);
        //Y轴轴线刻度间距
        rangeAxis.setTickUnit(NumberAxis.DEFAULT_TICK_UNIT);
        //获得X轴轴线对象
        NumberAxis domainAxis = (NumberAxis) xYPlot.getDomainAxis();
        //X轴轴线不自动宽度
        domainAxis.setAutoRangeIncludesZero(false);
        //X轴轴线是否显示
        domainAxis.setAxisLineVisible(false);
        //X轴轴线刻度
        domainAxis.setRange(0, 51);
        //X轴轴线刻度间距
        domainAxis.setTickUnit(NumberAxis.DEFAULT_TICK_UNIT);
    }

    /**
     * 画X轴线
     *
     * @param
     */
    private XYSeriesCollection drawingXLine(XYSeriesCollection dataset) {

        for (int i = 0; i <= 3; i++) {
            //创建一条线
            XYSeries firefox = new XYSeries("Firefox");
            //添加第一个点 x轴位置，y轴位置
            firefox.add(0, 0.0 + (2 * i));
            //添加第二个点 x轴位置，y轴位置
            firefox.add(51.0, 0.0 + (2 * i));
            //把刚才那条折线放入List集合中
            dataset.addSeries(firefox);
        }
        for (int i = 0; i <= 7; i++) {
            //创建一条线
            XYSeries firefox = new XYSeries("Firefox");
            //添加第一个点 x轴位置，y轴位置
            firefox.add(0, 10.0 + (2 * i));
            //添加第二个点 x轴位置，y轴位置
            firefox.add(51.0, 10.0 + (2 * i));
            //把刚才那条折线放入List集合中
            dataset.addSeries(firefox);
        }
        for (int i = 0; i < 44; i++) {
            //创建一条线
            XYSeries firefox = new XYSeries("Firefox");
            //添加第一个点 x轴位置，y轴位置
            firefox.add(9, 25.0 + i);
            //添加第二个点 x轴位置，y轴位置
            firefox.add(51.0, 25.0 + i);
            //把刚才那条折线放入List集合中
            dataset.addSeries(firefox);
        }
        for (int i = 0; i <= 4; i++) {
            //创建一条线
            XYSeries firefox = new XYSeries("Firefox");
            //添加第一个点 x轴位置，y轴位置
            firefox.add(0, 69.0 + (2 * i));
            //添加第二个点 x轴位置，y轴位置
            firefox.add(51.0, 69.0 + (2 * i));
            //把刚才那条折线放入List集合中
            dataset.addSeries(firefox);
        }
        return dataset;
    }

    /**
     * 画Y轴线
     *
     * @param
     */
    private XYSeriesCollection drawingYLine(XYSeriesCollection dataset) {


        //创建一条线
        XYSeries firefox = new XYSeries("Firefox");
        //添加第一个点 x轴位置，y轴位置
        firefox.add(9, 4.0);
        //添加第二个点 x轴位置，y轴位置
        firefox.add(9, 81.0);
        //把刚才那条折线放入List集合中
        dataset.addSeries(firefox);
        //体温脉搏呼吸纵线
        for (int i = 0; i < 2; i++) {
            //创建一条线
            XYSeries firefox1 = new XYSeries("Firefox1");
            //添加第一个点 x轴位置，y轴位置
            firefox1.add(3 + (3 * i), 28.0);
            //添加第二个点 x轴位置，y轴位置
            firefox1.add(3 + (3 * i), 73.0);
            //把刚才那条折线放入List集合中
            dataset.addSeries(firefox1);
        }
        //数据 格格 y线
        for (int i = 0; i < 42; i++) {
            //创建一条线
            XYSeries middleY = new XYSeries("middleY");

            //添加第一个点 x轴位置，y轴位置
            middleY.add(10 + (1 * i), 28.0);
            if (i == 5 || i==11 ||i==17||i==23||i==29||i==35) {
                //添加第二个点 x轴位置，y轴位置
                middleY.add(10 + (1 * i), 81.0);
            } else {
                //添加第二个点 x轴位置，y轴位置
                middleY.add(10 + (1 * i), 75.0);
            }
            //把刚才那条折线放入List集合中
            dataset.addSeries(middleY);
        }
        for (int i = 0; i < 6; i++) {
            //创建一条线
            XYSeries bottomRight = new XYSeries("bottomRight");

            //添加第二个点 x轴位置，y轴位置
            bottomRight.add(15 + (6 * i), 4.0);
            //添加第二个点 x轴位置，y轴位置
            bottomRight.add(15 + (6 * i), 28.0);
            //把刚才那条折线放入List集合中
            dataset.addSeries(bottomRight);
        }
        for (int i = 0; i < 7; i++) {
            //创建一条线
            XYSeries bottomyRight = new XYSeries("bottomyRight");

            //添加第二个点 x轴位置，y轴位置
            bottomyRight.add(12 + (6 * i), 20.0);
            //添加第二个点 x轴位置，y轴位置
            bottomyRight.add(12 + (6 * i), 22.0);
            //把刚才那条折线放入List集合中
            dataset.addSeries(bottomyRight);
        }
        return dataset;
    }
}
