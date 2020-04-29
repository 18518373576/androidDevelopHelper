package com.example.developerandroidx.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.developerandroidx.R;
import com.example.developerandroidx.bean.FunctionItemBean;

import java.util.List;

public class FunctionRcvAdapter extends RecyclerView.Adapter<FunctionRcvAdapter.Holder> {

    Context context;
    List<FunctionItemBean> list;

    public FunctionRcvAdapter(Context context, List<FunctionItemBean> list) {
        this.context = context;
        this.list = list;
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView tv_item_name;
        ImageView iv_item_icon;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tv_item_name = itemView.findViewById(R.id.tv_item_name);
            iv_item_icon = itemView.findViewById(R.id.iv_item_icon);

        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_function, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.tv_item_name.setText(list.get(position).itemName);
        holder.iv_item_icon.setImageResource(list.get(position).itemIconId);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String activityClassName = list.get(position).goTo;
                    if (!TextUtils.isEmpty(activityClassName)) {
                        context.startActivity(new Intent(context, Class.forName(activityClassName)));
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
