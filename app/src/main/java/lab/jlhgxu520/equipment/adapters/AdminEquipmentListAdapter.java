package lab.jlhgxu520.equipment.adapters;

import android.content.Context;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xq.fasterdialog.dialog.LoadingDialog;

import java.util.Date;
import java.util.List;

import lab.jlhgxu520.equipment.R;
import lab.jlhgxu520.equipment.activitys.LaboratoryToEquipmentActivity;
import lab.jlhgxu520.equipment.interfaces.myCallBack;
import lab.jlhgxu520.equipment.po.AdminEquipmentBean;
import lab.jlhgxu520.equipment.server.AdminServer;

public class AdminEquipmentListAdapter extends BaseAdapter {
    private LaboratoryToEquipmentActivity context;
    private List<AdminEquipmentBean> listBeans;
    private AdminServer adminServer;
    public AdminEquipmentListAdapter(LaboratoryToEquipmentActivity context, List<AdminEquipmentBean> listBeans, AdminServer adminServer){
        this.context = context;
        this.listBeans = listBeans;
        this.adminServer = adminServer;
    }
    @Override
    public int getCount() {
        return listBeans.size();
    }

    @Override
    public AdminEquipmentBean getItem(int position) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.admin_equipment_list_item,parent,false);
            handler = new ViewHandler();
            handler.delete = convertView.findViewById(R.id.delete);
            handler.equipment_number = convertView.findViewById(R.id.equipment_number);
            handler.time = convertView.findViewById(R.id.time);
            convertView.setTag(handler);
        }else
            handler = (ViewHandler) convertView.getTag();
        handler.equipment_number.setText(listBeans.get(position).getEquipment_number());
        Date date = new Date(listBeans.get(position).getRegister_time());
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        handler.time.setText("注册时间:"+format.format(date));
        handler.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LoadingDialog loadingDialog = new LoadingDialog(context);
                loadingDialog.setLoadingText("正在删除...");
                loadingDialog.show();
                adminServer.adminRemoveEquipment(listBeans.get(position).getEquipment_id(), new myCallBack() {
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
        TextView equipment_number;
        TextView time;
        ImageButton delete;

    }
}
