package lab.jlhgxu520.equipment.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import lab.jlhgxu520.equipment.R;
import lab.jlhgxu520.equipment.activitys.StudentLaboratoryActivity;
import lab.jlhgxu520.equipment.po.Equipment;

public class LaboratoryGridAdapter extends BaseAdapter {
    private Context context;
    private List<Equipment> listBeans;
    public LaboratoryGridAdapter(Context context,List<Equipment> listBeans){
        this.context = context;
        this.listBeans = listBeans;
    }
    @Override
    public int getCount() {
        return listBeans.size();
    }

    @Override
    public Equipment getItem(int position) {
        return listBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHandler handler;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.laboratory_grid_item,parent,false);
            handler = new ViewHandler();
            handler.state = convertView.findViewById(R.id.state);
            handler.number = convertView.findViewById(R.id.number);
            handler.student_name = convertView.findViewById(R.id.student_name);
            handler.core_future = convertView.findViewById(R.id.core_future);
            handler.exter_future = convertView.findViewById(R.id.exter_future);
            handler.rotate_future = convertView.findViewById(R.id.rotate_future);
            convertView.setTag(handler);
        }else
            handler = (ViewHandler) convertView.getTag();

        if (listBeans.get(position).getStudent_id() !=null && !"".equals(listBeans.get(position).getStudent_id())) {
            DecimalFormat df = new DecimalFormat("######0.00");
            handler.student_name.setText(listBeans.get(position).getStudent_name());
            handler.core_future.setText("内温:"+df.format(listBeans.get(position).getCore_future()));
            handler.exter_future.setText("外温:"+df.format(listBeans.get(position).getExter_future()));
            handler.rotate_future.setText("转速:"+df.format(listBeans.get(position).getRotate_future()));
        }else
            handler.student_name.setText("未使用");

        if (listBeans.get(position).getState()==0){
            handler.state.setText("断线");
            handler.student_name.setText("未使用");
        }else{
            handler.state.setText("");
        }
        handler.number.setText(listBeans.get(position).getNumber());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StudentLaboratoryActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("equipment_id",listBeans.get(position).getEquipment_id());
                bundle.putString("equipment_number",listBeans.get(position).getNumber());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        return convertView;
    }
    class ViewHandler{
        TextView state;
        TextView number;
        TextView student_name;
        TextView core_future;
        TextView exter_future;
        TextView rotate_future;
    }
}
