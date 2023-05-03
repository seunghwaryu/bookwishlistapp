package com.dryice.bookwishlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {
    private ArrayList<GroupItem> groupItems;

    @NonNull
    @Override
    public MainRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainRecyclerAdapter.ViewHolder holder, int position) {
        holder.onBind(groupItems.get(position));
    }

    public void setFriendList(ArrayList<GroupItem> list){
        this.groupItems = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return groupItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView cnt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            cnt = (TextView) itemView.findViewById(R.id.cnt);
        }

        void onBind(GroupItem item){
            name.setText(item.getName());
            cnt.setText(item.getCnt());
        }
    }

}
