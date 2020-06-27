package lab.jlhgxu520.equipment.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import lab.jlhgxu520.equipment.R;
import lab.jlhgxu520.equipment.activitys.LoginActivity;

public class LoginPopListAdapter extends BaseAdapter {
    private LoginActivity activity;
    private List<ThisBean> listBeans;
    private SharedPreferences loginPreferences;

    public LoginPopListAdapter(LoginActivity activity){
        this.activity = activity;
        listBeans = new ArrayList<>();
        loginPreferences = activity.getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);
        listBeans = new ArrayList<>();
        int sum = loginPreferences.getInt("sum",0);
        for(int i=0; i<sum;i++){
            ThisBean bean = new ThisBean();
            bean.username = loginPreferences.getString("username"+i,"");
            bean.password = loginPreferences.getString("password"+i,"");
            bean.isBoolean = loginPreferences.getBoolean("isBoolean"+i,false);
            listBeans.add(bean);
        }
    }
    @Override
    public int getCount() {
        return listBeans.size();
    }

    @Override
    public ThisBean getItem(int position) {
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
            convertView = LayoutInflater.from(activity).inflate(R.layout.login_pop_list_item,parent,false);
            handler = new ViewHandler();
            handler.username = convertView.findViewById(R.id.userName);
            handler.delete = convertView.findViewById(R.id.delete);
            convertView.setTag(handler);
        } else {
            handler = (ViewHandler) convertView.getTag();
        }
        handler.username.setText(listBeans.get(position).username);
        final int item = position;
        handler.username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.setUserName(listBeans.get(item).username);
                activity.setPassword(listBeans.get(item).password);
                activity.setSavePassword(listBeans.get(item).isBoolean);
                activity.closePopupWindow();
            }
        });
        handler.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listBeans.remove(item);
                notifyDataSetChanged();
                SharedPreferences.Editor editor = loginPreferences.edit();
                editor.clear();
                for (int i =0;i<listBeans.size();i++){
                    editor.putString("username"+i,listBeans.get(i).username);
                    editor.putString("password"+i,listBeans.get(i).password);
                    editor.putBoolean("isBoolean"+i,listBeans.get(i).isBoolean);//headimage
                }
                editor.putInt("sum",listBeans.size());
                editor.apply();
            }
        });
        return convertView;
    }

    class ViewHandler {
        TextView username;
        ImageButton delete;
    }

    class ThisBean {
        String username = "";
        String password = "";
        boolean isBoolean = false;
    }
}
