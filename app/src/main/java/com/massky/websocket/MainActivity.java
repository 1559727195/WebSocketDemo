package com.massky.websocket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.google.gson.Gson;
import com.massky.wsface.service.WsManagerService;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, WsManagerService.ConnectFaceWsLisenter {
    private static final String DEF_TEST_URL = "ws://192.168.169.220:8080/Mysql/myHandler";
    private static final String DEF_RELEASE_URL = "ws://masskyface.massky.com:22765";
    private static String DEF_URL = "ws://192.168.169.220:8080/Mysql/myHandler";
    /**
     * 服务器推送到设备需用FacePushReceiver自行过滤
     */
    private Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WsManagerService.getInstance().setUrl(DEF_URL);//设置连接
        WsManagerService.getInstance().init(this, WsApplication.getInstance());//建立连接
        btn_send = (Button) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WsManagerService.getInstance().disconnect();//断开连接

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                Map map = new HashMap();
                map.put("cmd", "beat");
                //示例代码，仅供参考，
                WsManagerService.getInstance().send(new Gson().toJson(map));
                break;
        }
    }

    /**
     * 连接Websocket成功
     */
    @Override
    public void connected() {

    }

    /**
     * 连接Websocket失败
     */
    @Override
    public void connecterror() {

    }
}



