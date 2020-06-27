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
import lab.jlhgxu520.equipment.adapters.AdminTeacherListAdapter;
import lab.jlhgxu520.equipment.interfaces.myCallBack;
import lab.jlhgxu520.equipment.interfaces.viewCallBack;
import lab.jlhgxu520.equipment.po.AdminClassRoomBean;
import lab.jlhgxu520.equipment.server.AdminServer;
import lab.jlhgxu520.equipment.server.GrpcChannel;
import lab.jlhgxu520.equipment.views.MaxHeightListView;

/**
 * 管理员端
 * 二级目录类
 * 操作对象:教室
 * 添加by教师
 * 删除by教室ID [ 教室ID = to 教师.item ]
 */
public class TeacherToLaboratoryActivity extends Activity {
    private String teacher_id;//UUID
    private String number;//工号
    private String class_number;//教室数量
    private String school;
    private String name;//老师名字

    private TextView nameView;
    private TextView schoolView;
    private TextView class_numberView;

    private MaxHeightListView listView;
    private AdminTeacherListAdapter listAdapter;
    private List<AdminClassRoomBean> listBeans;

    private Button addClass;

    private AdminServer adminServer;

    private LoadingDialog loadingDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_teacher);
        init();
        initData();
        addListener();
    }
    @Override
    protected void onRestart() {
        initListView();
        super.onRestart();
    }
    private void addListener() {
        addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addClass();
            }
        });
    }

    private void addClass() {
        final LoadingDialog loadingDialog = new LoadingDialog(this);
        loadingDialog.setLoadingText("正在添加...");
        setEditDialog("请添加教室号", "520", new viewCallBack() {
            @Override
            public void callBack(String value) {
                if ("".equals(value)){
                    Toast.makeText(TeacherToLaboratoryActivity.this,"请输入教室号",Toast.LENGTH_SHORT).show();
                    return;
                }
                final String class_number = value;
                loadingDialog.show();
                adminServer.addClass(teacher_id, class_number, new myCallBack() {
                    @Override
                    public void onSuccess(Object object) {
                        loadingDialog.dismiss();
                        Bundle bundle = (Bundle)object;
                        AdminClassRoomBean bean = new AdminClassRoomBean();
                        bean.setClass_id(bundle.getString("class_id"));
                        bean.setClass_number(bundle.getString("class_number"));
                        bean.setEquipment_number(bundle.getString("equipment_number"));
                        bean.setSchool(bundle.getString("school"));
                        listBeans.add(bean);
                        if (listAdapter == null){
                            listAdapter = new AdminTeacherListAdapter(TeacherToLaboratoryActivity.this,listBeans,adminServer);
                            listView.setAdapter(listAdapter);
                        }else
                            listAdapter.notifyDataSetChanged();
                        TeacherToLaboratoryActivity.this.class_number = (Integer.parseInt(TeacherToLaboratoryActivity.this.class_number)+1)+"";
                        class_numberView.setText(TeacherToLaboratoryActivity.this.class_number);
                    }

                    @Override
                    public void onFailure(Object object) {
                        loadingDialog.dismiss();
                        Toast.makeText(TeacherToLaboratoryActivity.this,(String)object,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
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
    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        teacher_id = bundle.getString("teacher_id");
        number = bundle.getString("number");
        class_number = bundle.getString("class_number");
        school = bundle.getString("school");
        name = bundle.getString("name");
        nameView.setText(name);
        schoolView.setText(school);
        class_numberView.setText(class_number);
        loadingDialog.setLoadingText("正在加载...");
        initListView();
    }

    private void initListView() {
        loadingDialog.show();
        adminServer.getClassRoomList(teacher_id, new myCallBack() {
            @Override
            public void onSuccess(Object object) {
                loadingDialog.dismiss();
                Bundle bundle = (Bundle)object;
                String jsonString = bundle.getString("jsonData");
                listBeans.clear();
                listBeans = JSONArray.parseArray(jsonString, AdminClassRoomBean.class);
                if (listAdapter == null){
                    listAdapter = new AdminTeacherListAdapter(TeacherToLaboratoryActivity.this,listBeans,adminServer);
                }else {
                    listView.setAdapter(null);
                    listAdapter = new AdminTeacherListAdapter(TeacherToLaboratoryActivity.this,listBeans,adminServer);
                }
                listView.setAdapter(listAdapter);
                listView.setHeight();
            }

            @Override
            public void onFailure(Object object) {
                loadingDialog.dismiss();
                Toast.makeText(TeacherToLaboratoryActivity.this,(String)object,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void remove(){
        TeacherToLaboratoryActivity.this.class_number = (Integer.parseInt(TeacherToLaboratoryActivity.this.class_number)-1)+"";
        class_numberView.setText(TeacherToLaboratoryActivity.this.class_number);
    }
    private void init() {
        addClass = findViewById(R.id.addClass);
        listView = findViewById(R.id.listView);
        nameView = findViewById(R.id.name);
        schoolView = findViewById(R.id.school);
        class_numberView = findViewById(R.id.class_number);
        listBeans = new ArrayList<>();
        adminServer = new AdminServer(GrpcChannel.getChannel());
        loadingDialog = new LoadingDialog(this);
    }
}
