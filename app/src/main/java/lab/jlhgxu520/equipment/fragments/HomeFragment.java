package lab.jlhgxu520.equipment.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lab.jlhgxu520.equipment.R;
import lab.jlhgxy520.equipment.rpc.proto.user.UserRpcServerGrpc;
import lab.jlhgxy520.equipment.rpc.proto.user.loginRequest;
import lab.jlhgxy520.equipment.rpc.proto.user.loginResponse;


public class HomeFragment extends Fragment {
    private View view;
    private Button button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("-------------------------------------------");
                ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("192.168.3.36",8282)
                        .usePlaintext().build();
                UserRpcServerGrpc.UserRpcServerBlockingStub stub = UserRpcServerGrpc.newBlockingStub(managedChannel);
                loginRequest request = loginRequest.newBuilder()
                        .setUsername("15104343050")
                        .setPassword("111111")
                        .setPower(1)
                        .build();
//        System.out.println(Arrays.toString(request.getRequestBytes().toByteArray()));
                loginResponse response = stub.login(request);
                System.out.println(response.getState());
            }
        });
        return view;
    }
}
