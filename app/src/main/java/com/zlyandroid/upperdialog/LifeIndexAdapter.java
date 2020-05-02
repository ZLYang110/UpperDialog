package com.zlyandroid.upperdialog;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         2016/12/13
 */
public class LifeIndexAdapter extends BaseRecyclerViewAdapter<LifeIndexAdapter.ViewHolder> {

    private Context context;
    private List<String> indexList;

    public LifeIndexAdapter(Context context, List<String> indexList) {
        this.context = context;
        this.indexList = indexList;
    }

    @Override
    public LifeIndexAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_life_index, parent, false);
        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(LifeIndexAdapter.ViewHolder holder, int position) {
        String index = indexList.get(position);
        holder.indexIconImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_index_sport));
        holder.indexLevelTextView.setText(index);
    }

    @Override
    public int getItemCount() {
        return indexList == null ? 0 : indexList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.index_icon_image_view)
        ImageView indexIconImageView;
        @BindView(R.id.index_level_text_view)
        TextView indexLevelTextView;
        @BindView(R.id.index_name_text_view)
        TextView indexNameTextView;

        ViewHolder(View itemView,final LifeIndexAdapter adapter) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.onItemHolderClick(LifeIndexAdapter.ViewHolder.this);
                }
            });
        }
    }


}
