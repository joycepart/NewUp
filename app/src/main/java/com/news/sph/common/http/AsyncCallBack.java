package com.news.sph.common.http;

import android.content.Context;

import com.google.gson.Gson;
import com.news.sph.AppConfig;
import com.news.sph.common.entity.BaseEntity;
import com.news.sph.common.eventbus.ErrorEvent;
import com.news.sph.common.utils.LogUtils;
import com.news.sph.common.utils.TDevice;

import java.io.IOException;

import de.greenrobot.event.EventBus;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AsyncCallBack<T> implements Callback {
	CallBack<T> callback;
	Class<T> clazz;
	Context context;
	public static final Gson gson = new Gson();
	private Object tag;

	public AsyncCallBack(Context context, CallBack<T> callback, Class<T> clazz) {
		this.context = context;
		this.callback = callback;
		this.clazz = clazz;
		this.tag = context;
	}

	@Override
	public void onFailure(Call call, IOException e) {
		LogUtils.i("request failed "+e+ "   e.msg:"+e.getMessage());
		if(!TDevice.hasInternet(context)){
			EventBus.getDefault().post(
					new ErrorEvent(AppConfig.ERROR_NONET,
							AppConfig.ERROR_NONET_MSG, tag, context));
		}else {
			String msg = e.getMessage();
			if (!"Canceled".equals(msg)) {
				EventBus.getDefault().post(
						new ErrorEvent(AppConfig.ERROR_REQ,
								AppConfig.ERROR_REQ_MSG, tag, context));
			}
		}
	}

	@Override
	public void onResponse(Call call, Response response) throws IOException {
		if (response.isSuccessful()) {

			String reader = response.body().string();
			int startIndex=reader.indexOf("{",reader.indexOf("{")+1);
			int endIndex=reader.lastIndexOf("]");
			reader=reader.substring(startIndex,endIndex);

			LogUtils.e("response success json-->" + reader);
			try {
				T t = gson.fromJson(reader.trim(), clazz);
				callback.sendMsg(CallBack.SUCCESS, t);
				BaseEntity entity=(BaseEntity)t;
				EventBus.getDefault().post(
						new ErrorEvent(entity.getStatus(),
								entity.getMsg(), tag, context));
			}catch (Exception e){
				callback.sendMsg(CallBack.FAIL, (T) AppConfig.ERROR_PARSER_MSG);
				EventBus.getDefault().post(
						new ErrorEvent(AppConfig.ERROR_PARSER,
								AppConfig.ERROR_PARSER_MSG, tag, context));
				LogUtils.i("response json parse error "+e);
				e.printStackTrace();
			}
		} else {
			callback.sendMsg(CallBack.FAIL, (T) AppConfig.ERROR_IO_MSG);
			EventBus.getDefault().post(
					new ErrorEvent(AppConfig.ERROR_IO,
							AppConfig.ERROR_IO_MSG, tag, context));
			LogUtils.i("response is not Successful "+response);
		}
	}

	public Object getTag() {
		return tag;
	}

	public void setTag(Object tag) {
		this.tag = tag;
	}


}
