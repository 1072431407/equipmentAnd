package lab.jlhgxu520.equipment.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;
import com.xq.fasterdialog.dialog.LoadingDialog;
import java.util.ArrayList;
import java.util.List;

import lab.jlhgxu520.equipment.MainActivity;
import lab.jlhgxu520.equipment.R;
import lab.jlhgxu520.equipment.interfaces.myCallBack;
import lab.jlhgxu520.equipment.server.GrpcChannel;
import lab.jlhgxu520.equipment.server.UserServer;
import lab.jlhgxu520.equipment.views.CircleImageTransformer;
import lab.jlhgxu520.equipment.views.LoginPoUpWindow;

public class LoginActivity extends Activity {
    private ImageView headImage;
    private EditText userName;
    private EditText password;
    private ImageView spread;
    private CheckBox savePassword;
    private CheckBox autoLogin;
    private ImageButton login;
    private TextView forgetPassword;
    private TextView toRegister;
    private RelativeLayout spreadButton;

    private LoginPoUpWindow popupWindow;
    private int isSpread;

    private UserServer userServer;
    private SharedPreferences preferences;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor editor;
    private SharedPreferences.Editor loginEditor;

    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        initData();
        addListener();
    }

    private void initData() {
        if (preferences.getBoolean("autologin",false))
            close();
        loadingDialog.setLoadingText("登录中...");
        Picasso.with(this)
                .load(R.drawable.head_image)
                .transform(new CircleImageTransformer())
                .into(headImage);
        userName.setText(preferences.getString("username",""));
        password.setText(preferences.getString("password",""));
        savePassword.setChecked(preferences.getBoolean("savepassword",false));
        autoLogin.setChecked(preferences.getBoolean("autologin",false));
    }

    private void addListener() {
        spreadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTheme();
//                int screenWidth  = getWindowManager().getDefaultDisplay().getWidth();        // 屏幕宽（像素，如：480px）
//                int screenHeight = getWindowManager().getDefaultDisplay().getHeight();        // 屏幕高（像素，如：800p）
//                spread.getLocationInWindow(location); //获取在当前窗口内的绝对坐标，含toolBar
//        popupWindow.showAsDropDown(spread,0,0,Gravity.RIGHT);
                int[] location = new  int[2] ;
                spread.getLocationOnScreen(location); //获取在整个屏幕内的绝对坐标，含00statusBar
                popupWindow.showAtLocation(spread,Gravity.TOP|Gravity.START,location[0]-460,location[1]+34);
            }
        });

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTheme();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLogin();
            }
        });
        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("power",1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,ForgetPassActivity.class);
                startActivity(intent);
            }
        });
    }
/*
登陆方法
 */
    private void toLogin() {
        final String userString = userName.getText().toString();
        final String passwordString = password.getText().toString();
        if ("".equals(userString)) {
            Toast.makeText(LoginActivity.this,"请输入学号",Toast.LENGTH_SHORT).show();
            return;
        }
        System.out.println(userString.length());
        if (userString.length()<10 || userString.length()>20) {
            Toast.makeText(LoginActivity.this,"请正确输入学号",Toast.LENGTH_SHORT).show();
            return;
        }
        if ("".equals(passwordString)) {
            Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
            return;
        }
        if (passwordString.length()<6 || passwordString.length()>10) {
            Toast.makeText(LoginActivity.this,"密码为6-10位",Toast.LENGTH_SHORT).show();
            return;
        }
        loadingDialog.show();

  /*
        发送rpc请求，执行回调方法
         */
        userServer.login(userString, passwordString, new myCallBack() {
            @Override
            public void onSuccess(Object object) {
                Bundle bundle = (Bundle)object;
                /*
                登陆成功
                保存用户的登录信息
                 */
                if ("登录成功".equals(bundle.getString("state"))){

                    if (autoLogin.isChecked()){
                        editor.putString("username",userString)
                                .putString("password",passwordString)
                                .putBoolean("autologin",true)
                                .putBoolean("savepassword",true)
                                .apply();

                    } else {
                        if (savePassword.isChecked()){
                            editor.putString("username",userString)
                                    .putString("password",passwordString)
                                    .putBoolean("autologin",false)
                                    .putBoolean("savepassword",true)
                                    .apply();
                        }else{
                            editor.putString("username",userString)
                                    .putString("password","")
                                    .putBoolean("autologin",false)
                                    .putBoolean("savepassword",false)
                                    .apply();
                        }
                    }
                    /*
                    bundle.putString("uuid",result.getUuid());
                    bundle.putInt("power",result.getPower());
                    bundle.putString("school",result.getSchool());
                    bundle.putString("field1",result.getFiled1());
                    bundle.putString("field2",result.getFiled2());
                     */
                    editor.putString("uuid",bundle.getString("uuid"))
                            .putInt("power",bundle.getInt("power"))
                            .putString("school",bundle.getString("school"))
                            .putString("field1",bundle.getString("field1"))
                            .putString("field2",bundle.getString("field2"))
                            .commit();
                    /**
                     * 保存UUID等登录用户的数据到editor中
                     */
                    int sum = loginPreferences.getInt("sum",0);
                    int newSum = sum;
                    List<ThisBean> listBeans = new ArrayList<>();
                    for(int i=0; i<sum;i++){
                        ThisBean bean = new ThisBean();
                        bean.username = loginPreferences.getString("username"+i,"");
                        bean.password = loginPreferences.getString("password"+i,"");
                        bean.isBoolean = loginPreferences.getBoolean("isBoolean"+i,false);
                        if (bean.username.equals(userString)){
                            newSum--;
                            continue;
                        }else
                            listBeans.add(bean);
                    }
                    newSum++;
                    ThisBean bean = new ThisBean();
                    if (savePassword.isChecked())
                        bean.password = passwordString;
                    else
                        bean.password = "";
                    bean.isBoolean = savePassword.isChecked();
                    bean.username = userString;
                    listBeans.add(0,bean);
                    if (listBeans.size()>10)
                        listBeans.remove(10);
                    loginEditor.clear();
                    loginEditor.putInt("sum",newSum).apply();
                    for (int i = 0;i < newSum; i++){
                        ThisBean b = listBeans.get(i);
                        loginEditor.putBoolean("isBoolean"+i,b.isBoolean)
                                .putString("username"+i,b.username)
                                .putString("password"+i,b.password)
                                .apply();
                    }
                    loadingDialog.dismiss();
                    if (bundle.getInt("power")==3){
                        Intent intent = new Intent(LoginActivity.this,AdminActivity.class);
                        startActivity(intent);
                        finish();
                    }else if (bundle.getInt("power")==2){
                        Intent intent = new Intent(LoginActivity.this,TeacherActivity.class);
                        startActivity(intent);
                        finish();
                    }else
                        close();
                }else {
                    Toast.makeText(LoginActivity.this,bundle.getString("state"),Toast.LENGTH_SHORT).show();
                    loadingDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Object object) {
                Toast.makeText(LoginActivity.this,(String)object,Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
            }
        });

    }

    private void close(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void setTheme(){
        isSpread *= -1;
        if (isSpread == 1){
            spread.setImageResource(R.drawable.arr_up);
        } else {
            spread.setImageResource(R.drawable.arr_down);
        }
    }

    private void init() {
        isSpread = -1;
        headImage = findViewById(R.id.headImage);
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        spread = findViewById(R.id.spread);
        savePassword = findViewById(R.id.savePassword);
        autoLogin = findViewById(R.id.autoLogin);
        login = findViewById(R.id.login);
        forgetPassword = findViewById(R.id.forgetPassword);
        toRegister = findViewById(R.id.toRegister);
        spreadButton = findViewById(R.id.spreadButton);
        spread.setImageResource(R.drawable.arr_down);
        preferences = getSharedPreferences("equipment", Context.MODE_PRIVATE);
        loginPreferences = getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);

        popupWindow = new LoginPoUpWindow(LoginActivity.this);

        editor = preferences.edit();
        loginEditor = loginPreferences.edit();
        userServer = new UserServer(GrpcChannel.getChannel());

        loadingDialog = new LoadingDialog(this);

    }
    public void setUserName(String value){
        if(value != null)
            userName.setText(value);
    }

    public void setPassword(String value) {
        if(value != null)
            password.setText(value);
    }
    public void setSavePassword(boolean value) {
        savePassword.setChecked(value);
    }
    public void closePopupWindow() {
        if(popupWindow != null && popupWindow.isShowing()){
            popupWindow.dismiss();
            popupWindow = null;
            WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
            layoutParams.alpha = 1f;
            getWindow().setAttributes(layoutParams);
            popupWindow = new LoginPoUpWindow(LoginActivity.this);
        }
    }



    class ThisBean {
        String username = "";
        String password = "";
        boolean isBoolean = false;
    }

}
