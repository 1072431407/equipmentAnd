package lab.jlhgxu520.equipment.activitys;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSONArray;
import com.xq.fasterdialog.dialog.LoadingDialog;

import java.util.List;

import lab.jlhgxu520.equipment.R;
import lab.jlhgxu520.equipment.interfaces.myCallBack;
import lab.jlhgxu520.equipment.po.EquipmentData;
import lab.jlhgxu520.equipment.server.GrpcChannel;
import lab.jlhgxu520.equipment.server.StudentServer;
import lab.jlhgxu520.equipment.tools.UserCache;
import lab.jlhgxu520.equipment.views.MyLineChart;
import lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse;
import lab.jlhgxy520.equipment.rpc.proto.student.instructExperimentRequest;
import lab.jlhgxy520.equipment.rpc.proto.student.instructExperimentResponse;

/**
 * 学生端
 * 进行ing实验类
 */
public class StudentLaboratoryActivity extends Activity {
//    private String spinnerArray[] = {"内温","外温","转速","时间间隔"};
    private String equipment_id;
    private String equipment_number;
    private Button startExperiment;
    private Button pauseExperiment;
    private TextView title;

    private boolean isGetData;//继续暂停
    private boolean threadLock;//结束标志
    private StudentServer studentServer;

    private long startTime;

    private MyLineChart lineChart;

    private String spinnerIndex = "内温";
    private Spinner spinner;
    private Button submit;
    private EditText editText;

    private TextView core_future;
    private TextView exter_future;
    private TextView rotate_future;

    private int interval;
    private TextView equ_state;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_class);
        init();
        initData();
        addListener();
    }

    private void addListener() {
        pauseExperiment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                over();
            }
        });
        startExperiment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentServer.instruct(instructExperimentRequest.Instruct.START, equipment_id, UserCache.UUID,"", new myCallBack() {
                    @Override
                    public void onSuccess(Object object) {
                        startTime = System.currentTimeMillis();
                        isGetData = true;
                        pauseExperiment.setVisibility(View.VISIBLE);
                        startExperiment.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Object object) {
                        activityClose();
                        Toast.makeText(StudentLaboratoryActivity.this,(String)object,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                instruct(spinnerIndex,editText.getText().toString());
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] array = getResources().getStringArray(R.array.languages);
                spinnerIndex = array[position];
                System.out.println(array[position]);
                if ("内温".equals(array[position])){
                    editText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    System.out.println(array[position]);
                    return;
                }
                if ("外温".equals(array[position])){
                    editText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    System.out.println(array[position]);
                    return;
                }
                if ("转速".equals(array[position])){
                    editText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    System.out.println(array[position]);
                    return;
                }
                if ("时间间隔".equals(array[position])){
                    editText.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);
                    System.out.println(array[position]);
                    return;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 下达指令
     * @param stamp 标记
     * @param strInstruct 指令
     */
    private void instruct(final String stamp,final String strInstruct) {
        String str = null;
        if ("内温".equals(stamp)){
            str = "core_future:"+strInstruct;
        }
        if ("外温".equals(stamp)){
            str = "exter_future:"+strInstruct;
        }
        if ("转速".equals(stamp)){
            str = "rotate_future:"+strInstruct;
        }
        if ("时间间隔".equals(stamp)){//353042
            str = "interval:"+strInstruct;
        }

        studentServer.instruct(instructExperimentRequest.Instruct.INSTRUCT, equipment_id, UserCache.UUID,str, new myCallBack() {
            @Override
            public void onSuccess(Object object) {
                if ("内温".equals(stamp)){
                    core_future.setText(strInstruct);
                }
                if ("外温".equals(stamp)){
                    exter_future.setText(strInstruct);
                }
                if ("转速".equals(stamp)){
                    rotate_future.setText(strInstruct);
                }
                if ("时间间隔".equals(stamp)){
                    interval = Integer.parseInt(strInstruct);
                }
            }

            @Override
            public void onFailure(Object object) {
                Toast.makeText(StudentLaboratoryActivity.this,(String)object,Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void over() {
        studentServer.instruct(instructExperimentRequest.Instruct.OVER, equipment_id, UserCache.UUID,"", new myCallBack() {
            @Override
            public void onSuccess(Object object) {
                isGetData = false;
                pauseExperiment.setVisibility(View.GONE);
                startExperiment.setVisibility(View.VISIBLE);
                finish();
            }

            @Override
            public void onFailure(Object object) {
                activityClose();
                Toast.makeText(StudentLaboratoryActivity.this,(String)object,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void AsyAcqEquipmentState() {
        @SuppressLint("HandlerLeak") final Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        Bundle bundle = msg.getData();
                        upSetView(bundle.getInt("equ_state")
                                ,bundle.getDouble("rotate_future")
                                ,bundle.getDouble("exter_future")
                                ,bundle.getDouble("core_future"));
                        break;
                    case 2:
                        activityClose();
                        break;
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if (!isGetData)
                        continue;
                    if (threadLock)
                        break;
                    try{
                        EquipmentStateResponse response= studentServer.getEquipmentState(equipment_id);
                        Message message = new Message();
                        message.what = 1;
                        Bundle bundle = new Bundle();
                        bundle.putInt("equ_state",response.getEquState());
                        bundle.putDouble("rotate_future",response.getRotateFuture());
                        bundle.putDouble("exter_future",response.getExterFuture());
                        bundle.putDouble("core_future",response.getCoreFuture());
                        message.setData(bundle);
                        handler.sendMessage(message);
                        Thread.sleep(5000);
                    }catch (Exception e){
                    }
                }
            }
        }).start();
    }

    /**
     * 更新设备状态视图
     * @param equ_state
     * @param rotate_future
     * @param exter_future
     * @param core_future
     */
    private void upSetView(int equ_state, double rotate_future, double exter_future, double core_future) {
        this.core_future.setText(core_future+"");
        this.exter_future.setText(exter_future+"");
        this.rotate_future.setText(rotate_future+"");

        if (equ_state == 0){
            this.equ_state.setText("启动");
            this.equ_state.setTextColor(Color.GREEN);
        }else {
            this.equ_state.setText("停止");
            this.equ_state.setTextColor(Color.RED);
        }
    }

    /**
     * 异步获取设备数据
     */
    private void AsyAcqData() {
        @SuppressLint("HandlerLeak") final Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        Bundle bundle = msg.getData();
                        upView(bundle.getString("data"));
                        break;
                    case 2:
                        activityClose();
                        break;
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if (!isGetData)
                        continue;
                    if (threadLock)
                        break;
                    try{
                        List<EquipmentData> list = studentServer.realTimeData(startTime,equipment_id,UserCache.UUID);
                        if (list == null){
                            Message message = new Message();
                            message.what = 2;
                            handler.sendMessage(message);
                            return;
                        }
                        if (list.size()>0){
                            Message message = new Message();
                            message.what = 1;
                            Bundle bundle = new Bundle();
                            bundle.putString("data",JSONArray.toJSONString(list));
                            message.setData(bundle);
                            handler.sendMessage(message);
                        }
                        Thread.sleep(interval*1000);
                    }catch (Exception e){
                        Message message = new Message();
                        message.what = 2;
                        handler.sendMessage(message);
                        return;
                    }
                }
            }
        }).start();
    }
    /**
     * 更新设备状态视图
     * @param jsonData 视图数据
     */
    private void upView(String jsonData){
        List<EquipmentData> list = JSONArray.parseArray(jsonData,EquipmentData.class);
        lineChart.setDataList(list);
    }
    private void activityClose() {
        threadLock = true;
        final LoadingDialog loadingDialog = new LoadingDialog(this);
        loadingDialog.setLoadingText("重连设备....");
        loadingDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {}
                loadingDialog.dismiss();
                StudentLaboratoryActivity.this.setResult(2,getIntent());
                StudentLaboratoryActivity.this.finish();
                Looper.prepare();
                Toast.makeText(StudentLaboratoryActivity.this,"重连失败!",Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }).start();

    }

    private void initData() {
//        ArrayAdapter adapter=new ArrayAdapter(this,R.layout.spinner_layout,spinnerArray);
//        spinner.setAdapter(adapter);

        pauseExperiment.setVisibility(View.GONE);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        equipment_id = bundle.getString("equipment_id");
        equipment_number = bundle.getString("equipment_number");
        title.setText(equipment_number);
        threadLock = false;
        lineChart.setDataList(null);
        AsyAcqData();
        AsyAcqEquipmentState();
    }

    private void init() {
        title = findViewById(R.id.title);
        startExperiment = findViewById(R.id.startExperiment);
        studentServer = new StudentServer(GrpcChannel.getChannel());
        pauseExperiment = findViewById(R.id.pauseExperiment);
        lineChart = findViewById(R.id.lineChart);

        spinner = findViewById(R.id.spinner);
        editText = findViewById(R.id.editText);
        submit = findViewById(R.id.submit);
        equ_state = findViewById(R.id.equ_state);
        core_future = findViewById(R.id.core_future);
        exter_future = findViewById(R.id.exter_future);
        rotate_future = findViewById(R.id.rotate_future);
    }
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if(keyCode == KeyEvent.KEYCODE_BACK){
//            return false;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}
