package lab.jlhgxu520.equipment.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSONArray;
import com.xq.fasterdialog.bean.InputBean;
import com.xq.fasterdialog.dialog.EditDialog;
import com.xq.fasterdialog.dialog.LoadingDialog;
import com.xq.fasterdialog.dialog.base.BaseDialog;

import java.util.ArrayList;
import java.util.List;

import lab.jlhgxu520.equipment.R;
import lab.jlhgxu520.equipment.adapters.AdminEquipmentListAdapter;
import lab.jlhgxu520.equipment.adapters.AdminTeacherListAdapter;
import lab.jlhgxu520.equipment.interfaces.myCallBack;
import lab.jlhgxu520.equipment.interfaces.viewCallBack;
import lab.jlhgxu520.equipment.po.AdminClassRoomBean;
import lab.jlhgxu520.equipment.po.AdminEquipmentBean;
import lab.jlhgxu520.equipment.server.AdminServer;
import lab.jlhgxu520.equipment.server.GrpcChannel;
import lab.jlhgxu520.equipment.views.MaxHeightListView;

/**
 * 管理员端
 * 三级目录类
 * 操作对象:设备
 * 添加by教室
 * 删除by设备ID [ 设备ID = to 教室.item ]
 */
public class LaboratoryToEquipmentActivity extends Activity {
    private String class_id;//实验室唯一标识UUID
    private String class_number_str;//实验室号 520
    private String equipment_number_str;//设备数量

    private TextView class_number;
    private TextView equipment_number;
    private Button addEquipment;

    private MaxHeightListView listView;
    private AdminEquipmentListAdapter listAdapter;
    private List<AdminEquipmentBean> listBeans;

    private AdminServer adminServer;
    private LoadingDialog loadingDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_equipment);
        init();
        initData();
        addListener();
    }

    @Override
    protected void onRestart() {
        initList();
        super.onRestart();
    }
    private void addListener() {
        addEquipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEquipment();
            }
        });
    }
    private void addEquipment() {
        final LoadingDialog loadingDialog = new LoadingDialog(this);
        loadingDialog.setLoadingText("正在添加...");
        setEditDialog("请添加设备ID", "0001", new viewCallBack() {
            @Override
            public void callBack(String value) {
                if ("".equals(value)){
                    Toast.makeText(LaboratoryToEquipmentActivity.this,"请输入设备ID",Toast.LENGTH_SHORT).show();
                    return;
                }
                final String class_number = value;
                loadingDialog.show();
                adminServer.adminAddEquipment(class_id, class_number, new myCallBack() {
                    @Override
                    public void onSuccess(Object object) {
                        loadingDialog.dismiss();
                        Bundle bundle = (Bundle)object;
                        AdminEquipmentBean bean = new AdminEquipmentBean();
                        bean.setClass_id(bundle.getString("class_id"));
                        bean.setEquipment_id(bundle.getString("equipment_id"));
                        bean.setEquipment_number(bundle.getString("equipment_number"));
                        bean.setRegister_time(bundle.getLong("time"));
                        listBeans.add(bean);
                        if (listAdapter == null){
                            listAdapter = new AdminEquipmentListAdapter(LaboratoryToEquipmentActivity.this,listBeans,adminServer);
                            listView.setAdapter(listAdapter);
                        }else
                            listAdapter.notifyDataSetChanged();
                        listView.setHeight();
                        LaboratoryToEquipmentActivity.this.equipment_number_str = (Integer.parseInt(LaboratoryToEquipmentActivity.this.equipment_number_str)+1)+"";
                        equipment_number.setText(LaboratoryToEquipmentActivity.this.equipment_number_str);
                    }

                    @Override
                    public void onFailure(Object object) {
                        loadingDialog.dismiss();
                        Toast.makeText(LaboratoryToEquipmentActivity.this,(String)object,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    public void remove(){
        equipment_number_str = (Integer.parseInt(equipment_number_str)-1)+"";
        equipment_number.setText(equipment_number_str);
    }
    private void setEditDialog(String title, String hint, final viewCallBack callBack){
        final EditDialog editDialog = new EditDialog(this);
        InputBean inputBean = new InputBean();
        editDialog.setCustomView(R.layout.layout_editdialog_meterail);//layout_editdialog_xq
        editDialog.setStyle(R.style.Animate_Alpha);
        editDialog.setWidthPercent(0.8f);
        editDialog.setCanceledOnTouchOutside(false);
        editDialog.setPositiveText("确定");
        editDialog.setNegativeText("取消");
        inputBean.setHint(hint);
        inputBean.setInputType(InputType.TYPE_CLASS_NUMBER);
        editDialog.setTitle(title);
        editDialog.setInputBean(0,inputBean);
        editDialog.show();
        editDialog.setPositiveListener(new BaseDialog.OnDialogClickListener(){
            @Override
            public void onClick(BaseDialog dialog) {
                SparseArray<CharSequence> array = editDialog.getTextArray();
                String message = (String)array.get(0);
                callBack.callBack(message);
            }
        });
    }
    private void initList() {
        loadingDialog.show();
        adminServer.adminGetEquipmentList(class_id, new myCallBack() {
            @Override
            public void onSuccess(Object object) {
                loadingDialog.dismiss();
                Bundle bundle = (Bundle)object;
                String jsonString = bundle.getString("jsonData");
                listBeans.clear();
                listBeans = JSONArray.parseArray(jsonString, AdminEquipmentBean.class);
                if (listAdapter == null){
                    listAdapter = new AdminEquipmentListAdapter(LaboratoryToEquipmentActivity.this,listBeans,adminServer);
                }else {
                    listView.setAdapter(null);
                    listAdapter = new AdminEquipmentListAdapter(LaboratoryToEquipmentActivity.this,listBeans,adminServer);
                }
                listView.setAdapter(listAdapter);
                listView.setHeight();
            }

            @Override
            public void onFailure(Object object) {
                loadingDialog.dismiss();
                Toast.makeText(LaboratoryToEquipmentActivity.this,(String)object,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        class_id = bundle.getString("class_id");
        class_number_str = bundle.getString("class_number");
        equipment_number_str = bundle.getString("equipment_number");
        class_number.setText(class_number_str);
        equipment_number.setText(equipment_number_str);
        loadingDialog.setLoadingText("正在加载...");
        initList();
    }

    private void init() {
        class_number = findViewById(R.id.class_number);
        equipment_number = findViewById(R.id.equipment_number);
        listView = findViewById(R.id.listView);
        listBeans = new ArrayList<>();
        addEquipment = findViewById(R.id.addEquipment);
        adminServer = new AdminServer(GrpcChannel.getChannel());
        loadingDialog = new LoadingDialog(this);
    }
}
