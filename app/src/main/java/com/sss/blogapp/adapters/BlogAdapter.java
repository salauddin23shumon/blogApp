package com.sss.blogapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.sss.blogapp.R;
import com.sss.blogapp.roomdb.Blog;

import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.BlogViewHolder> {

    private Context context;
    private List<Blog>blogList;

    private static final String TAG = "BlogAdapter";

    public BlogAdapter(Context context, List<Blog> blogList) {
        this.context = context;
        this.blogList = blogList;
    }

    @NonNull
    @Override
    public BlogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_blog_row, parent, false);
        return new BlogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogViewHolder holder, int position) {
        Blog model=blogList.get(position);
        holder.nameTv.setText(model.getAuthor().getName());
        holder.titleTV.setText(model.getTitle());
        Picasso.get().load(model.getCoverPhoto()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }

    public void setBlogList(List<Blog> blogList) {
        this.blogList = blogList;
        notifyDataSetChanged();
    }

    public class BlogViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTv, titleTV;
        private ImageView imageView;
        public BlogViewHolder(@NonNull View itemView) {

            super(itemView);
            nameTv=itemView.findViewById(R.id.authorNameTV);
            titleTV=itemView.findViewById(R.id.titleTV);
            imageView=itemView.findViewById(R.id.imgView);
        }
    }
}
