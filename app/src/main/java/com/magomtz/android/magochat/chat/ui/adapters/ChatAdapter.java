package com.magomtz.android.magochat.chat.ui.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.magomtz.android.magochat.R;
import com.magomtz.android.magochat.entities.ChatMessage;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mago on 03/07/2016.
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private Context context;
    private List<ChatMessage> chatMessages;

    public ChatAdapter(Context context, List<ChatMessage> chatMessages) {
        this.context = context;
        this.chatMessages = chatMessages;
    }

    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_chat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChatMessage chatMessage = chatMessages.get(position);

        String msg = chatMessage.getMsg();
        holder.txtMessage.setText(msg);


        int color = fetchColor(R.attr.colorAccent);
        int gravity = Gravity.RIGHT;

        if(!chatMessage.isSentByMe()){
            color = fetchColor(R.attr.colorPrimary);
            gravity = Gravity.LEFT;
        }

        holder.txtMessage.setBackgroundColor(color);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)holder.txtMessage.getLayoutParams();
        params.gravity=gravity;
        holder.txtMessage.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    public void add(ChatMessage msg) {
        if(!chatMessages.contains(msg)){
            chatMessages.add(msg);
            notifyDataSetChanged();
        }
        if (!alreadyInAdapter(msg)) {
            this.chatMessages.add(msg);
            this.notifyDataSetChanged();
        }
    }

    private boolean alreadyInAdapter(ChatMessage newMsg){
        boolean alreadyInAdapter = false;
        for (ChatMessage msg : this.chatMessages) {
            if (msg.getMsg().equals(newMsg.getMsg()) &&
                    msg.getSender().equals(newMsg.getSender())) {
                alreadyInAdapter = true;
                break;
            }
        }
        return alreadyInAdapter;
    }

    private int fetchColor(int color){
        TypedValue typedValue = new TypedValue();
        TypedArray a = context.obtainStyledAttributes(typedValue.data,
                new int[]{color});
        int returnColor = a.getColor(0,0);
        a.recycle();
        return returnColor;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.txtMessage)
        TextView txtMessage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
