package lab.jlhgxu520.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LineChart lineChart;
    private float[][] data = {{1f,1f},{2f,2f},{4f,4f},{1f,1f}};
    private int length = 10;
    private int height = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lineChart = findViewById(R.id.lineChart);
        lineChart.getAxisRight().setEnabled(false);//右侧不显示Y轴
//        lineChart.getXAxis().setDrawGridLines(false);//不显示网格
        lineChart.getAxisLeft().setAxisMinValue(0.0f);//设置Y轴显示最小值，不然0下面会有空隙
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴的位置
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);    //x轴是在上边显示还是显示在下边
        lineChart.getXAxis().enableGridDashedLine(10f, 10f, 0f);    //背景用虚线表格来绘制  给整成虚线
        lineChart.getXAxis().setAxisMinimum(0f);//设置轴的最小值。这样设置将不会根据提供的数据自动计算。
        lineChart.getXAxis().setGranularityEnabled(true);    //粒度
        lineChart.getXAxis().setLabelCount(10,true);
        lineChart.setDoubleTapToZoomEnabled(true);//双击屏幕缩放

        /***********************************************/
        List<Entry> entries = new ArrayList<Entry>();
        entries.add(new Entry(0, 0));
        for (float i=1;i<length;i++) {
            float p = (float) Math.random()*height;
            entries.add(new Entry(i, p));
        }
        if (entries.size()<20)
            lineChart.getXAxis().setAxisMaxValue(20.0f);
        if (height<15)
            lineChart.getAxisLeft().setAxisMaxValue(15.0f);

        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
        dataSet.setColor(Color.RED);
        dataSet.setValueTextColor(Color.BLUE);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);//样式
        dataSet.setHighLightColor(Color.RED); // 设置点击某个点时，横竖两条线的颜色
        dataSet.setDrawValues(false);//在点上显示数值 默认true
        dataSet.setDrawCircles(false);//在点上画圆 默认true

        /***********************************************/
        LineData lineData = new LineData();
        lineData.addDataSet(dataSet);


        /***********************************************/
        lineChart.setData(lineData);
        lineChart.invalidate();




    }
}
