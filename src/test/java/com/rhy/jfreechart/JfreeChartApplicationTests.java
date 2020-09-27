package com.rhy.jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@SpringBootTest
class JfreeChartApplicationTests {

    @Test
    void contextLoads() {
        CategoryDataset dataset = getDataSet2();
        JFreeChart chart = ChartFactory.createBarChart3D(
                "水果产量图", // 图表标题
                "水果", // 目录轴的显示标签
                "产量", // 数值轴的显示标签
                dataset, // 数据集
                PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                true,  // 是否显示图例(对于简单的柱状图必须是 false)
                false, // 是否生成工具
                false  // 是否生成 URL 链接
        );
        //设置标题字体
        TextTitle textTitle = chart.getTitle();
        textTitle.setFont(new Font("黑体", Font.PLAIN, 20));

        chart.setBackgroundPaint(Color.WHITE);
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setAxisLineVisible(false);
        //设置刻度标记字体
        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));
        //设置标签字体
        domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));
        NumberAxis numberaxis = (NumberAxis) plot.getRangeAxis();
        numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));
        numberaxis.setLabelFont(new Font("黑体", Font.PLAIN, 12));
        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
        plot.setDomainAxis(domainAxis);
        BarRenderer3D renderer = new BarRenderer3D();
        renderer.setBaseOutlinePaint(Color.BLACK);
        // 显示每个柱的数值，并修改该数值的字体属性
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        // 设置每个地区所包含的平行柱的之间距离
        renderer.setItemMargin(0.1);
        // 设置柱的数值可见
        renderer.setBaseItemLabelsVisible(true);
        plot.setRenderer(renderer);
        // 设置柱的透明度
        plot.setForegroundAlpha(0.8f);

        FileOutputStream fos_jpg = null;
        try {
            fos_jpg = new FileOutputStream("D:\\ideaProject\\jfree-chart\\img\\fruitt.jpg");
            ChartUtilities.writeChartAsJPEG(fos_jpg, 1, chart, 400, 300);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos_jpg.close();
            } catch (Exception e) {}
        }
    }
    /**
     * 获取一个演示用的组合数据集对象
     * @return
     */
    private static CategoryDataset getDataSet2() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(100, "北京", "苹果");
        dataset.addValue(100, "上海", "苹果");
        dataset.addValue(100, "广州", "苹果");
        dataset.addValue(200, "北京", "梨子");
        dataset.addValue(200, "上海", "梨子");
        dataset.addValue(200, "广州", "梨子");
        dataset.addValue(300, "北京", "葡萄");
        dataset.addValue(300, "上海", "葡萄");
        dataset.addValue(300, "广州", "葡萄");
        dataset.addValue(400, "北京", "香蕉");
        dataset.addValue(400, "上海", "香蕉");
        dataset.addValue(400, "广州", "香蕉");
        dataset.addValue(500, "北京", "荔枝");
        dataset.addValue(500, "上海", "荔枝");
        dataset.addValue(500, "广州", "荔枝");
        return dataset;
    }


    /**
     * 创建JFreeChart LineXY Chart（折线图）
     */
    @Test
    void contextLoad2() {
        //步骤1：创建XYDataset对象（准备数据）
        XYDataset dataset = createXYDataset();
        //步骤2：根据Dataset 生成JFreeChart对象，以及做相应的设置
        JFreeChart freeChart = createChart(dataset);
        //步骤3：将JFreeChart对象输出到文件，Servlet输出流等
        saveAsFile(freeChart, "D:\\ideaProject\\jfree-chart\\img\\lineXY.png", 600, 400);
    }

    // 保存为文件
    public void saveAsFile(JFreeChart chart, String outputPath,
                                  int weight, int height) {
        FileOutputStream out = null;
        try {
            File outFile = new File(outputPath);
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            out = new FileOutputStream(outputPath);
            // 保存为PNG
            ChartUtilities.writeChartAsPNG(out, chart, weight, height);
            // 保存为JPEG
            // ChartUtilities.writeChartAsJPEG(out, chart, weight, height);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }
    }

    // 根据XYDataset创建JFreeChart对象
    public JFreeChart createChart(XYDataset dataset) {
        // 创建JFreeChart对象：ChartFactory.createXYLineChart
        JFreeChart jfreechart = ChartFactory.createXYLineChart("XYLine Chart Demo", // 标题
                "年分", // categoryAxisLabel （category轴，横轴，X轴标签）
                "数量", // valueAxisLabel（value轴，纵轴，Y轴的标签）
                dataset, // dataset
                PlotOrientation.VERTICAL,
                true, // legend
                false, // tooltips
                false); // URLs

        // 使用CategoryPlot设置各种参数。以下设置可以省略。
        XYPlot plot = (XYPlot) jfreechart.getPlot();
        // 背景色 透明度
        plot.setBackgroundAlpha(0.5f);
        // 前景色 透明度
        plot.setForegroundAlpha(0.5f);
        // 其它设置可以参考XYPlot类

        return jfreechart;
    }

    /**
     * 创建XYDataset对象
     *
     */
    private XYDataset createXYDataset() {
        XYSeries xyseries1 = new XYSeries("One");
        xyseries1.add(1987, 50);
        xyseries1.add(1997, 20);
        xyseries1.add(2007, 30);
//
//        XYSeries xyseries2 = new XYSeries("Two");
//        xyseries2.add(1987, 20);
//        xyseries2.add(1997, 10D);
//        xyseries2.add(2007, 40D);
//
//
//        XYSeries xyseries3 = new XYSeries("Three");
//        xyseries3.add(1987, 40);
//        xyseries3.add(1997, 30.0008);
//        xyseries3.add(2007, 38.24);


        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();

        xySeriesCollection.addSeries(xyseries1);
//        xySeriesCollection.addSeries(xyseries2);
//        xySeriesCollection.addSeries(xyseries3);

        return xySeriesCollection;
    }
    @Test
    public void test1()throws Exception
    {
        //创建一条线
        XYSeries firefox = new XYSeries( "Firefox" );
        //添加第一个点 x轴位置，y轴位置
        firefox.add( 1.0 , 1.0 );
        //添加第二个点 x轴位置，y轴位置
        firefox.add( 2.0 , 4.0 );
        //添加第三个点 x轴位置，y轴位置
        firefox.add( 3.0 , 3.0 );
//        XYSeries chrome = new XYSeries( "Chrome" );
//        chrome.add( 1.0 , 4.0 );
//        chrome.add( 2.0 , 5.0 );
//        chrome.add( 3.0 , 6.0 );
//        XYSeries iexplorer = new XYSeries( "InternetExplorer" );
//        iexplorer.add( 3.0 , 4.0 );
//        iexplorer.add( 4.0 , 5.0 );
//        iexplorer.add( 5.0 , 4.0 );
        //这个就可以理解为线的List集合
        XYSeriesCollection dataset = new XYSeriesCollection();
        //把刚才那条折线放入List集合中
        dataset.addSeries( firefox );
//        dataset.addSeries( chrome );
//        dataset.addSeries( iexplorer );
        //固定方法，ChartFactory利用这个类构建一个折线图
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                "Browser usage statastics",
                "Category",
                "Score",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
        //图片的宽度
        int width = 640; /* Width of the image */
        //图片的高度
        int height = 480; /* Height of the image */
        //保存的图片位置
        File XYChart = new File( "D:\\ideaProject\\jfree-chart\\img\\XYLineChart.jpeg" );
        //就是一个工具类  固定用法  将图片保存到本地
        ChartUtilities.saveChartAsJPEG( XYChart, xylineChart, width, height);
    }//原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/jfreechart/jfreechart_xy_chart.html


}
