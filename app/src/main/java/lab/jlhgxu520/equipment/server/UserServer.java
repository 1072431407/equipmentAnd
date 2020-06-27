package lab.jlhgxu520.equipment.server;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.ByteString;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import io.grpc.ManagedChannel;
import lab.jlhgxu520.equipment.interfaces.myCallBack;
import lab.jlhgxy520.equipment.rpc.proto.user.UserRpcServerGrpc;
import lab.jlhgxy520.equipment.rpc.proto.user.loginRequest;
import lab.jlhgxy520.equipment.rpc.proto.user.loginResponse;
import lab.jlhgxy520.equipment.rpc.proto.user.registerRequest;
import lab.jlhgxy520.equipment.rpc.proto.user.registerResponse;
import lab.jlhgxy520.equipment.rpc.proto.user.upMessageRequest;
import lab.jlhgxy520.equipment.rpc.proto.user.upMessageResponse;
import lab.jlhgxy520.equipment.rpc.proto.user.upPasswordRequest;
import lab.jlhgxy520.equipment.rpc.proto.user.upPasswordResponse;


public class UserServer {
    private UserRpcServerGrpc.UserRpcServerFutureStub futureStub;
    public UserServer(ManagedChannel channel){
        futureStub = UserRpcServerGrpc.newFutureStub(channel);
    }
    public void upMessage(String uuid,String school,String filed1,String filed2,final myCallBack callBack){
        upMessageRequest request = upMessageRequest.newBuilder()
                .setUuid(uuid)
                .setSchool(school)
                .setFiled1(filed1)
                .setFiled2(filed2)
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
        ListenableFuture<upMessageResponse> future = futureStub.upMessage(request);
        Futures.addCallback(future, new FutureCallback<upMessageResponse>() {
            @Override
            public void onSuccess(@NullableDecl upMessageResponse result) {
                Message message = new Message();
                Bundle bundle = new Bundle();
                message.what = 1;
                bundle.putString("state",result.getState());
                message.setData(bundle);
                handler.sendMessage(message);
            }

            @Override
            public void onFailure(Throwable t) {
                Message message = new Message();
                message.what = 2;
                handler.sendMessage(message);
            }
        },Executors.newCachedThreadPool());
    }

    public void login(String username, String password, final myCallBack callBack){
        if ("".equals(username)||username == null||"".equals(password)||password == null)
            throw new NullPointerException("user and password no null！");
        final loginRequest request = loginRequest.newBuilder()
                .setUsername(username)
                .setPassword(password)
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
        ListenableFuture<loginResponse> future = futureStub.login(request);
        Futures.addCallback(future, new FutureCallback<loginResponse>() {
            @Override
            public void onSuccess(@NullableDecl loginResponse result) {
                Message message = new Message();
                message.what = 1;
                Bundle bundle = new Bundle();
                bundle.putString("state",result.getState());
                if("登录成功".equals(result.getState())){
                    bundle.putString("uuid",result.getUuid());
                    bundle.putInt("power",result.getPower());
                    bundle.putString("school",result.getSchool());
                    bundle.putString("field1",result.getFiled1());
                    bundle.putString("field2",result.getFiled2());
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

    public void register(String username,String password, int power, String school, String field1,String field2,final myCallBack callBack){
        final registerRequest request = registerRequest.newBuilder()
                .setUsername(username)
                .setPassword(password)
                .setPower(power)
                .setSchool(school)
                .setFiled1(field1)
                .setFiled2(field2)
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
        ListenableFuture<registerResponse> future = futureStub.register(request);
        Futures.addCallback(future, new FutureCallback<registerResponse>() {
            @Override
            public void onSuccess(@NullableDecl registerResponse result) {
                Message message = new Message();
                Bundle bundle = new Bundle();
                message.what = 1;
                bundle.putString("state",result.getState());
                message.setData(bundle);
                handler.sendMessage(message);
            }

            @Override
            public void onFailure(Throwable t) {
                Message message = new Message();
                message.what = 2;
                handler.sendMessage(message);
            }
        },Executors.newCachedThreadPool());
    }

    public void forgetPassword(String username,String password,final myCallBack callBack){
        if ("".equals(username)||username == null)
            throw new NullPointerException("username no null！");
        if ("".equals(password)||password == null)
            throw new NullPointerException("password no null！");
        final upPasswordRequest request = upPasswordRequest.newBuilder()
                .setUsername(username)
                .setPassword(password)
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

        ListenableFuture<upPasswordResponse> future = futureStub.upPassword(request);
        Futures.addCallback(future, new FutureCallback<upPasswordResponse>() {
            @Override
            public void onSuccess(@NullableDecl upPasswordResponse result) {
                Message message = new Message();
                Bundle bundle = new Bundle();
                message.what = 1;
                bundle.putString("state",result.getState());
                message.setData(bundle);
                handler.sendMessage(message);
            }
            @Override
            public void onFailure(Throwable t) {
                Message message = new Message();
                message.what = 2;
                handler.sendMessage(message);
            }
        },Executors.newCachedThreadPool());
    }
}
