package com.rhy.jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
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
            } catch (Exception e) {
            }
        }
    }

    /**
     * 获取一个演示用的组合数据集对象
     *
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


    @Test
    public void test1() throws Exception {

        //创建一条线
        XYSeries firefox = new XYSeries( "Firefox" );
        //添加第一个点 x轴位置，y轴位置
        firefox.add( 1.0 , 1.0 );
        //添加第二个点 x轴位置，y轴位置
        firefox.add( 10.0 , 10.0 );
        //添加第三个点 x轴位置，y轴位置
        firefox.add( 13.0 , 13.0 );
        XYSeries chrome = new XYSeries( "Chrome" );
        //添加第一个点 x轴位置，y轴位置
        chrome.add( 1.0 , 1.0 );
        //添加第三个点 x轴位置，y轴位置
        chrome.add( 13.0 , 13.0 );
        //添加第二个点 x轴位置，y轴位置
        chrome.add( 10.0 , 10.0 );
//        XYSeries iexplorer = new XYSeries( "InternetExplorer" );
//        iexplorer.add( 3.0 , 4.0 );
//        iexplorer.add( 4.0 , 5.0 );
//        iexplorer.add( 5.0 , 4.0 );
        //这个就可以理解为线的List集合
        XYSeriesCollection dataset = new XYSeriesCollection();
        //把刚才那条折线放入List集合中
        dataset.addSeries( firefox );
        dataset.addSeries( chrome );
//        dataset.addSeries( iexplorer );
        //固定方法，ChartFactory利用这个类构建一个折线图
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                "Browser usage statastics",
                "Category",
                "Score",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
        //创建一个文本注释
        XYTextAnnotation anno = new XYTextAnnotation("我的大宝贝", 10, 9);
        //画在图上
        xylineChart.getXYPlot().addAnnotation(anno);
        XYLineAndShapeRenderer xylinerenderer= (XYLineAndShapeRenderer) xylineChart.getXYPlot().getRenderer();
        xylinerenderer.setSeriesPaint(0,Color.YELLOW);
        xylinerenderer.setSeriesPaint(1,Color.BLACK);
        //图片的宽度
        int width = 640; /* Width of the image */
        //图片的高度
        int height = 480; /* Height of the image */
        //保存的图片位置
        File XYChart = new File( System.getProperty("user.dir")+"\\img\\XYLineChart.jpeg" );
        //就是一个工具类  固定用法  将图片保存到本地
        ChartUtilities.saveChartAsJPEG( XYChart, xylineChart, width, height);
    }//原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/jfreechart/jfreechart_xy_chart.html

    @Test
    public void test2() throws IOException {
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

        //获得渲染器这个意思
        XYLineAndShapeRenderer xyLineAndShapeRenderer = (XYLineAndShapeRenderer) xYPlot.getRenderer();
        for(int i=0;i<119;i++){
            xyLineAndShapeRenderer.setSeriesPaint(i,Color.BLACK);
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
            firefox.add(0, 4.0 + (2 * i));
            //添加第二个点 x轴位置，y轴位置
            firefox.add(51.0, 4.0 + (2 * i));
            //把刚才那条折线放入List集合中
            dataset.addSeries(firefox);
        }
        for (int i = 0; i <= 7; i++) {
            //创建一条线
            XYSeries firefox = new XYSeries("Firefox");
            //添加第一个点 x轴位置，y轴位置
            firefox.add(0, 14.0 + (2 * i));
            //添加第二个点 x轴位置，y轴位置
            firefox.add(51.0, 14.0 + (2 * i));
            //把刚才那条折线放入List集合中
            dataset.addSeries(firefox);
        }
        for (int i = 0; i < 44; i++) {
            //创建一条线
            XYSeries firefox = new XYSeries("Firefox");
            //添加第一个点 x轴位置，y轴位置
            firefox.add(9, 29.0 + i);
            //添加第二个点 x轴位置，y轴位置
            firefox.add(51.0, 29.0 + i);
            //把刚才那条折线放入List集合中
            dataset.addSeries(firefox);
        }
        for (int i = 0; i <= 4; i++) {
            //创建一条线
            XYSeries firefox = new XYSeries("Firefox");
            //添加第一个点 x轴位置，y轴位置
            firefox.add(0, 73.0 + (2 * i));
            //添加第二个点 x轴位置，y轴位置
            firefox.add(51.0, 73.0 + (2 * i));
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
