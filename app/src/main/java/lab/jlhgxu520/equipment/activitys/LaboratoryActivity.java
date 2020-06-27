package lab.jlhgxu520.equipment.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSONArray;
import com.xq.fasterdialog.dialog.LoadingDialog;
import com.xq.fasterdialog.dialog.NormalDialog;
import com.xq.fasterdialog.dialog.base.BaseDialog;

import java.util.ArrayList;
import java.util.List;

import lab.jlhgxu520.equipment.R;
import lab.jlhgxu520.equipment.adapters.LaboratoryGridAdapter;
import lab.jlhgxu520.equipment.adapters.TeacherListAdapter;
import lab.jlhgxu520.equipment.interfaces.myCallBack;
import lab.jlhgxu520.equipment.po.Equipment;
import lab.jlhgxu520.equipment.po.Laboratory;
import lab.jlhgxu520.equipment.server.GrpcChannel;
import lab.jlhgxu520.equipment.server.TeacherServer;
import lab.jlhgxu520.equipment.views.MaxHeightGridView;

/**
 * 教师端
 * 实验室统计
 * gridView
 */
public class LaboratoryActivity extends Activity {
    private String class_id;
    private String class_number;
    private String class_code;
    private int equipment_number;
    private int class_state;

    private TextView title;
    private ImageButton flush;
    private TextView equ_number;

    private Button attendClass;
    private Button finishClass;

    private MaxHeightGridView gridView;
    private LaboratoryGridAdapter gridAdapter;
    private List<Equipment> listBeans;

    private TeacherServer teacherServer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labortory);
        init();
        initData();
        addListener();
    }

    @Override
    protected void onRestart() {
        initGridView();
        super.onRestart();
    }

    private void addListener() {
        //下课
        finishClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishClass();
            }
        });
        //上课
        attendClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attendClass();
            }
        });
        //刷新
        flush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initGridView();
            }
        });
    }

    private void finishClass() {
        final LoadingDialog loadingDialog = new LoadingDialog(this);
        loadingDialog.setLoadingText("正在解散课堂...");
        final NormalDialog normalDialog = new NormalDialog(this);
        normalDialog.setTitle("提示");
        normalDialog.setPositiveText("确定");
        normalDialog.setNegativeText("取消");
        normalDialog.setWidthPercent(0.8f);
        normalDialog.setCanceledOnTouchOutside(true);
        normalDialog.setContent("您确定解散课堂吗?");
        normalDialog.show();
        normalDialog.setPositiveListener(new BaseDialog.OnDialogClickListener() {
            @Override
            public void onClick(BaseDialog dialog) {
                loadingDialog.show();
                normalDialog.dismiss();
                teacherServer.finishClass(class_id, new myCallBack() {
                    @Override
                    public void onSuccess(Object object) {
                        loadingDialog.dismiss();
                        Toast.makeText(LaboratoryActivity.this,"课堂已解散!",Toast.LENGTH_SHORT).show();
                        LaboratoryActivity.this.finish();
                    }

                    @Override
                    public void onFailure(Object object) {
                        loadingDialog.dismiss();
                        Toast.makeText(LaboratoryActivity.this,(String)object,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        normalDialog.setNegativeListener(new BaseDialog.OnDialogClickListener() {
            @Override
            public void onClick(BaseDialog dialog) {
                normalDialog.dismiss();
            }
        });
    }

    private void attendClass() {
        final LoadingDialog loadingDialog = new LoadingDialog(this);
        loadingDialog.setLoadingText("加载中...");
        loadingDialog.show();
        teacherServer.attendClass(class_id, new myCallBack() {
            @Override
            public void onSuccess(Object object) {
                Bundle bundle = (Bundle)object;
                String class_code = bundle.getString("class_code");
                LaboratoryActivity.this.class_code = class_code;
                LaboratoryActivity.this.class_state = 1;
                setView();
                loadingDialog.dismiss();
            }

            @Override
            public void onFailure(Object object) {
                loadingDialog.dismiss();
                Toast.makeText(LaboratoryActivity.this,(String)object,Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        class_id = bundle.getString("class_id");
        class_number = bundle.getString("class_number");
        class_state = bundle.getInt("class_state");
        equipment_number = bundle.getInt("equipment_number");
        if (class_state==1)
            class_code = bundle.getString("class_code");
        setView();
    }

    private void setView(){
        setTitle();
        setButton();
        initGridView();
    }

    private void initGridView() {
        final LoadingDialog loadingDialog = new LoadingDialog(this);
        loadingDialog.setLoadingText("加载中...");
        loadingDialog.show();
        teacherServer.getEquipmentList(class_id, new myCallBack() {
            @Override
            public void onSuccess(Object object) {
                Bundle bundle = (Bundle) object;
                String jsonString = bundle.getString("jsonData");
                listBeans.clear();
                listBeans = JSONArray.parseArray(jsonString, Equipment.class);
                if (gridAdapter == null){
                    gridAdapter = new LaboratoryGridAdapter(LaboratoryActivity.this,listBeans);
                }else {
                    gridView.setAdapter(null);
                    gridAdapter = new LaboratoryGridAdapter(LaboratoryActivity.this,listBeans);
                }
                gridView.setAdapter(gridAdapter);
                gridView.setHeight(2);
                loadingDialog.dismiss();
                int sum = 0;
                for (int i=0;i<listBeans.size();i++)
                    if (listBeans.get(i).getState()==1)
                        sum++;
                setEquipmentNumber(sum);
            }

            @Override
            public void onFailure(Object object) {
                loadingDialog.dismiss();
                Toast.makeText(LaboratoryActivity.this,(String)object,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setButton(){
        if (class_state == 1){
            attendClass.setVisibility(View.GONE);
            finishClass.setVisibility(View.VISIBLE);
        }else{
            attendClass.setVisibility(View.VISIBLE);
            finishClass.setVisibility(View.GONE);
        }
    }

    private void setEquipmentNumber(int i) {
        equ_number.setText(i+"/"+equipment_number);
    }

    private void setTitle() {
        if (class_state==1){
            title.setText(class_number+":"+class_code);
        }else
            title.setText(class_number+":未开课");
    }


    private void init() {
        title = findViewById(R.id.title);
        flush = findViewById(R.id.flush);
        equ_number = findViewById(R.id.equipment_number);
        attendClass = findViewById(R.id.attendClass);
        finishClass = findViewById(R.id.finishClass);
        teacherServer = new TeacherServer(GrpcChannel.getChannel());
        listBeans = new ArrayList<>();
        gridView = findViewById(R.id.gridView);
    }
}
