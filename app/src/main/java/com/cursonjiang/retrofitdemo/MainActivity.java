package com.cursonjiang.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cursonjiang.retrofitdemo.API.gitapi;
import com.cursonjiang.retrofitdemo.model.gitmodel;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private Button mButton;
    private TextView mTextView;
    private EditText mEditText;
    private ProgressBar mProgressBar;

    String API = "https://api.github.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.button);
        mTextView = (TextView) findViewById(R.id.tv);
        mEditText = (EditText) findViewById(R.id.edit);
        mProgressBar = (ProgressBar) findViewById(R.id.pb);

        mButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String user = mEditText.getText().toString();
                        mProgressBar.setVisibility(View.VISIBLE);

                        //设置地址并构建一个RestAdapter对象
                        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API).build();
                        //创建一个服务适配器
                        gitapi gitapi = restAdapter.create(com.cursonjiang.retrofitdemo.API.gitapi.class);
                        gitapi.getFeed(
                                user,
                                new Callback<gitmodel>() {
                                    //请求成功
                                    @Override
                                    public void success(gitmodel gitmodel, Response response) {
                                        Toast.makeText(MainActivity.this, "url:" + response.getUrl(), Toast.LENGTH_LONG).show();
                                        mTextView.setText(
                                                "Github Name:" + gitmodel.getName()
                                                        + "\nWebsite:" + gitmodel.getBlog()
                                                        + "\nCompany Name:" + gitmodel.getCompany()
                                        );
                                        mProgressBar.setVisibility(View.INVISIBLE);
                                    }

                                    //请求失败
                                    @Override
                                    public void failure(RetrofitError error) {
                                        mTextView.setText(error.getMessage());
                                        mProgressBar.setVisibility(View.INVISIBLE);
                                    }
                                }
                        );
                    }
                }
        );
    }
}
