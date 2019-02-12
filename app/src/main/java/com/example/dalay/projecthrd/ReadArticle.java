package com.example.dalay.projecthrd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dalay.projecthrd.entity.Article;
import com.squareup.picasso.Picasso;

public class ReadArticle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_article);
        if(getSupportActionBar()!=null)
            getSupportActionBar().hide();
        ImageView imageView = findViewById(R.id.image);
        TextView title = findViewById(R.id.title);
        TextView des = findViewById(R.id.description);
        TextView authorName = findViewById(R.id.author_name);
        TextView date = findViewById(R.id.date);
        ImageButton bt = findViewById(R.id.btclose);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        Article article = intent.getParcelableExtra("Article");
        title.setText(article.getTitle());
        des.setText(article.getDescription());
        String name = "Dalay";
        if(name!=null){
            authorName.setText("By : "+name);
        }
        date.setText("Date : "+article.getCreatedDate().substring(0,8) + ","+article.getCreatedDate().substring(8));
        Picasso.get().load(article.getImage())
                .error(R.drawable.tomteav)
                .into(imageView);


    }
}
