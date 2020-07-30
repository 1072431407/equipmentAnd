package lab.jlhgxu520.equipment.server;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;

import io.grpc.ManagedChannel;
import lab.jlhgxu520.equipment.interfaces.myCallBack;
import lab.jlhgxu520.equipment.po.AdminTeacherBean;
import lab.jlhgxu520.equipment.po.Equipment;
import lab.jlhgxu520.equipment.po.Laboratory;
import lab.jlhgxy520.equipment.rpc.proto.teacher.*;

public class TeacherServer {
    private TeacherRpcServerGrpc.TeacherRpcServerBlockingStub blockingStub;
    public TeacherServer(ManagedChannel channel){
        blockingStub = TeacherRpcServerGrpc.newBlockingStub(channel);
    }


    public void attendClass(String class_id,final myCallBack callBack){
        final attendClassRequest request = attendClassRequest.newBuilder()
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
                        callBack.onFailure("开课失败!");
                        break;
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    attendClassResponse response = blockingStub.attendClass(request);
                    if (response.getState() == State.SUCCEED){
                        Message message = new Message();
                        message.what = 1;
                        Bundle bundle = new Bundle();
                        bundle.putString("class_code", response.getClassCode());
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
    public void getLaboratoryList(String teacher_id,final myCallBack callBack) {
        final getLaboratoryRequest request = getLaboratoryRequest.newBuilder().setTeacherId(teacher_id).build();
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
                try {
                    Iterator<getLaboratoryResponse> item = blockingStub.getLaboratoryList(request);
                    List<Laboratory> listBeans = new ArrayList<>();
                    while (item.hasNext()) {
                        getLaboratoryResponse response = item.next();
                        if (response.getState()==State.FAILED){
                            Message message = new Message();
                            message.what = 2;
                            handler.sendMessage(message);
                            return;
                        }
                        Laboratory bean = new Laboratory();
                        bean.setClass_id(response.getClassId());
                        bean.setClass_number(response.getClassNumber());
                        bean.setEquipment_number(Integer.parseInt(response.getEquipmentNumber()));
                        if (response.getClassState() == getLaboratoryResponse.ClassState.ATTEND){
                            bean.setState(1);
                            bean.setClass_code(response.getClassCode());
                        }else
                            bean.setState(0);

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
                }catch (Exception e){
                    Message message = new Message();
                    message.what = 2;
                    handler.sendMessage(message);
                }
            }
        }).start();
    }
    public void getEquipmentList(String class_id,final myCallBack callBack) {
        final getEquipmentRequest request = getEquipmentRequest.newBuilder().setClassId(class_id).build();
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
                        callBack.onFailure("该实验室当前没有设备!");
                        break;
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Iterator<getEquipmentResponse> item = blockingStub.getEquipmentList(request);
                    List<Equipment> listBeans = new ArrayList<>();
                    while (item.hasNext()) {
                        getEquipmentResponse response = item.next();
                        if (response.getState()==State.FAILED){
                            Message message = new Message();
                            message.what = 2;
                            handler.sendMessage(message);
                            return;
                        }
                        Equipment bean = new Equipment();
                        bean.setEquipment_id(response.getEquipmentId());
                        bean.setNumber(response.getNumber());
                        bean.setState(response.getEquipmentState());
                        bean.setStudent_id(response.getStudentId());
                        bean.setStudent_name(response.getStudentName());
                        bean.setCore_future(response.getCoreFuture());
                        bean.setExter_future(response.getExterFuture());
                        bean.setRotate_future(response.getRotateFuture());
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
                }catch (Exception e){
                    Message message = new Message();
                    message.what = 2;
                    handler.sendMessage(message);
                }
            }
        }).start();
    }

    public void finishClass(String class_id,final myCallBack callBack) {
        final finishClassRequest request = finishClassRequest.newBuilder()
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
                        callBack.onFailure("解散课堂失败!");
                        break;
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    finishClassResponse response = blockingStub.finishClass(request);
                    if (response.getState() == State.SUCCEED){
                        Message message = new Message();
                        message.what = 1;
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
}
