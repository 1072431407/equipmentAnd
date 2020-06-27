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
import lab.jlhgxu520.equipment.activitys.LaboratoryActivity;
import lab.jlhgxu520.equipment.activitys.TeacherToLaboratoryActivity;
import lab.jlhgxu520.equipment.interfaces.myCallBack;
import lab.jlhgxu520.equipment.po.Laboratory;

public class TeacherListAdapter extends BaseAdapter {
    private Context context;
    private List<Laboratory> listBeans;
    public TeacherListAdapter(Context context,List<Laboratory> listBeans){
        this.context = context;
        this.listBeans = listBeans;
    }
    @Override
    public int getCount() {
        return listBeans.size();
    }

    @Override
    public Laboratory getItem(int position) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.teacher_list_item,parent,false);
            handler = new ViewHandler();
            handler.class_number = convertView.findViewById(R.id.class_number);
            handler.equipment_number = convertView.findViewById(R.id.equipment_number);
            convertView.setTag(handler);
        }else
            handler = (ViewHandler) convertView.getTag();

        handler.class_number.setText(listBeans.get(position).getClass_number());
        handler.equipment_number.setText("设备数量:"+listBeans.get(position).getEquipment_number());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LaboratoryActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("class_id",listBeans.get(position).getClass_id());
                bundle.putString("class_number",listBeans.get(position).getClass_number());
                bundle.putInt("equipment_number",listBeans.get(position).getEquipment_number());
                if (listBeans.get(position).getState()==0)
                    bundle.putInt("class_state",0);
                else {
                    bundle.putInt("class_state",1);
                    bundle.putString("class_code",listBeans.get(position).getClass_code());
                }
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        return convertView;
    }
    class ViewHandler{
        TextView class_number;
        TextView equipment_number;
    }


}
