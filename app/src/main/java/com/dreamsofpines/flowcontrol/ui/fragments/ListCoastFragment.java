package com.dreamsofpines.flowcontrol.ui.fragments;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.LinearLayout;

import com.dreamsofpines.flowcontrol.R;
import com.dreamsofpines.flowcontrol.data.storage.models.Cost;
import com.dreamsofpines.flowcontrol.ui.adapters.CostsAdapter;

import java.util.List;

public class ListCoastFragment extends Fragment  {

    private List<Cost> mCosts;
    private View card,menu;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setCosts(List<Cost> costs) {
        mCosts = costs;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_cost_fragment, container, false);

        card = (View) getActivity().findViewById(R.id.cardLayout);

        menu = (View) getActivity().findViewById(R.id.home_menu_for_card);
        card.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(menu.getVisibility() == View.INVISIBLE){
                    menu.setVisibility(View.VISIBLE);
                }else{
                    menu.setVisibility(View.INVISIBLE);
                }
                return false;
            }
        });

        final Animation cardHide = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.card_show_anim);
        final Animation cardShow = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.card_hide_anim);
        final RecyclerView recyclerViewVert = (RecyclerView) view.findViewById(R.id.recViewVert);
        RecyclerView.LayoutManager lim = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerViewVert.setLayoutManager(lim);
        recyclerViewVert.setHasFixedSize(true);

        recyclerViewVert.addOnScrollListener(new RecyclerView.OnScrollListener(){

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 5) {
                    if(card.getVisibility() == View.VISIBLE) {
                        card.startAnimation(cardHide);
                    }

                }
                if (dy < -5) {
                    if(card.getVisibility() == View.INVISIBLE) {
                        card.startAnimation(cardShow);
                    }
                }
            }
        });

        cardHide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                card.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                card.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        cardShow.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                card.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                card.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        CostsAdapter ca = new CostsAdapter(mCosts);
        recyclerViewVert.setAdapter(ca);
        return view;
    }




    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
