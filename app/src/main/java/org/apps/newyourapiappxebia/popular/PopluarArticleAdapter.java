package org.apps.newyourapiappxebia.popular;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.apps.newyourapiappxebia.R;
import org.apps.newyourapiappxebia.model.Article;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class PopluarArticleAdapter extends RecyclerView.Adapter<PopluarArticleAdapter.PopularArticleViewHolder> {

    private final PopularArticleClickedListener listener;
    private final List<Article> data = new ArrayList<>();

    PopluarArticleAdapter(PopularArticleClickedListener listener) {
        this.listener = listener;
        setHasStableIds(true);
    }

    @Override
    public PopularArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_popular_articles_list_item, parent, false);
        return new PopularArticleViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(PopularArticleViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).id();
    }

    void setData(List<Article> articles) {
        if (articles != null) {
            data.clear();
            data.addAll(articles);
            notifyDataSetChanged();

        } else {
            data.clear();
            notifyDataSetChanged();
        }
    }

    static final class PopularArticleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_article_title)
        TextView titleTextView;
        @BindView(R.id.tv_article_author)
        TextView articleAuthorText;

        private Article article;

        PopularArticleViewHolder(View itemView, PopularArticleClickedListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if (article != null) {
                    listener.onArticleClicked(article);
                }
            });
        }

        void bind(Article article) {
            this.article = article;
            titleTextView.setText(article.title());
            articleAuthorText.setText(article.byAuthor());
        }
    }

    interface PopularArticleClickedListener {

        void onArticleClicked(Article article);
    }
}
