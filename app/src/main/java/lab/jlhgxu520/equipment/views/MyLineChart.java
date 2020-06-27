package lab.jlhgxu520.equipment.views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import com.alibaba.fastjson.JSON;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import lab.jlhgxu520.equipment.po.EquipmentData;

public class MyLineChart extends LineChart {
    public MyLineChart(Context context) {
        super(context);
        initSetting();
    }

    public MyLineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSetting();
    }

    public MyLineChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initSetting();
    }

    private void initSetting() {
        getAxisRight().setEnabled(false);//右侧不显示Y轴
//        lineChart.getXAxis().setDrawGridLines(false);//不显示网格
        getAxisLeft().setAxisMinValue(0.0f);//设置Y轴显示最小值，不然0下面会有空隙
        getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴的位置
        getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);    //x轴是在上边显示还是显示在下边
        getXAxis().enableGridDashedLine(10f, 10f, 0f);    //背景用虚线表格来绘制  给整成虚线
        getXAxis().setAxisMinimum(0f);//设置轴的最小值。这样设置将不会根据提供的数据自动计算。
        getXAxis().setGranularityEnabled(true);    //粒度
        getXAxis().setLabelCount(10,true);
        setDoubleTapToZoomEnabled(true);//双击屏幕缩放
    }

    public void setDataList(List<EquipmentData> listBeans){
        LineData lineData = new LineData();
        /***********************************************/
//        List<Entry> rotateEntry = new ArrayList<>();//转速
        List<Entry> coreEntry = new ArrayList<>();//内温
        List<Entry> exterEntry = new ArrayList<>();//外温
        int max = 30;
        int height = 100;
        List<EquipmentData> list = sort(listBeans);
        if (list != null){
            for (int i=0;i<list.size();i++) {
                long startTime = list.get(i).getStart_time();
                long time = list.get(i).getTime();
                long between = (time - startTime)/1000;
                float minute = between/60f;
                if (minute>max)
                    max = (int)(1+minute);
                if (list.get(i).getRotate()>height)
                    height = (int)(list.get(i).getRotate()+1);
                if (list.get(i).getRotate()>height)
                    height = (int)(list.get(i).getCore_temper()+1);
                if (list.get(i).getRotate()>height)
                    height = (int)(list.get(i).getExter_temper()+1);
//                rotateEntry.add(new Entry(minute, (float)list.get(i).getRotate()));
                coreEntry.add(new Entry(minute, (float)list.get(i).getCore_temper()));
                exterEntry.add(new Entry(minute, (float)list.get(i).getExter_temper()));
            }
        }else {
//            rotateEntry.add(new Entry(0, 0));
            coreEntry.add(new Entry(0, 0));
            exterEntry.add(new Entry(0, 0));
        }

        if (max<=30)
            getXAxis().setAxisMaxValue(30.0f);
        else
            getXAxis().setAxisMaxValue(max);
        if (height<=100)
            getAxisLeft().setAxisMaxValue(100.0f);
        else
            getAxisLeft().setAxisMaxValue(height);

        /********************转速***************************/
//        LineDataSet rotateSet = new LineDataSet(rotateEntry, "转速"); // add entries to dataset
//        rotateSet.setColor(Color.RED);
//        rotateSet.setValueTextColor(Color.BLACK);
//        rotateSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);//样式
//        rotateSet.setHighLightColor(Color.RED); // 设置点击某个点时，横竖两条线的颜色
//        rotateSet.setDrawValues(false);//在点上显示数值 默认true
//        rotateSet.setDrawCircles(false);//在点上画圆 默认true

        /********************内温***************************/
        LineDataSet coreSet = new LineDataSet(coreEntry, "内温"); // add entries to dataset
        coreSet.setColor(Color.GREEN);
        coreSet.setValueTextColor(Color.BLACK);
        coreSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);//样式
        coreSet.setHighLightColor(Color.GREEN); // 设置点击某个点时，横竖两条线的颜色
        coreSet.setDrawValues(false);//在点上显示数值 默认true
        coreSet.setDrawCircles(false);//在点上画圆 默认true

        /********************外温***************************/
        LineDataSet exterSet = new LineDataSet(exterEntry, "外温"); // add entries to dataset
        exterSet.setColor(Color.BLUE);
        exterSet.setValueTextColor(Color.BLACK);
        exterSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);//样式
        exterSet.setHighLightColor(Color.BLUE); // 设置点击某个点时，横竖两条线的颜色
        exterSet.setDrawValues(false);//在点上显示数值 默认true
        exterSet.setDrawCircles(false);//在点上画圆 默认true

        /***********************************************/
//        lineData.addDataSet(rotateSet);
        lineData.addDataSet(coreSet);
        lineData.addDataSet(exterSet);

        /***********************************************/
        setData(lineData);
        invalidate();
    }

    private List<EquipmentData> sort(List<EquipmentData> list) {
        if (list == null)
            return null;
        Collections.sort(list, new Comparator<EquipmentData>() {
            @Override
            public int compare(EquipmentData o1, EquipmentData o2) {
                /**
                 * 升序排的话就是第一个参数.compareTo(第二个参数);
                 * 降序排的话就是第二个参数.compareTo(第一个参数);
                 */
                return o2.compareTo(o1);
            }
        });
        return list;
    }

//    public static void main(String[] args) {
//        List<EquipmentData> list = new ArrayList<>();
//        EquipmentData equ1=  new EquipmentData();
//        EquipmentData equ2=  new EquipmentData();
//        EquipmentData equ3=  new EquipmentData();
//        equ1.setTime(300);
//        equ2.setTime(200);
//        equ3.setTime(100);
//        list.add(equ1);
//        list.add(equ2);
//        list.add(equ3);
//        Collections.sort(list, new Comparator<EquipmentData>() {
//            @Override
//            public int compare(EquipmentData o1, EquipmentData o2) {
//                /**
//                 * 升序排的话就是第一个参数.compareTo(第二个参数);
//                 * 降序排的话就是第二个参数.compareTo(第一个参数);
//                 */
//                return o2.compareTo(o1);
//            }
//        });
//        System.out.println(JSON.toJSON(list));
//    }

}
