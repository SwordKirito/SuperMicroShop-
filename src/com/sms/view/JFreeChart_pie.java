package com.sms.view;



import org.jfree.chart.ChartFactory;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class JFreeChart_pie
{
    JFreeChart_pie(){
        DefaultPieDataset dpd=new DefaultPieDataset(); //建立一个默认的饼图
        dpd.setValue("夏装", 25);  //输入数据
        dpd.setValue("秋装", 25);
        dpd.setValue("女式短袖", 45);
        dpd.setValue("男士长裤", 10);
        
        JFreeChart chart=ChartFactory.createPieChart("一月超级微店服装统计",dpd,true,true,false); 
        //可以查具体的API文档,第一个参数是标题，第二个参数是一个数据集，第三个参数表示是否显示Legend，第四个参数表示是否显示提示，第五个参数表示图中是否存在URL

        
        ChartFrame chartFrame=new ChartFrame("一月超级微店服装统计",chart); 
        //chart要放在Java容器组件中，ChartFrame继承自java的Jframe类。该第一个参数的数据是放在窗口左上角的，不是正中间的标题。
        chartFrame.pack(); //以合适的大小展现图形
       chartFrame.setVisible(true);//图形是否可见
 
    }
    
}