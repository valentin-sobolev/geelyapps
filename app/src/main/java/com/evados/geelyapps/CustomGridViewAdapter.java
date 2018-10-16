package com.evados.geelyapps;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.evados.geelyapps.model.AppModel;

import java.util.List;

/**
 * @author manish.s
 */
public class CustomGridViewAdapter extends ArrayAdapter<AppModel> {
    Context context;
    List<AppModel> data;
    private int layoutResourceId;
    private OnItemClickInterface onItemClickInterface;

    CustomGridViewAdapter(Context context, int layoutResourceId,
                          List<AppModel> data, OnItemClickInterface onItemClickInterface) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        this.onItemClickInterface = onItemClickInterface;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        RecordHolder holder;
        final AppModel item = data.get(position);
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new RecordHolder();
            holder.txtTitle = row.findViewById(R.id.app_name);
            holder.imageItem = row.findViewById(R.id.app_icon);
            holder.linearLayout = row.findViewById(R.id.app_layout);
            holder.linearLayout.setOnClickListener(v -> onItemClickInterface.onItemClick(item));
            row.setTag(holder);
        } else {
            holder = (RecordHolder) row.getTag();
        }
        holder.txtTitle.setText(item.getName());
        holder.imageItem.setImageDrawable(item.getIcon());
        return row;

    }

    static class RecordHolder {
        TextView txtTitle;
        ImageView imageItem;
        LinearLayout linearLayout;
    }
}