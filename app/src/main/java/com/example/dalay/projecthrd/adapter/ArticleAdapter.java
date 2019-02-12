package com.example.dalay.projecthrd.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.dalay.projecthrd.CallBack.OnClickItem;
import com.example.dalay.projecthrd.R;
import com.example.dalay.projecthrd.UpdateArticleActivity;
import com.example.dalay.projecthrd.entity.Article;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private List<Article> articleList;
    private Context context;
    private OnClickItem onClickItem;

    public ArticleAdapter(List<Article> articleList, Context context) {
        this.articleList = articleList;
        this.context = context;
        if(context instanceof  OnClickItem)
        onClickItem  = (OnClickItem) context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_recycler,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.title.setText(article.getTitle());
        holder.category.setText(article.getCategory().getName());
        if(article.getImage()!=null){
            if(article.getImage().isEmpty()){
                holder.imageView.setImageResource(R.drawable.tomteav);
            }else {
                Picasso.get().load(article.getImage())
                        .error(R.drawable.tomteav)
                        .into(holder.imageView);
            }
        }

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView title;
        private TextView category;
        private ImageView setting;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            category = itemView.findViewById(R.id.category);
            setting = itemView.findViewById(R.id.setting);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickItem.onArticleClicked(articleList.get(getAdapterPosition()));

                }
            });
            setting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(context,view);
                    popupMenu.getMenuInflater().inflate(R.menu.setting_menu,popupMenu.getMenu());
                    popupMenu.show();
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            switch (menuItem.getItemId()){
                                case R.id.remove:
                                    onClickItem.onRemove(getAdapterPosition());
                                    return true;
                                case R.id.update:
                                    onClickItem.onUpdate(getAdapterPosition());
                                    return true;
                            }
                            return false;
                        }
                    });
                }
            });
        }
    }

}
