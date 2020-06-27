package lab.jlhgxu520.equipment.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.xq.fasterdialog.bean.InputBean;
import com.xq.fasterdialog.dialog.EditDialog;
import com.xq.fasterdialog.dialog.LoadingDialog;
import com.xq.fasterdialog.dialog.NormalDialog;
import com.xq.fasterdialog.dialog.base.BaseDialog;

import lab.jlhgxu520.equipment.R;
import lab.jlhgxu520.equipment.activitys.StudentLaboratoryActivity;
import lab.jlhgxu520.equipment.interfaces.myCallBack;
import lab.jlhgxu520.equipment.interfaces.viewCallBack;
import lab.jlhgxu520.equipment.server.GrpcChannel;
import lab.jlhgxu520.equipment.server.StudentServer;
import lab.jlhgxu520.equipment.tools.UserCache;


/*
    课程
 */
public class CourseFragment extends Fragment {
    private View view;
    private TextView state;
    private LoadingDialog loadingDialog;

    private StudentServer studentServer;
    private String class_id;
    private String equipment_key;
    private String equipment_number;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.plan_fragment,container,false);
        init();
        initData();
        addListener();
        return view;
    }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode==1){
//            state.setText("创建课堂");
//            class_id = "";
//        }
//
//        if (resultCode==2){
//            state.setText("加入课堂");
//            class_id = "";
//        }
//    }

    private void addListener() {
        state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("加入课堂".equals(state.getText().toString())){
                    setEditDialog("请输入房间号:", "", new viewCallBack() {
                        @Override
                        public void callBack(final String value) {
                            loadingDialog.setLoadingText("正在加入房间...");
                            loadingDialog.show();
                            studentServer.addClass(value, UserCache.UUID, new myCallBack() {
                                @Override
                                public void onSuccess(Object object) {
                                    loadingDialog.dismiss();
                                    Bundle bundle = (Bundle)object;
                                    String state = bundle.getString("state");
                                    if ("加入成功".equals(state)){
                                        Intent intent = new Intent(getActivity(), StudentLaboratoryActivity.class);
                                        Bundle bundle1 = new Bundle();
                                        bundle1.putString("class_id",bundle.getString("class_id"));
                                        bundle1.putString("equipment_id",bundle.getString("equipment_id"));
                                        bundle1.putString("equipment_number",bundle.getString("equipment_number"));
                                        intent.putExtras(bundle1);
                                        startActivity(intent);
                                    }else
                                        Toast.makeText(getContext(),state,Toast.LENGTH_SHORT).show();
                                }
                                @Override
                                public void onFailure(Object object) {
                                    loadingDialog.dismiss();
                                    Toast.makeText(getContext(),(String)object,Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                if ("回到课堂".equals(state.getText().toString())){
                    Intent intent = new Intent(getActivity(), StudentLaboratoryActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("class_id",class_id);
                    bundle.putString("equipment_id",equipment_key);
                    bundle.putString("equipment_number",equipment_number);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }
    private void setEditDialog(String title, String hint, final viewCallBack callBack){
        final EditDialog editDialog = new EditDialog(getContext());
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
        loadingDialog.setLoadingText("正在创建...");
        getClassState();
    }

    @Override
    public void onStart() {
        getClassState();
        super.onStart();
    }


    private void getClassState() {
        final LoadingDialog loadingDialog = new LoadingDialog(getActivity());
        loadingDialog.setLoadingText("正在加载...");
        loadingDialog.show();
        studentServer.getClassState(UserCache.UUID, new myCallBack() {
            @Override
            public void onSuccess(Object object) {
                Bundle bundle = (Bundle)object;
                String stateStr = bundle.getString("state");
                state.setText(stateStr);
                if ("回到课堂".equals(stateStr)){
                    class_id = bundle.getString("class_id");
                    equipment_key = bundle.getString("equipment_key");
                    equipment_number = bundle.getString("equipment_number");
                }
                loadingDialog.dismiss();
            }
            @Override
            public void onFailure(Object object) {
                state.setText("加入课堂");
                loadingDialog.dismiss();
            }
        });
    }

    private void init() {
        state = view.findViewById(R.id.state);
        loadingDialog = new LoadingDialog(getContext());
        studentServer = new StudentServer(GrpcChannel.getChannel());
    }

}
