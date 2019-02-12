package com.example.dalay.projecthrd.CallBack;

import android.view.View;

import com.example.dalay.projecthrd.entity.Article;

public interface OnClickItem {
    void onArticleClicked(Article article);
    void onRemove(int position);
    void onUpdate(int position);

}
