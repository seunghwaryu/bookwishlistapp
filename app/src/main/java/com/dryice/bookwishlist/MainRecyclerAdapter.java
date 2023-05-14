package com.dryice.bookwishlist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {
    private ArrayList<GroupItem> groupItems;

    //===== [Click 이벤트 구현을 위해 추가된 코드] ==========================
    // OnItemClickListener 인터페이스 선언
    public interface OnItemClickListener {
        void onItemClicked(int position);
    }

    // OnItemClickListener 참조 변수 선언
    private OnItemClickListener itemClickListener;

    // OnItemClickListener 전달 메소드
    public void setOnItemClickListener (OnItemClickListener listener) {
        itemClickListener = listener;
    }
    //=====================================================================

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView cnt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            cnt = (TextView) itemView.findViewById(R.id.cnt);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View a_view) {
//                    final int position = getAdapterPosition();
//                    if (position != RecyclerView.NO_POSITION) {
//                        a_itemClickListener.onItemClick(a_view, position);
//                    }
//                }
//            });
        }

        void onBind(GroupItem item){
            name.setText(item.getName());
            cnt.setText(item.getCnt());
        }
    }

    @NonNull
    @Override
    public MainRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_item, parent, false);
        MainRecyclerAdapter.ViewHolder viewHolder = new MainRecyclerAdapter.ViewHolder(view);

        //===== [Click 이벤트 구현을 위해 추가된 코드] =====================
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                Context context = view.getContext();
                Intent intent = new Intent(context,BookListActivity.class);
                ((MainActivity)context).startActivity(intent);
            }
        });
        //==================================================================

        return viewHolder;
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

}
