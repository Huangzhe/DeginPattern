package com.sh.lynn.hz.developbox.netframe.volleyutil;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.sh.lynn.hz.developbox.R;
import com.sh.lynn.hz.developbox.event.YYPhotoEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VolleyDemoActivity extends AppCompatActivity {
    @BindView(R.id.tv_req_result)
    TextView tv_response;
    @BindView(R.id.iv_imageLoader)
    ImageView iv_photo;
    private  String NET_YYJOY = " http://route.showapi.com/341-2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_demo);
        ButterKnife.bind(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.btn_request_volley)
    public void onClickRequest() {
        try {
            URL url = new URL(NET_YYJOY);
            HttpURLConnection urlConnection =(HttpURLConnection)url.openConnection();


        } catch (Exception e) {
            e.printStackTrace();
        }



        NetUtils.getPhoto("1");
//        JSONObject jsonObject = new JSONObject();
//        NET_YYJOY=  NET_YYJOY+"?showapi_appid=30978&showapi_sign=7a1b389d30e547aea07908aaf8826d9b";
//        try {
////            jsonObject.put("showapi_appid", "30978");
////            jsonObject.put("showapi_sign", "7a1b389d30e547aea07908aaf8826d9b");
//            jsonObject.put("maxResult", "1");
//            jsonObject.put("page", "10");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, NET_YYJOY, jsonObject, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Log.e("JsonObjectRequest", response.toString());
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("JsonObjectRequest", error.toString());
//            }
//
//        }
//        )
//        {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String,String> hashMap = new HashMap<>();
////                hashMap.put("showapi_appid", "30978");
////                hashMap.put("showapi_sign", "7a1b389d30e547aea07908aaf8826d9b");
//                return hashMap;
//            }
//        };
//
//        Volley.newRequestQueue(this).add(request);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(YYPhotoEvent event) {
        String result = event.getMsg().toString();
    }

    ;
}
