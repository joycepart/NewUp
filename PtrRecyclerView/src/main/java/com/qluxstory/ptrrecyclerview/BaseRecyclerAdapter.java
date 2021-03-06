package com.qluxstory.ptrrecyclerview;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 支持多布局item的adapter
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {

    protected List<T> mDatas = new ArrayList<>();

    protected List<View> mHeaderViews = new ArrayList<>();

    protected List<View> mFootViews = new ArrayList<>();

    protected List<Integer> mHeaderViewTypes = new ArrayList<>();
    protected List<Integer> mFooterViewTypes = new ArrayList<>();

    protected final static int VIEW_TYPE_HEADER = Integer.MIN_VALUE;
    protected final static int VIEW_TYPE_FOOTER = Integer.MAX_VALUE;

    IViewType viewTypes;
    View loadMoreFooterView;
    boolean setLayoutManager = false;

    public BaseRecyclerAdapter() {

    }

    public BaseRecyclerAdapter(IViewType viewTypes) {

        this.viewTypes = viewTypes;
    }

    public void addHeaderView(View view) {
        mHeaderViews.add(view);
    }

    public void removeFooterView(View view) {
        if (mFootViews.contains(view)) {
            mFootViews.remove(view);
        }
    }


    public void showloadMoreFooterView(boolean b) {
        if (loadMoreFooterView == null) {
            return;
        }
        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) loadMoreFooterView.getLayoutParams();
        if (b) {
            int w = View.MeasureSpec.makeMeasureSpec(0,
                    View.MeasureSpec.UNSPECIFIED);
            int h = View.MeasureSpec.makeMeasureSpec(0,
                    View.MeasureSpec.UNSPECIFIED);
            loadMoreFooterView.measure(w, h);
            lp.width = RecyclerView.LayoutParams.MATCH_PARENT;
            lp.height = loadMoreFooterView.getMeasuredHeight();
        } else {
            lp.width = 0;
            lp.height = 0;
        }
        loadMoreFooterView.setLayoutParams(lp);
    }

    public void addFooterView(View view) {
        if ("loadMoreFooterView".equals(view.getTag())) {
            loadMoreFooterView = view;
            if (mFootViews.contains(loadMoreFooterView)) {
                mFootViews.remove(loadMoreFooterView);
            }
            mFootViews.add(view);
        } else {
            if (mFootViews.contains(loadMoreFooterView)) {
                mFootViews.add(mFootViews.size() - 2, view);
            } else {
                mFootViews.add(view);
            }
        }

    }

    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    public int getFootersCount() {
        return mFootViews.size();
    }

    protected OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public interface OnRecyclerViewItemClickListener {
        /**
         * @param itemView normalItem view
         * @param itemBean normalItem WrapBean
         * @param position normalItem real position ,be consistent with mDatas
         */
        void onItemClick(View itemView, Object itemBean, int position);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void append(T itemBean) {
        if (itemBean != null) {
            mDatas.add(itemBean);
            notifyDataSetChanged();
        }
    }

    public void append(List<T> itemBeans) {
        if (itemBeans.size() > 0) {
            for (T itemBean : itemBeans) {
                mDatas.add(itemBean);
            }

            notifyDataSetChanged();
        }
    }

    public void replaceAll(List<T> itemBeans) {
        mDatas.clear();
        if (itemBeans.size() > 0) {
            mDatas.addAll(itemBeans);
            notifyDataSetChanged();
        }
    }

    public void removeAt(int position) {
        mDatas.remove(position);
        notifyDataSetChanged();
    }

    public void remove(T itemBean) {
        mDatas.remove(itemBean);
        notifyDataSetChanged();
    }

    public void removeAll() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return getHeadersCount() + getFootersCount() + mDatas.size();
    }

    public boolean isHeader(int position) {
        return getHeadersCount() > 0 && position <= getHeadersCount() - 1;
    }

    public boolean isFooter(int position) {
        int lastPosition = getItemCount() - getFootersCount();
        return getFootersCount() > 0 && position >= lastPosition;
    }

    public abstract void bindData(BaseRecyclerViewHolder holder, T itemBean, int position, int viewType);

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (!setLayoutManager && parent instanceof RecyclerView) {
            setLayoutManager((RecyclerView) parent);
            setLayoutManager = true;
        }

        if (mHeaderViewTypes.contains(viewType)) {
            return new BaseRecyclerViewHolder(mHeaderViews.get(viewType - VIEW_TYPE_HEADER));
        } else if (mFooterViewTypes.contains(viewType)) {
            return new BaseRecyclerViewHolder(mFootViews.get(VIEW_TYPE_FOOTER - viewType));
        } else {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(viewTypes.getItemViewLayoutId(viewType), parent,
                    false);
            return new BaseRecyclerViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(final BaseRecyclerViewHolder holder, final int position) {
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {
            return;
        }
        final int realPosition = position - numHeaders;
        if (realPosition < mDatas.size()) {
            bindData(holder, mDatas.get(realPosition), realPosition, getItemViewType(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(holder.itemView, mDatas.get(realPosition), realPosition);
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        int numHeaders = getHeadersCount();
        int realPosition = position - numHeaders;
        if (numHeaders > 0 && position < numHeaders) {
            int mHeaderViewType = VIEW_TYPE_HEADER + position;
            mHeaderViewTypes.add(mHeaderViewType);
            return mHeaderViewType;
        } else if (getFootersCount() > 0 && realPosition >= mDatas.size()) {
            int mFooterViewType = VIEW_TYPE_FOOTER - (position - numHeaders - mDatas.size());
            mFooterViewTypes.add(mFooterViewType);
            return mFooterViewType;
        } else {
            return viewTypes.getItemViewType(position, mDatas.get(realPosition));
        }
    }

    public void setLayoutManager(RecyclerView recyclerView) {
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (isHeader(position) || isFooter(position)) ? ((GridLayoutManager) layoutManager).getSpanCount() : 1;
                }
            });
        }
    }


}
