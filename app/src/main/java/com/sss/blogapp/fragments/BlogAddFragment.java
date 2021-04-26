package com.sss.blogapp.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.sss.blogapp.R;
import com.sss.blogapp.responses.BlogResponse;
import com.sss.blogapp.responses.BlogResponseLocal;
import com.sss.blogapp.roomdb.Author;
import com.sss.blogapp.roomdb.Blog;
import com.sss.blogapp.roomdb.BlogDb;
import com.sss.blogapp.viewmodels.BlogAddViewModel;
import com.sss.blogapp.viewmodels.BlogViewModel;

import java.util.ArrayList;
import java.util.List;


public class BlogAddFragment extends Fragment {


    private TextInputEditText titleET, descET, catET, nameET, profET;
    private Button saveBtn;
    private BlogDb db;
    private BlogAddViewModel viewModel;

    private static final String TAG = "BlogAddFragment";

    public BlogAddFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        db=BlogDb.getInstance(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(getActivity()).get(BlogAddViewModel.class);
        return inflater.inflate(R.layout.fragment_blog_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titleET=view.findViewById(R.id.titleET);
        descET=view.findViewById(R.id.decET);
        catET=view.findViewById(R.id.catET);
        nameET=view.findViewById(R.id.authorNameET);
        profET=view.findViewById(R.id.profET);
        saveBtn=view.findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title=titleET.getText().toString();
                String desc=descET.getText().toString();
                String category=catET.getText().toString();
                String authorName=nameET.getText().toString();
                String profession=profET.getText().toString();

                int id=db.dao().lastRowId();
                id++;

//                Log.e(TAG, "onClick: "+id );
                List<String> categories=new ArrayList<>();
                categories.add(category);

                Author author=new Author();
                author.setName(authorName);
                author.setAvatar("https://i.pravatar.cc/250");
                author.setProfession(profession);
                author.setId(id);

                Blog blog=new Blog();
                blog.setId(id);
                blog.setTitle(title);
                blog.setDescription(desc);
                blog.setCoverPhoto("https://i.picsum.photos/id/608/200/300.jpg?hmac=b-eDmVyhr3rI_4k3z2J_PNwOxEwSKa5EDC9uFH4jERU");
                blog.setCategories(categories);
                blog.setAuthor(author);


                if(title.isEmpty() || desc.isEmpty() || category.isEmpty() || authorName.isEmpty()|| profession.isEmpty()){
                    Toast.makeText(getActivity(), "insert required data", Toast.LENGTH_LONG).show();
                }else {
                    viewModel.insertToDb(blog);
                    viewModel.getLocalResLiveData().observe(getViewLifecycleOwner(), new Observer<BlogResponseLocal>() {
                        @Override
                        public void onChanged(BlogResponseLocal blogResponseLocal) {
                            if (blogResponseLocal.isInsert()){
                                Toast.makeText(getActivity(), "insert success", Toast.LENGTH_SHORT).show();
                                Navigation.findNavController(view).popBackStack();
                            }else {
                                Log.d(TAG, "onChanged: not success");
                            }
                        }
                    });
                }
            }
        });

    }
}