package lab.jlhgxu520.equipment.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSONArray;
import com.xq.fasterdialog.dialog.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import lab.jlhgxu520.equipment.R;
import lab.jlhgxu520.equipment.adapters.AdminListAdapter;
import lab.jlhgxu520.equipment.adapters.TeacherListAdapter;
import lab.jlhgxu520.equipment.interfaces.myCallBack;
import lab.jlhgxu520.equipment.po.AdminTeacherBean;
import lab.jlhgxu520.equipment.po.Laboratory;
import lab.jlhgxu520.equipment.server.GrpcChannel;
import lab.jlhgxu520.equipment.server.TeacherServer;
import lab.jlhgxu520.equipment.tools.UserCache;
import lab.jlhgxu520.equipment.views.MaxHeightListView;

/**
 * 教师端
 * 主页面类
 */
public class TeacherActivity extends Activity {
    private TextView school;
    private TextView name;
    private TextView class_number;
    private SharedPreferences preferences;
    private TeacherServer teacherServer;

    private List<Laboratory> listBeans;
    private MaxHeightListView listView;
    private TeacherListAdapter listAdapter;

    private Button outLogin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        init();
        initData();
        addListener();
    }

    private void addListener() {
        outLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initData() {
        school.setText(UserCache.school);
        name.setText(UserCache.name);
        initListView();
    }

    @Override
    protected void onRestart() {
        initListView();
        super.onRestart();
    }

    private void initListView() {
        final LoadingDialog loadingDialog = new LoadingDialog(this);
        loadingDialog.setLoadingText("正在加载...");
        loadingDialog.show();
        teacherServer.getLaboratoryList(UserCache.UUID, new myCallBack() {
            @Override
            public void onSuccess(Object object) {
                Bundle bundle = (Bundle) object;
                String jsonString = bundle.getString("jsonData");
                listBeans.clear();
                listBeans = JSONArray.parseArray(jsonString, Laboratory.class);
                if (listAdapter == null){
                    listAdapter = new TeacherListAdapter(TeacherActivity.this,listBeans);
                }else {
                    listView.setAdapter(null);
                    listAdapter = new TeacherListAdapter(TeacherActivity.this,listBeans);
                }
                listView.setAdapter(listAdapter);
                listView.setHeight();
                class_number.setText(listBeans.size()+"");
                loadingDialog.dismiss();
            }

            @Override
            public void onFailure(Object object) {
                loadingDialog.dismiss();
                Toast.makeText(TeacherActivity.this,(String)object,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        preferences = getSharedPreferences("equipment", Context.MODE_PRIVATE);
        teacherServer = new TeacherServer(GrpcChannel.getChannel());
        school = findViewById(R.id.school);
        name = findViewById(R.id.name);
        outLogin = findViewById(R.id.outLogin);
        listView = findViewById(R.id.listView);
        listBeans = new ArrayList<>();
        class_number = findViewById(R.id.class_number);
        UserCache.UUID = preferences.getString("uuid", "");
        UserCache.power = preferences.getInt("power", 1);
        UserCache.school = preferences.getString("school", "");
        UserCache.name = preferences.getString("field1", "");
        UserCache.number = preferences.getString("field2", "");
    }
}
