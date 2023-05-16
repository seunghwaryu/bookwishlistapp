package com.dryice.bookwishlist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {

    private List<Book> groupList;

    public MainRecyclerAdapter(List<Book> list) {
        groupList = list;
    }

    //===== [Click 이벤트 구현을 위해 추가된 코드] ==========================
    // OnItemClickListener 인터페이스 선언
    public interface OnItemClickListener {
        void onItemClicked(int position);
    }

    // OnItemClickListener 참조 변수 선언
    private MainRecyclerAdapter.OnItemClickListener itemClickListener;

    // OnItemClickListener 전달 메소드
    public void setOnItemClickListener (MainRecyclerAdapter.OnItemClickListener listener) {
        itemClickListener = listener;
    }
    //=====================================================================

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.group_item, parent, false);
        MainRecyclerAdapter.ViewHolder vh = new MainRecyclerAdapter.ViewHolder(view);

        //===== [Click 이벤트 구현을 위해 추가된 코드] =====================
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = vh.getAdapterPosition();
                Context context = view.getContext();
                Intent intent = new Intent(context,BookListActivity.class);
                ((MainActivity)context).startActivity(intent);
            }
        });
        //==================================================================

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book item = groupList.get(position);
        holder.groupName.setText(item.groupName);
        holder.bookCnt.setText("("+String.valueOf(item.bookCnt)+")");
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView groupName;
        TextView bookCnt;

        ViewHolder(View itemView) {
            super(itemView);

            groupName = itemView.findViewById(R.id.item_name);
            bookCnt = itemView.findViewById(R.id.item_cnt);

        }
    }
}
