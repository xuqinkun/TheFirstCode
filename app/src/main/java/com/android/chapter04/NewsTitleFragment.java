package com.android.chapter04;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.main.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsTitleFragment extends Fragment {
    private boolean isTwoPane;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_frag, container, false);
        RecyclerView newsTitleContainer = view.findViewById(R.id.news_title_container);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        newsTitleContainer.setLayoutManager(layoutManager);
        newsTitleContainer.setAdapter(new NewsAdapter(getNews()));
        return view;
    }

    private List<News> getNews() {
        List<News> newsList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            News news = new News();
            news.setTitle("This is news title " + i);
            news.setContent(getRandomContent("This is news content " + i + "."));
            newsList.add(news);
        }
        return newsList;
    }

    private String getRandomContent(String content) {
        Random random = new Random();
        int len = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            builder.append(content);
        }
        return builder.toString();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content_layout) != null) {
            isTwoPane = true;
        } else {
            isTwoPane = false;
        }
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
        private List<News> newsList;

        public NewsAdapter(List<News> newsList) {
            this.newsList = newsList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
            final ViewHolder viewHolder = new ViewHolder(view);
            view.setOnClickListener(v -> {
                News news = newsList.get(viewHolder.getAdapterPosition());
                if (isTwoPane) {
                    NewsContentFragment contentFragment = (NewsContentFragment) getFragmentManager()
                            .findFragmentById(R.id.news_content_fragment);
                    contentFragment.refresh(news.getTitle(), news.getContent());
                } else {
                    NewsContentActivity.actionStart(getActivity(), news.getTitle(), news.getContent());
                }
            });
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            News news = newsList.get(position);
            holder.newsTitle.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return newsList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView newsTitle;

            public ViewHolder(@NonNull View view) {
                super(view);
                newsTitle = view.findViewById(R.id.news_title);
            }
        }
    }
}
