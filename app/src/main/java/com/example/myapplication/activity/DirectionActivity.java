package com.example.myapplication.activity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DirectionActivity extends BaseActivity implements SensorEventListener {

    @BindView(R.id.iv_show)
    ImageView ivShow;
    @BindView(R.id.iv_return)
    ImageView ivReturn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    //记录指南针图片转过的角度
    private float currentDegree = 0f;
    //定义Sensor管理器
    SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        ivReturn.setImageDrawable(getResources().getDrawable(R.drawable.chuzuxiangqing));
        tvTitle.setText("指南针");
        //获取传感器管理服务
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //为系统的方向传感器注册监听器
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //取消注册
        mSensorManager.unregisterListener(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        //取消注册
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //获取触发sensorEvent的传感器类型
        int sensorType = sensorEvent.sensor.getType();
        if (sensorType == Sensor.TYPE_ORIENTATION) {
            //获取Z轴转过的角度
            float degree = sensorEvent.values[0];
            //创建旋转动画
            RotateAnimation ra = new RotateAnimation(currentDegree, -degree, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            //设置动画的持续时间
            ra.setDuration(200);
            //运行动画
            ivShow.startAnimation(ra);
            currentDegree = -degree;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @OnClick(R.id.iv_return)
    public void onViewClicked() {
        finish();
    }
}
