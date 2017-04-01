package com.mt.time.detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;


import com.mt.time.AppUtil;
import com.mt.time.R;
import com.mt.time.db.AppModel;
import com.mt.time.db.DBUtil;
import com.mt.time.db.TimeUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MaoTong on 2017/3/13.
 */

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailViewHolder> {

    private List<AppModel> mModels = new ArrayList<>();
    private Context mContext;
    private int mModelsSize;

    private static final String TAG = "DetailAdapter";

    DetailAdapter(Context context) {
        mModels = DBUtil.findAll();
        mModelsSize = mModels.size();
        mContext = context;
    }

    @Override
    public DetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DetailViewHolder(LayoutInflater.from(mContext).inflate(R.layout.content_detail_item, parent, false));
    }

    @Override
    public void onBindViewHolder(DetailViewHolder holder, int position) {
        AppModel appModel = mModels.get(mModelsSize - (position + 1));
        holder.mAppName.setText(appModel.getAppName());
        holder.mStartTime.setText(appModel.getStartTime());

        if (appModel.getEndTime() == null) {
            String endTime = TimeUtil.currentTime();
            holder.mEndTime.setText(endTime);
            holder.mDuration.setText(TimeUtil.durationTime(new Date(appModel.getStartTime()), new Date(endTime)));
        } else {
            holder.mEndTime.setText(appModel.getEndTime());
            holder.mDuration.setText(appModel.getDuration());
        }

        holder.mAppIcon.setImageDrawable(AppUtil.getApplicationIconByPackageName(mContext, appModel.getPackageName()));

    }

    @Override
    public int getItemCount() {
        return mModels.size();
    }

    class DetailViewHolder extends RecyclerView.ViewHolder {

        private TextView mAppName, mStartTime, mEndTime, mDuration;
        private ImageView mAppIcon;

        DetailViewHolder(View itemView) {
            super(itemView);
            mAppName = (TextView) itemView.findViewById(R.id.tv_app_name);
            mStartTime = (TextView) itemView.findViewById(R.id.tv_start_time);
            mEndTime = (TextView) itemView.findViewById(R.id.tv_end_time);
            mDuration = (TextView) itemView.findViewById(R.id.tv_duration);
            mAppIcon = (ImageView) itemView.findViewById(R.id.iv_app_icon);
        }
    }
}
