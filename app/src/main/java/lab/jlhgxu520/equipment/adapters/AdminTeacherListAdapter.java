package lab.jlhgxu520.equipment.adapters;

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
import lab.jlhgxu520.equipment.activitys.LaboratoryToEquipmentActivity;
import lab.jlhgxu520.equipment.activitys.TeacherToLaboratoryActivity;
import lab.jlhgxu520.equipment.interfaces.myCallBack;
import lab.jlhgxu520.equipment.po.AdminClassRoomBean;
import lab.jlhgxu520.equipment.server.AdminServer;

public class AdminTeacherListAdapter extends BaseAdapter {
    private List<AdminClassRoomBean> listBeans;
    private TeacherToLaboratoryActivity context;
    private AdminServer adminServer;

    public AdminTeacherListAdapter(TeacherToLaboratoryActivity context, List<AdminClassRoomBean> listBeans, AdminServer adminServer){
        this.context = context;
        this.listBeans = listBeans;
        this.adminServer = adminServer;
    }


    @Override
    public int getCount() {
        return listBeans.size();
    }

    @Override
    public AdminClassRoomBean getItem(int position) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.admin_teacher_list_item,parent,false);
            handler = new ViewHandler();
            handler.school = convertView.findViewById(R.id.school);
            handler.class_number = convertView.findViewById(R.id.class_number);
            handler.delete = convertView.findViewById(R.id.delete);
            convertView.setTag(handler);
        }else
            handler = (ViewHandler) convertView.getTag();



        handler.school.setText(listBeans.get(position).getSchool());
        handler.class_number.setText(listBeans.get(position).getClass_number());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LaboratoryToEquipmentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("class_id",listBeans.get(position).getClass_id());
                bundle.putString("class_number",listBeans.get(position).getClass_number());
                bundle.putString("equipment_number",listBeans.get(position).getEquipment_number());
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
                adminServer.removeClass(listBeans.get(position).getClass_id(), new myCallBack() {
                    @Override
                    public void onSuccess(Object object) {
                        listBeans.remove(position);
                        notifyDataSetChanged();
                        context.remove();
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
        TextView class_number;
        TextView school;//注册时间:2016-06-14
        ImageView delete;
    }

}
