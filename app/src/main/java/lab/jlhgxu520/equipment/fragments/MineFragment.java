package lab.jlhgxu520.equipment.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import lab.jlhgxu520.equipment.R;
import lab.jlhgxu520.equipment.activitys.LoginActivity;
import lab.jlhgxu520.equipment.interfaces.myCallBack;
import lab.jlhgxu520.equipment.server.GrpcChannel;
import lab.jlhgxu520.equipment.server.UserServer;
import lab.jlhgxu520.equipment.tools.UserCache;

public class MineFragment extends Fragment {
    private View view;
    private SharedPreferences preferences;
    private TextView name;
    private TextView number;
    private EditText schoolContent;
    private EditText field_1Content;
    private EditText field_2Content;
    private Button alter;
    private TextView power;
    private int isAlter;
    private Button outLogin;
    private UserServer userServer;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mine_fragment,container,false);
        init();
        initData();
        addListener();
        return view;
    }

    private void initData() {
        isAlter = -1;
        if (UserCache.power==1){
            power.setText("学生");
            name.setText("姓名:");
            number.setText("学号:");
        }else{
            power.setText("教师");
            name.setText("姓名:");
            number.setText("工号:");
        }
        schoolContent.setText(UserCache.school);
        field_1Content.setText(UserCache.name);
        field_2Content.setText(UserCache.number);
        schoolContent.setEnabled(false);
        field_1Content.setEnabled(false);
        field_2Content.setEnabled(false);
    }

    private void addListener() {
        outLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        alter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAlter *= -1;
                if (isAlter==1){//修改
                    alter.setText("保存");
                    schoolContent.setEnabled(true);
                    field_1Content.setEnabled(true);
                    field_2Content.setEnabled(true);
                }else{
                    alter.setText("修改");
                    schoolContent.setEnabled(false);
                    field_1Content.setEnabled(false);
                    field_2Content.setEnabled(false);
                    final String schoolStr = schoolContent.getText().toString();
                    final String field_1Str = field_1Content.getText().toString();
                    final String field_2Str = field_2Content.getText().toString();
                    if ("".equals(schoolStr)){
                        Toast.makeText(getContext(),"请正确输入学校",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if ("".equals(field_1Content)){
                        Toast.makeText(getContext(),"请正确输入"+ name.getText().toString(),Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if ("".equals(field_2Content)){
                        Toast.makeText(getContext(),"请正确输入"+ number.getText().toString(),Toast.LENGTH_SHORT).show();
                        return;
                    }
                    userServer.upMessage(UserCache.UUID, schoolStr, field_1Str, field_2Str, new myCallBack() {
                        @Override
                        public void onSuccess(Object object) {
                            Bundle bundle = (Bundle)object;
                            String state = bundle.getString("state");
                            if ("保存成功".equals(state)){
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("school",schoolStr)
                                        .putString("field1",field_1Str)
                                        .putString("field2",field_2Str)
                                        .commit();
                                UserCache.school = schoolStr;
                                UserCache.name = field_1Str;
                                UserCache.number = field_2Str;
                            }else
                                onFailure("保存失败！");
                        }

                        @Override
                        public void onFailure(Object object) {
                            String state = (String)object;
                            Toast.makeText(getContext(),state,Toast.LENGTH_SHORT).show();;
                            schoolContent.setText(UserCache.school);
                            field_1Content.setText(UserCache.name);
                            field_2Content.setText(UserCache.number);
                        }
                    });
                }
            }
        });
    }

    private void init() {
        userServer = new UserServer(GrpcChannel.getChannel());
        preferences = getActivity().getSharedPreferences("equipment", Context.MODE_PRIVATE);
        name = view.findViewById(R.id.field_1);
        number = view.findViewById(R.id.field_2);
        schoolContent = view.findViewById(R.id.schoolContent);
        field_1Content = view.findViewById(R.id.field_1Content);
        field_2Content = view.findViewById(R.id.field_2Content);
        alter = view.findViewById(R.id.alter);
        power = view.findViewById(R.id.power);
        outLogin = view.findViewById(R.id.outLogin);
    }


}
