package com.example.plants.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.plants.R;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private Tencent tencent;  //sdk的入口
    private static final String APPID="101918718";
    private ImageView imageView;
    private Button button;
    private Listener listener; //实现回调
    private String name,figureurl;
    private UserInfo mInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView(){
        tencent=Tencent.createInstance(APPID,this.getApplicationContext());
        imageView=(ImageView)findViewById(R.id.img_qq);
        button=(Button) findViewById(R.id.btn_login);
        listener=new Listener();
        //点击QQ的图标进行登录
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "点击成功了", Toast.LENGTH_SHORT).show();
                Login();
            }
        });
    }

    public void Login(){
        if (!tencent.isSessionValid()){
            tencent.login(this,"all",listener);
        }
    }

    public class Listener implements IUiListener{

        @Override
        public void onComplete(Object o) {
            Toast.makeText(LoginActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
            getUserInfo();
            initOpenIdAndToken(o);
            Intent intent=new Intent();
        }

        @Override
        public void onError(UiError uiError) {
            Log.e(APPID,"登录失败"+uiError.toString());
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onWarning(int i) {

        }
    }

    private void initOpenIdAndToken(Object object) {
        JSONObject jb = (JSONObject) object;
        try {
            String openID = jb.getString("openid");  //openid用户唯一标识
            String access_token = jb.getString("access_token");
            String expires = jb.getString("expires_in");
            tencent.setOpenId(openID);
            tencent.setAccessToken(access_token, expires);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getUserInfo() {
        QQToken token = tencent.getQQToken();
        mInfo = new UserInfo(LoginActivity.this, token);
        mInfo.getUserInfo(new IUiListener() {
            @Override
            public void onComplete(Object object) {
                JSONObject jb = (JSONObject) object;
                try {
                    name = jb.getString("nickname");
                    figureurl = jb.getString("figureurl_qq_2");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(UiError uiError) {
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onWarning(int i) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        tencent.onActivityResultData(requestCode, resultCode, data, listener);
    }
}