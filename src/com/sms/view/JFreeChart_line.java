package com.sms.view;


import org.jfree.chart.ChartFactory;

import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class JFreeChart_line
{
   JFreeChart_line()
    {
    	DefaultCategoryDataset dpd=new DefaultCategoryDataset(); //建立一个默认的折线图
        dpd.addValue(9,"统计", "1号");  //输入数据
        dpd.addValue(5,"统计", "2号");  //输入数据
        dpd.addValue(19,"统计", "3号");  //输入数据
        dpd.addValue(8,"统计", "4号");  //输入数据
        dpd.addValue(3,"统计", "5号");  //输入数据
        dpd.addValue(5,"统计", "6号");  //输入数据
        dpd.addValue(19,"统计", "7号");  //输入数据
        dpd.addValue(3,"统计", "8号");  //输入数据
        dpd.addValue(10,"统计", "9号");  //输入数据
        dpd.addValue(5,"统计", "10号");  //输入数据
        dpd.addValue(13,"统计", "11号");  //输入数据
        dpd.addValue(8,"统计", "12号");  //输入数据
        dpd.addValue(20,"统计", "13号");  //输入数据
        dpd.addValue(5,"统计", "14号");  //输入数据
        dpd.addValue(16,"统计", "15号");  //输入数据
        dpd.addValue(8,"统计", "16号");  //输入数据
        
     
        JFreeChart chart=ChartFactory.createLineChart("一月超级微店服装统计", "时间", "销售额", dpd, PlotOrientation.VERTICAL, true, false, false);
      //可以查具体的API文档,第一个参数是标题，第二个参数是横轴，第三个参数表示是纵轴，第四个参数数据集，第五个参数表示图表方向垂直，第六个是否显示图例，第七个是否使用工具，第八个是否生成url地址
        ChartFrame chartFrame=new ChartFrame("某公司人员组织数据图",chart); 
        //chart要放在Java容器组件中，ChartFrame继承自java的Jframe类。该第一个参数的数据是放在窗口左上角的，不是正中间的标题。
        chartFrame.pack(); //以合适的大小展现图形
        chartFrame.setVisible(true);//图形是否可见
        
    }
}