package lab.jlhgxu520.equipment.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.xq.fasterdialog.dialog.LoadingDialog;
import com.xq.fasterdialog.dialog.NormalDialog;

import lab.jlhgxu520.equipment.R;
import lab.jlhgxu520.equipment.interfaces.myCallBack;
import lab.jlhgxu520.equipment.server.GrpcChannel;
import lab.jlhgxu520.equipment.server.UserServer;

public class RegisterActivity extends Activity {
    private EditText userName;
    private EditText password;
    private EditText password1;
    private EditText authCode;
    private TextView getAuthCode;
    private ImageButton register;
    private UserServer userServer;
    private LoadingDialog loadingDialog;
    private NormalDialog normalDialog;

    private int whichPower;
    private TextView school;
    private EditText field_1;//1.学生姓名2.教室编号
    private EditText  field_2;//1.学号2.教室IP
    private String SystemAuthCode;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        initData();
        addListener();
    }

    private void initData() {
        loadingDialog.setLoadingText("注册中...");
        normalDialog.setTitle("验证码");
        normalDialog.setPositiveText("确定");
        normalDialog.setWidthPercent(0.8f);
        normalDialog.setCanceledOnTouchOutside(true);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        whichPower = bundle.getInt("power");
        if(whichPower==1){
            field_2.setHint("学号:161*****01");
        }else{
            field_2.setHint("工号:155*****22");

        }
    }

    private String getSystemAuthCode(){
        int ren = (int)(Math.random()*(9999-1000)+1000);
        System.out.println("getSystemAuthCode:"+ren);
        return ren+"";
    }
    private void addListener() {
        getAuthCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SystemAuthCode = getSystemAuthCode();
                normalDialog.setContent(SystemAuthCode);
                normalDialog.show();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String usernameStr = userName.getText().toString();
                final String passwordStr = password.getText().toString();
                final String password1Str = password1.getText().toString();
                final String authCodeStr = authCode.getText().toString();

                final int power = whichPower;
                final String schoolStr = school.getText().toString();
                final String field1Str = field_1.getText().toString();
                final String field2Str = field_2.getText().toString();
                if ("".equals(usernameStr)){
                    toast("请输入电话号!");
                    return;
                }
                if ("".equals(passwordStr)){
                    toast("请输入密码!");
                    return;
                }
                if (passwordStr.length()>10||passwordStr.length()<6){
                    toast("密码为6-10位!");
                    return;
                }
                if (!passwordStr.equals(password1Str)){
                    toast("两次密码不一致!");
                    return;
                }
                if ("".equals(authCodeStr)){
                    toast("请输入验证码!");
                    return;
                }
                //比较验证码
                if (!authCodeStr.equals(SystemAuthCode)){
                    toast("验证码错误!");
                    return;
                }
                if ("".equals(schoolStr)){
                    toast("请输入学校名称!");
                    return;
                }
                if ("".equals(field1Str)){
                    toast("请输入姓名!");

                }
                if ("".equals(field2Str))
                    if (power==1){
                        toast("请输入学号!");
                        return;
                    }else{
                        toast("请输入工号!");
                        return;
                    }
                loadingDialog.show();
                userServer.register(usernameStr, password1Str, power, schoolStr,field1Str, field2Str, new myCallBack() {
                    @Override
                    public void onSuccess(Object object) {
                        Bundle bundle = (Bundle)object;
                        loadingDialog.dismiss();
                        System.out.println(bundle.getString("state"));
                        if ("注册成功".equals(bundle.getString("state"))){
                            toast("注册成功!");
                            finish();
                        }else{
                            toast(bundle.getString("state"));
                        }
                    }

                    @Override
                    public void onFailure(Object object) {
                        loadingDialog.dismiss();
                        toast((String)object);
                    }
                });

            }
        });
    }

    private void toast(String value){
        Toast.makeText(RegisterActivity.this,value,Toast.LENGTH_SHORT).show();
    }
    private void init() {
        school = findViewById(R.id.school);
        field_1 = findViewById(R.id.field_1);
        field_2 = findViewById(R.id.field_2);

        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        password1 = findViewById(R.id.password1);
        authCode = findViewById(R.id.authCode);
        getAuthCode = findViewById(R.id.getAuthCode);
        register = findViewById(R.id.register);
        userServer = new UserServer(GrpcChannel.getChannel());
        loadingDialog = new LoadingDialog(this);
        normalDialog = new NormalDialog(this);
    }

}
