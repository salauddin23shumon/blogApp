package com.sss.blogapp.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sss.blogapp.R;
import com.sss.blogapp.adapters.BlogAdapter;
import com.sss.blogapp.responses.BlogResponse;
import com.sss.blogapp.roomdb.Author;
import com.sss.blogapp.roomdb.Blog;
import com.sss.blogapp.roomdb.BlogDb;
import com.sss.blogapp.viewmodels.BlogViewModel;

import java.util.ArrayList;
import java.util.List;


public class BlogListFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Blog> blogs;
    private BlogAdapter adapter;
    private Context context;
    private BlogViewModel viewModel;
    private BlogDb db;
    private FloatingActionButton fab;
    private NavController controller;

    private static final String TAG = "BlogListFragment";

    public BlogListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
        blogs=new ArrayList<>();
        db=BlogDb.getInstance(context);
        adapter=new BlogAdapter(context, blogs);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(getActivity()).get(BlogViewModel.class);
        viewModel.getApiData();
        return inflater.inflate(R.layout.fragment_blog_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.blogRV);
        fab=view.findViewById(R.id.fab);
        controller=Navigation.findNavController(view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mLayoutManager.scrollToPositionWithOffset(0, 0);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);



        viewModel.getAllBlogData().observe(getViewLifecycleOwner(), new Observer<List<Blog>>() {
            @Override
            public void onChanged(List<Blog> blogs) {
                if (blogs!=null){
                    adapter.setBlogList(blogs);
                    recyclerView.scrollToPosition(adapter.getItemCount() - 1);
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.navigate(R.id.action_blogListFragment_to_blogAddFragment);
            }
        });
    }
}