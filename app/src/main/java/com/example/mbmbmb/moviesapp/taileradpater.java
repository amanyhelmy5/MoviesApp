package com.example.mbmbmb.moviesapp;

/**
 * Created by mbmbmb on 4/20/2016.
 */
/*
public class taileradpater  extends RecyclerView.Adapter<taileradpater.ViewHolderViewHolder> {
    @SuppressWarnings("unused")
    private final static String LOG_TAG = taileradpater.class.getSimpleName();

    private final ArrayList<Trailer> mTrailers;
    private final Callbacks mCallbacks;

    public enum ViewHolderViewHolder extends RecyclerView.ViewHolder {}

    public interface Callbacks {
        void watch(Trailer trailer, int position);
    }

    public taileradpater(ArrayList<Trailer> trailers, Callbacks callbacks) {
        mTrailers = trailers;
        mCallbacks = callbacks;
    }


    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tailer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderViewHolder holder, int position) {

    }

    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Trailer trailer = mTrailers.get(position);
        final Context context = holder.mView.getContext();

        float paddingLeft = 0;
        if (position == 0) {
           // paddingLeft = context.getResources().getDimension(R.dimen.detail_horizontal_padding);
        }

        float paddingRight = 0;
        if (position + 1 != getItemCount()) {
           // paddingRight = context.getResources().getDimension(R.dimen.detail_horizontal_padding) / 2;
        }

        holder.mView.setPadding((int) paddingLeft, 0, (int) paddingRight, 0);

        holder.mTrailer = trailer;

        String thumbnailUrl = "http://img.youtube.com/vi/" + "56b97ff259acaff235cab79cbd341154" + "/0.jpg";
        Log.i(LOG_TAG, "thumbnailUrl -> " + thumbnailUrl);

        Picasso.with(context)
                .load(thumbnailUrl)
                .config(Bitmap.Config.RGB_565)
                .into(holder.mThumbnailView);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbacks.watch(trailer, holder.getAdapterPosition());
            }
        });
    }

    public int getItemCount() {
        return mTrailers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
      //  @Bind(R.id.tum)
        ImageView mThumbnailView;
        public Trailer mTrailer;

        public ViewHolder(View view) {
            super(view);
         //   ButterKnife.bind(this, view);
            mView = view;
        }
    }

    public void add(List<Trailer> trailers) {
        mTrailers.clear();
        mTrailers.addAll(trailers);
    }

    public ArrayList<Trailer> getTrailers() {
        return mTrailers;
    }
}

*/