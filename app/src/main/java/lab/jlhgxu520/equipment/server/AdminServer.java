package lab.jlhgxu520.equipment.server;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.grpc.ManagedChannel;
import lab.jlhgxu520.equipment.interfaces.myCallBack;
import lab.jlhgxu520.equipment.po.AdminClassRoomBean;
import lab.jlhgxu520.equipment.po.AdminEquipmentBean;
import lab.jlhgxu520.equipment.po.AdminTeacherBean;
import lab.jlhgxy520.equipment.rpc.proto.admin.AdminRpcServerGrpc;

import lab.jlhgxy520.equipment.rpc.proto.admin.addClassRequest;
import lab.jlhgxy520.equipment.rpc.proto.admin.addClassResponse;
import lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentRequest;
import lab.jlhgxy520.equipment.rpc.proto.admin.addEquipmentResponse;
import lab.jlhgxy520.equipment.rpc.proto.admin.getClassRequest;
import lab.jlhgxy520.equipment.rpc.proto.admin.getClassResponse;
import lab.jlhgxy520.equipment.rpc.proto.admin.getEquipmentRequest;
import lab.jlhgxy520.equipment.rpc.proto.admin.getEquipmentResponse;
import lab.jlhgxy520.equipment.rpc.proto.admin.getTeacherRequest;
import lab.jlhgxy520.equipment.rpc.proto.admin.getTeacherResponse;
import lab.jlhgxy520.equipment.rpc.proto.admin.removeClassRequest;
import lab.jlhgxy520.equipment.rpc.proto.admin.removeClassResponse;
import lab.jlhgxy520.equipment.rpc.proto.admin.removeEquipmentRequest;
import lab.jlhgxy520.equipment.rpc.proto.admin.removeEquipmentResponse;
import lab.jlhgxy520.equipment.rpc.proto.admin.removeTeacherRequest;
import lab.jlhgxy520.equipment.rpc.proto.admin.removeTeacherResponse;

public class AdminServer {

    private AdminRpcServerGrpc.AdminRpcServerBlockingStub blockingStub;
    public AdminServer(ManagedChannel channel){
        blockingStub = AdminRpcServerGrpc.newBlockingStub(channel);
    }
    /*
    string class_id = 1;//UUID
    int64 time = 2;//注册时间
    string equipment_number = 3;//设备ID  用于绑定硬件 教室内部唯一标识
     */
    public void adminAddEquipment(String class_id,String equipment_number,final myCallBack callBack){
        final addEquipmentRequest request = addEquipmentRequest.newBuilder()
                .setClassId(class_id)
                .setEquipmentNumber(equipment_number)
                .setTime(System.currentTimeMillis())
                .build();
        @SuppressLint("HandlerLeak") final Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        callBack.onSuccess(msg.getData());
                        break;
                    case 2:
                        callBack.onFailure("网络异常!");
                        break;
                    case 3:
                        callBack.onFailure("添加失败!");
                        break;
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    addEquipmentResponse response = blockingStub.adminAddEquipment(request);
                    if (response.getState() == addEquipmentResponse.State.SUCCEED){
                        Message message = new Message();
                        message.what = 1;
                        Bundle bundle = new Bundle();
                        bundle.putString("class_id",response.getClassId());
                        bundle.putString("equipment_id",response.getEquipmentId());
                        bundle.putString("equipment_number",response.getEquipmentNumber());
                        bundle.putLong("time",response.getTime());
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }else {
                        Message message = new Message();
                        message.what = 3;
                        handler.sendMessage(message);
                    }
                }catch (Exception e){
                    Message message = new Message();
                    message.what = 2;
                    handler.sendMessage(message);
                }
            }
        }).start();

    }
    public void adminRemoveEquipment(String equipment_id,final myCallBack callBack){
        final removeEquipmentRequest request = removeEquipmentRequest.newBuilder()
                .setEquipmentId(equipment_id)
                .build();
        @SuppressLint("HandlerLeak") final Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        callBack.onSuccess(null);
                        break;
                    case 2:
                        callBack.onFailure("网络异常!");
                        break;
                    case 3:
                        callBack.onFailure(msg.getData().getString("state"));
                        break;
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    removeEquipmentResponse response = blockingStub.adminRemoveEquipment(request);
                    if (response.getState() == removeEquipmentResponse.State.SUCCEED){
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);
                    }else {
                        Message message = new Message();
                        message.what = 3;
                        Bundle bundle = new Bundle();
                        bundle.putString("state",response.getMessage());
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }
                }catch (Exception e){
                    Message message = new Message();
                    message.what = 2;
                    handler.sendMessage(message);
                }
            }
        }).start();

    }
    public void adminGetEquipmentList(String class_id,final myCallBack callBack){
        final getEquipmentRequest request = getEquipmentRequest.newBuilder()
                .setClassId(class_id)
                .build();
        @SuppressLint("HandlerLeak") final Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        callBack.onSuccess(msg.getData());
                        break;
                    case 2:
                        callBack.onFailure("网络异常!");
                        break;
                    case 3:
                        callBack.onFailure("当前没有设备!");
                        break;
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Iterator<getEquipmentResponse> iterator = blockingStub.adminGetEquipmentList(request);
                    List<AdminEquipmentBean> list = new ArrayList<>();
                    while (iterator.hasNext()){
                        getEquipmentResponse response = iterator.next();
                        if (response.getState() == getEquipmentResponse.State.SUCCEED){
                            AdminEquipmentBean bean = new AdminEquipmentBean();
                            bean.setRegister_time(response.getTime());
                            bean.setEquipment_id(response.getEquipmentId());
                            bean.setClass_id(response.getClassId());
                            bean.setEquipment_number(response.getEquipmentNumber());
                            list.add(bean);
                        }
                    }
                    if (list.size()==0){
                        Message message = new Message();
                        message.what = 3;
                        handler.sendMessage(message);
                    }else {
                        Message message = new Message();
                        message.what = 1;
                        Bundle bundle = new Bundle();
                        bundle.putString("jsonData", JSONObject.toJSONString(list));
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }
                }catch (Exception e){
                    Message message = new Message();
                    message.what = 2;
                    handler.sendMessage(message);
                }
            }
        }).start();
    }

    public void getTeacherList(final myCallBack callBack){
        @SuppressLint("HandlerLeak") final Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        callBack.onSuccess(msg.getData());
                        break;
                    case 2:
                        callBack.onFailure("网络异常!");
                        break;
                    case 3:
                        callBack.onFailure("当前没有教师!");
                        break;
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                Iterator<getTeacherResponse> item;
                try {
                    item = blockingStub.adminGetTeacherList(getTeacherRequest.newBuilder().build());
                }catch (Exception e){
                    Message message = new Message();
                    message.what = 2;
                    handler.sendMessage(message);
                    return;
                }
                List<AdminTeacherBean> listBeans = new ArrayList<>();
                while (item.hasNext()) {
                    getTeacherResponse response = item.next();
                    if (response.getState()==getTeacherResponse.State.FAILED){
                        Message message = new Message();
                        message.what = 2;
                        handler.sendMessage(message);
                        return;
                    }
                    AdminTeacherBean bean = new AdminTeacherBean();
                    bean.setClass_number(response.getClassNumber());
                    bean.setTeacher_id(response.getTeacherId());
                    bean.setName(response.getName());
                    bean.setNumber(response.getNumber());
                    bean.setSchool(response.getSchool());
                    listBeans.add(bean);
                }
                if (listBeans.size()==0){
                    Message message = new Message();
                    message.what = 3;
                    handler.sendMessage(message);

                }else {
                    Message message = new Message();
                    message.what = 1;
                    Bundle bundle = new Bundle();
                    bundle.putString("jsonData", JSONObject.toJSONString(listBeans));
                    message.setData(bundle);
                    handler.sendMessage(message);
                }
            }
        }).start();
    }
    public void removeTeacher(String teacher_id,final myCallBack callBack){
        final removeTeacherRequest request = removeTeacherRequest.newBuilder().setTeacherId(teacher_id).build();
        @SuppressLint("HandlerLeak") final Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        callBack.onSuccess(msg.getData());
                        break;
                    case 2:
                        callBack.onFailure("网络异常!");
                        break;
                    case 3:
                        callBack.onFailure(msg.getData().getString("state"));
                        break;
                }
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    removeTeacherResponse response = blockingStub.adminRemoveTeacher(request);
                    if (response.getState()==removeTeacherResponse.State.SUCCEED) {
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);
                    }else {
                        Message message = new Message();
                        message.what = 3;
                        Bundle bundle = new Bundle();
                        bundle.putString("state",response.getMessage());
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }
                }catch (Exception e){
                    Message message = new Message();
                    message.what = 2;
                    handler.sendMessage(message);
                }

            }
        }).start();

    }

    public void getClassRoomList(String teacher_id, final myCallBack callBack) {
        final getClassRequest request = getClassRequest.newBuilder()
                .setTeacherId(teacher_id)
                .build();
        @SuppressLint("HandlerLeak") final Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        callBack.onSuccess(msg.getData());
                        break;
                    case 2:
                        callBack.onFailure("网络异常!");
                        break;
                    case 3:
                        callBack.onFailure("当前没有课堂!");
                        break;
                }
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                Iterator<getClassResponse> iterator = null;
                try {
                    iterator = blockingStub.adminGetClassList(request);
                }catch (Exception e){
                    Message message = new Message();
                    message.what = 2;
                    handler.sendMessage(message);
                    return;
                }
                List<AdminClassRoomBean> listBeans = new ArrayList<>();
                while (iterator.hasNext()){
                    getClassResponse response = iterator.next();
                    if (response.getState() == getClassResponse.State.FAILED){
                        Message message = new Message();
                        message.what = 2;
                        handler.sendMessage(message);
                        return;
                    }

                    AdminClassRoomBean bean = new AdminClassRoomBean();
                    bean.setClass_number(response.getClassNumber());
                    bean.setClass_id(response.getClassId());
                    bean.setSchool(response.getSchool());
                    bean.setEquipment_number(response.getEquipmentNumber());
                    listBeans.add(bean);
                }
                if (listBeans.size()==0){
                    Message message = new Message();
                    message.what = 3;
                    handler.sendMessage(message);
                }else {
                    Message message = new Message();
                    message.what = 1;
                    Bundle bundle = new Bundle();
                    bundle.putString("jsonData", JSONObject.toJSONString(listBeans));
                    message.setData(bundle);
                    handler.sendMessage(message);
                }
            }
        }).start();
    }
    public void addClass(String teacher_id,String class_number, final myCallBack callBack) {
        final addClassRequest request = addClassRequest.newBuilder()
                .setTeacherId(teacher_id)
                .setClassNumber(class_number)
                .build();
        @SuppressLint("HandlerLeak") final Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        callBack.onSuccess(msg.getData());
                        break;
                    case 2:
                        callBack.onFailure("网络异常!");
                        break;
                    case 3:
                        callBack.onFailure("当前没有教师!");
                        break;
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    addClassResponse response = blockingStub.adminAddClass(request);
                    Message message = new Message();
                    message.what = 1;
                    Bundle bundle = new Bundle();
                    bundle.putString("class_id", response.getClassId());
                    bundle.putString("class_number", response.getClassNumber());
                    bundle.putString("equipment_number", response.getEquipmentNumber());
                    bundle.putString("school", response.getSchool());
                    message.setData(bundle);
                    handler.sendMessage(message);
                }catch (Exception e){
                    Message message = new Message();
                    message.what = 2;
                    handler.sendMessage(message);
                }
            }
        }).start();
    }
    public void removeClass(String class_id,final myCallBack callBack){
        final removeClassRequest request = removeClassRequest.newBuilder().setClassId(class_id).build();
        @SuppressLint("HandlerLeak") final Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        callBack.onSuccess(msg.getData());
                        break;
                    case 2:
                        callBack.onFailure("网络异常!");
                        break;
                    case 3:
                        callBack.onFailure(msg.getData().getString("state"));
                        break;
                }
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    removeClassResponse response = blockingStub.adminRemoveClass(request);
                    if (response.getState()==removeClassResponse.State.SUCCEED) {
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);
                    }else {
                        Message message = new Message();
                        message.what = 3;
                        Bundle bundle = new Bundle();
                        bundle.putString("state",response.getMessage());
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }
                }catch (Exception e){
                    Message message = new Message();
                    message.what = 2;
                    handler.sendMessage(message);
                }

            }
        }).start();
    }
}
