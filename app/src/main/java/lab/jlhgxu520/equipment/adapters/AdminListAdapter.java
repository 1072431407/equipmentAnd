package lab.jlhgxu520.equipment.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xq.fasterdialog.dialog.LoadingDialog;

import java.util.List;

import lab.jlhgxu520.equipment.R;
import lab.jlhgxu520.equipment.activitys.TeacherToLaboratoryActivity;
import lab.jlhgxu520.equipment.interfaces.myCallBack;
import lab.jlhgxu520.equipment.po.AdminTeacherBean;
import lab.jlhgxu520.equipment.server.AdminServer;

public class AdminListAdapter extends BaseAdapter {
    private List<AdminTeacherBean> listBeans;
    private Context context;
    private AdminServer adminServer;
    public AdminListAdapter(Context context,List<AdminTeacherBean> listBeans,AdminServer adminServer){
        this.context = context;
        this.listBeans = listBeans;
        this.adminServer = adminServer;
    }
    @Override
    public int getCount() {
        return listBeans.size();
    }

    @Override
    public AdminTeacherBean getItem(int position) {
        return listBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHandler handler;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.admin_list_item,parent,false);
            handler = new ViewHandler();
            handler.name = convertView.findViewById(R.id.name);
            handler.school = convertView.findViewById(R.id.school);
            handler.delete = convertView.findViewById(R.id.delete);
            convertView.setTag(handler);
        }else
            handler = (ViewHandler) convertView.getTag();

        handler.name.setText(listBeans.get(position).getName());
        handler.school.setText(listBeans.get(position).getSchool());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TeacherToLaboratoryActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("teacher_id",listBeans.get(position).getTeacher_id());
                bundle.putString("number",listBeans.get(position).getNumber());
                bundle.putString("class_number",listBeans.get(position).getClass_number());
                bundle.putString("school",listBeans.get(position).getSchool());
                bundle.putString("name",listBeans.get(position).getName());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        handler.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LoadingDialog loadingDialog = new LoadingDialog(context);
                loadingDialog.setLoadingText("正在删除...");
                loadingDialog.show();
                adminServer.removeTeacher(listBeans.get(position).getTeacher_id(), new myCallBack() {
                    @Override
                    public void onSuccess(Object object) {
                        listBeans.remove(position);
                        notifyDataSetChanged();
                        loadingDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Object object) {
                        loadingDialog.dismiss();
                    }
                });
            }
        });
        return convertView;
    }
    class ViewHandler{
        TextView name;
        TextView school;
        ImageView delete;
    }
}
