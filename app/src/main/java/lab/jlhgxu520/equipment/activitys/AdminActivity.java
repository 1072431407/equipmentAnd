package lab.jlhgxu520.equipment.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSONArray;
import com.xq.fasterdialog.dialog.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import lab.jlhgxu520.equipment.R;
import lab.jlhgxu520.equipment.adapters.AdminListAdapter;
import lab.jlhgxu520.equipment.interfaces.myCallBack;
import lab.jlhgxu520.equipment.po.AdminTeacherBean;
import lab.jlhgxu520.equipment.server.AdminServer;
import lab.jlhgxu520.equipment.server.GrpcChannel;
import lab.jlhgxu520.equipment.views.MaxHeightListView;

/**
 * 管理员端
 * 一级目录类
 * 操作对象:教师
 */
public class AdminActivity extends Activity {
    private Button addTeacher;
    private MaxHeightListView listView;
    private List<AdminTeacherBean> listBeans;
    private AdminListAdapter listAdapter;

    private AdminServer adminServer;

    private LoadingDialog loadingDialog;
    private Button outLogin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        init();
        initData();
        addListener();
    }

    @Override
    protected void onRestart() {
        initListView();
        super.onRestart();
    }

    private void initListView() {
        loadingDialog.show();
        adminServer.getTeacherList(new myCallBack() {
            @Override
            public void onSuccess(Object object) {
                loadingDialog.dismiss();
                Bundle bundle = (Bundle) object;
                String jsonString = bundle.getString("jsonData");
                listBeans.clear();
                listBeans = JSONArray.parseArray(jsonString, AdminTeacherBean.class);
                if (listAdapter == null){
                    listAdapter = new AdminListAdapter(AdminActivity.this,listBeans,adminServer);
                }else {
                    listView.setAdapter(null);
                    listAdapter = new AdminListAdapter(AdminActivity.this,listBeans,adminServer);
                }
                listView.setAdapter(listAdapter);
                listView.setHeight();
            }
            @Override
            public void onFailure(Object object) {
                loadingDialog.dismiss();
                Toast.makeText(AdminActivity.this,(String)object,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        loadingDialog.setLoadingText("加载中...");
        initListView();
    }

    private void addListener() {
        outLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        addTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this,RegisterActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("power",2);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void init() {
        addTeacher = findViewById(R.id.addTeacher);
        listView = findViewById(R.id.listView);
        adminServer = new AdminServer(GrpcChannel.getChannel());
        listBeans = new ArrayList<>();
        loadingDialog = new LoadingDialog(this);
        outLogin = findViewById(R.id.outLogin);

    }
}
