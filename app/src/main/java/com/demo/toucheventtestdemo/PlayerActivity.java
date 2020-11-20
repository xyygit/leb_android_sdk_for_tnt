package com.demo.toucheventtestdemo;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.tencent.xbright.lebwebrtcsdk.LEBWebRTCEvents;
import com.tencent.xbright.lebwebrtcsdk.LEBWebRTCParameters;
import com.tencent.xbright.lebwebrtcsdk.LEBWebRTCStatsReport;
import com.tencent.xbright.lebwebrtcsdk.LEBWebRTCView;

import java.nio.ByteBuffer;

public class PlayerActivity extends AppCompatActivity implements LEBWebRTCEvents {
    private static final String TAG = PlayerActivity.class.getSimpleName();
    private LEBWebRTCView mWebRTCView;
    private String mWebRTCUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        mWebRTCView = findViewById(R.id.id_surface_view);
        mWebRTCView.initilize(getLEBParam(), this);
    }

    private LEBWebRTCParameters getLEBParam() {
        //创建参数对象
        LEBWebRTCParameters mLEBWebRTCParameters = new LEBWebRTCParameters();
        //设置播放码流链接, webrtc://xxxxx
        mLEBWebRTCParameters.setStreamUrl(mWebRTCUrl);
        //设置是否硬解，默认为硬解
        mLEBWebRTCParameters.enableHwDecode(true);
        //设置连接超时时间，默认为5000ms
        mLEBWebRTCParameters.setConnectionTimeOutInMs(5000);
        //设置播放状态回调事件周期，默认为1000ms
        mLEBWebRTCParameters.setStatsReportPeriodInMs(1000);
        //设置日志级别，默认为LOG_NONE
        mLEBWebRTCParameters.setLoggingSeverity(LEBWebRTCParameters.LOG_NONE);
        //设置是否关闭加密，默认为打开加密
        mLEBWebRTCParameters.disableEncryption(true);
        //设置是否启用SEI回调，默认为关闭
        mLEBWebRTCParameters.enableSEICallback(false);
        //设置拉流音频格式，LEBWebRTCParameters.OPUS, LEBWebRTCParameters.AAC_LATM, LEBWebRTCParameters.AAC_ADTS
        mLEBWebRTCParameters.setAudioFormat(LEBWebRTCParameters.OPUS);
        return mLEBWebRTCParameters;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //TODO 1.开始启动sdk，sdk在p2p未连接时会创建offer(local sdp)
        mWebRTCView.startPlay();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mWebRTCView.pausePlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //退出播放需先向信令服务器发出请求（具体见下文）再本地执行下面命令
        //退出播放、并断开连接
        mWebRTCView.stopPlay();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        mWebRTCView.release();
    }

    @Override
    public void onEventOfferCreated(String sdp) {
        // offer创建成功
        Log.d(TAG, "------ onEventOfferCreated :" + sdp);
        //TODO  2.Offer创建成功后回调，用户可以回调中实现向信令服务器发送offer，获取remote sdp，并设置给SDK
        //TODO 3.将remote sdp设置给SDK，sdk会发起p2p连接，连接成功后开始播放
        mWebRTCView.setRemoteSDP(sdp);
    }

    @Override
    public void onEventConnected() {
        // 连接成功
        Log.d(TAG, "------ onEventConnected :");
    }

    @Override
    public void onEventConnectFailed(ConnectionState cs) {
        // 连接失败
        Log.d(TAG, "------ onEventConnectFailed :" + cs.name());
    }

    @Override
    public void onEventDisconnect() {
        // 断开断开
        Log.d(TAG, "------ onEventDisconnect :");
    }

    @Override
    public void onEventFirstPacketReceived(int mediType) {
        // 渲染首帧
        // 0:audio, 1:video
        Log.d(TAG, "------ onEventFirstPacketReceived :" + " mediType:" + mediType);
    }

    @Override
    public void onEventFirstFrameRendered() {
        // 渲染首帧
        Log.d(TAG, "------ onEventFirstFrameRendered :");
    }

    @Override
    public void onEventResolutionChanged(int width, int height) {
        // 分辨率切换
        Log.d(TAG, "------ onEventResolutionChanged :" + "width:" + width + " / height:" + height);
    }

    @Override
    public void onEventStatsReport(LEBWebRTCStatsReport webRTCStatsReport) {
        // 统计数据
        Log.d(TAG, "------ onEventStatsReport :" + webRTCStatsReport);
        /*public class LEBWebRTCStatsReport {
            // video stats
            public long   mFirstVideoPacketDelay; //从启动到收到第一包视频数据的延时
            public long   mFirstFrameRenderDelay; //从启动到首帧渲染延时
            public double mFramerate; //解码帧率
            public long   mVideoBitrate; //视频码率
            public long   mFramesDecoded; //解码帧数
            public long   mFramesDropped; //丢帧数
            public long   mFramesReceived; //接收帧数
            public int    mPacketsLost; //丢包个数
            public long   mFrameWidth; //视频宽度
            public long   mFrameHeight; //视频高度
            public long   mVideoDelayMs;
            public long   mVdieoJitterBufferDelayMs;
            public long   mVideoNacksSent;
            public long   mRTT;

            //audio stats
            public long   mFirstAudioPacketDelay;//从启动到收到第一包音频数据的延时
            public int    mAudioPacketsLost; //丢包个数
            public long   mAudioPacketsReceived; //接收包数
            public long   mAudioBitrate;//音频码率
            public long   mAudioDelayMs;
            public long   mAudioJitterBufferDelayMs;
            public long   mAudioNacksSent;

            //play stats
            public double mAverageFrameRate;//平均帧率
            public long   mAverageBitRate;//平均码率
            public long   mPlayTime;//播放时长
        }*/
    }

    @Override
    public void onEventSEIReceived(ByteBuffer data) {
        // sei回调，解码线程，不要阻塞，没有start code
        // 启用需设置enableSEICallback(true)，默认不回调
        Log.d(TAG, "------ onEventSEIReceived :" + data);
    }
}