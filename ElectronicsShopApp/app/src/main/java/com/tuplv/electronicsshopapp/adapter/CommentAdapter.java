package com.tuplv.electronicsshopapp.adapter;

import static com.tuplv.electronicsshopapp.api.GetAPI.DOMAIN_IMAGE;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.tuplv.electronicsshopapp.R;
import com.tuplv.electronicsshopapp.model.Comment;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    Context context;
    int layout;
    List<Comment> list;

    public CommentAdapter(Context context, int layout, List<Comment> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment comment = list.get(position);
        Picasso.get().load(DOMAIN_IMAGE + "/user/" + comment.getAvatarUser()).into(holder.imgAvatar);
        holder.tvFullName.setText(comment.getFullNameUser());
        holder.tvContent.setText(comment.getContent());
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAvatar;
        TextView tvFullName, tvContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            tvFullName = itemView.findViewById(R.id.tvFullName);
            tvContent = itemView.findViewById(R.id.tvContent);
        }
    }
}
