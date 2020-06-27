package lab.jlhgxu520.equipment.server;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

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
import lab.jlhgxu520.equipment.po.EquipmentData;
import lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateRequest;
import lab.jlhgxy520.equipment.rpc.proto.student.EquipmentStateResponse;
import lab.jlhgxy520.equipment.rpc.proto.student.StudentRpcServerGrpc;
import lab.jlhgxy520.equipment.rpc.proto.student.addClassRequest;
import lab.jlhgxy520.equipment.rpc.proto.student.addClassResponse;
import lab.jlhgxy520.equipment.rpc.proto.student.instructExperimentRequest;
import lab.jlhgxy520.equipment.rpc.proto.student.instructExperimentResponse;
import lab.jlhgxy520.equipment.rpc.proto.student.realTimeRequest;
import lab.jlhgxy520.equipment.rpc.proto.student.realTimeResponse;
import lab.jlhgxy520.equipment.rpc.proto.student.studentStateRequest;
import lab.jlhgxy520.equipment.rpc.proto.student.studentStateResponse;

public class StudentServer {
    private StudentRpcServerGrpc.StudentRpcServerFutureStub futureStub;
    private StudentRpcServerGrpc.StudentRpcServerBlockingStub blockingStub;
    public StudentServer(ManagedChannel channel){
        futureStub = StudentRpcServerGrpc.newFutureStub(channel);
        blockingStub = StudentRpcServerGrpc.newBlockingStub(channel);
    }
    public EquipmentStateResponse getEquipmentState(String equipment){
        return blockingStub.getEquipmentState(EquipmentStateRequest.newBuilder().setEquipmentId(equipment).build());
    }
    public List<EquipmentData> realTimeData(long startTime, String equipment_key, String student_id){
        realTimeRequest request = realTimeRequest.newBuilder()
                .setEquipmentKey(equipment_key)
                .setStudentId(student_id)
                .setStartTime(startTime)
                .build();
        Iterator<realTimeResponse> iterator = null;
        try {
            iterator = blockingStub.realTimeData(request);
        }catch (Exception e){
            return null;
        }
        List<EquipmentData> list = new ArrayList<>();
        while (iterator.hasNext()){
            realTimeResponse response = iterator.next();
            if (response.getState() == realTimeResponse.State.FAILED)
                return null;
            EquipmentData bean = new EquipmentData();
            bean.setTime(response.getTime());
            bean.setRotate(response.getRotate());
            bean.setCore_temper(response.getCoreTemper());
            bean.setExter_temper(response.getExterTemper());
            bean.setStart_time(response.getStartTime());
            list.add(bean);
        }
        return list;
    }

    public void instruct(instructExperimentRequest.Instruct instruct, String equipment_key, String student_id,String strInstruct, final myCallBack callBack) {
        instructExperimentRequest request = instructExperimentRequest.newBuilder()
                .setEquipmentKey(equipment_key)
                .setStudentId(student_id)
                .setInstruct(instruct)
                .setStrInstruct(strInstruct)
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
                        callBack.onFailure("设备链接已断开！");
                        break;
                }
            }
        };
        ListenableFuture<instructExperimentResponse> future = futureStub.instructExperiment(request);
        Futures.addCallback(future, new FutureCallback<instructExperimentResponse>() {
            @Override
            public void onSuccess(@NullableDecl instructExperimentResponse result) {
                if (result.getState()==instructExperimentResponse.State.FAILED){
                    onFailure(null);
                    return;
                }
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }

            @Override
            public void onFailure(Throwable t) {
                Message message = new Message();
                message.what = 2;
                handler.sendMessage(message);
            }
        }, Executors.newCachedThreadPool());
    }

    public void addClass(String class_code,String student_id,final myCallBack callBack){
        addClassRequest request = addClassRequest.newBuilder()
                .setClassCode(class_code)
                .setStudentId(student_id)
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
                        callBack.onFailure("网络异常！");
                        break;
                }
            }
        };
        ListenableFuture<addClassResponse> future = futureStub.addClass(request);
        Futures.addCallback(future, new FutureCallback<addClassResponse>() {
            @Override
            public void onSuccess(@NullableDecl addClassResponse result) {
                Message message = new Message();
                Bundle bundle = new Bundle();
                message.what = 1;
                String state = result.getState();
                bundle.putString("state",state);//加入成功,房间人数已满
                if ("加入成功".equals(state)){
                    bundle.putString("class_id",result.getClassId());
                    bundle.putString("equipment_id",result.getEquipmentId());
                    bundle.putString("equipment_number",result.getEquipmentNumber());
                }
                message.setData(bundle);
                handler.sendMessage(message);
            }

            @Override
            public void onFailure(Throwable t) {
                Message message = new Message();
                message.what = 2;
                handler.sendMessage(message);
            }
        }, Executors.newCachedThreadPool());
    }

    public void getClassState(String student_id,final myCallBack callBack){
        studentStateRequest request = studentStateRequest.newBuilder().setStudentId(student_id).build();
        @SuppressLint("HandlerLeak") final Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        callBack.onSuccess(msg.getData());
                        break;
                    case 2:
                        callBack.onFailure("网络异常！");
                        break;
                }
            }
        };
        ListenableFuture<studentStateResponse> future = futureStub.studentClassState(request);
        Futures.addCallback(future, new FutureCallback<studentStateResponse>() {
            @Override
            public void onSuccess(@NullableDecl studentStateResponse result) {
                Message message = new Message();
                Bundle bundle = new Bundle();
                message.what = 1;
                String state = result.getState();
                bundle.putString("state",state);//加入课堂,回到课堂
                if ("回到课堂".equals(state)){
                    bundle.putString("class_id",result.getClassId());//创建课堂,回到课堂
                    bundle.putString("equipment_key",result.getEquipmentKey());//创建课堂,回到课堂
                    bundle.putString("equipment_number",result.getEquipmentNumber());
                }
                message.setData(bundle);
                handler.sendMessage(message);
            }

            @Override
            public void onFailure(Throwable t) {
                Message message = new Message();
                message.what = 2;
                handler.sendMessage(message);
            }
        }, Executors.newCachedThreadPool());
    }
}
