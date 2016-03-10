package max.waitzman.oopshelpme.views.adapters;

/**
 * Created by User7 on 09/03/2016.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import max.waitzman.oopshelpme.R;
import max.waitzman.oopshelpme.models.Rescue;

public class RescueRowListRecyclerViewAdapter extends RecyclerView.Adapter<RescueRowListRecyclerViewAdapter.CustomViewHolder> {
	private List<Rescue> rescuesList;
	private Context mContext;
	private static ClickListener clickListener;

	public interface ClickListener {
		void onItemClick(int position, View v);
	}

	public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		protected ImageView ivUserPicture;
		protected TextView title;
		//protected TextView description;



		public CustomViewHolder(View itemView) {
			super(itemView);
			this.ivUserPicture = (ImageView) itemView.findViewById(R.id.ivUserPicture);
			this.title = (TextView) itemView.findViewById(R.id.title);
			//this.description = (TextView) view.findViewById(R.id.description);
			itemView.setOnClickListener(this);
		}

		/**
		 * Called when a view has been clicked.
		 * @param v The view that was clicked.
		 */
		@Override
		public void onClick(View v) {
			int position = getLayoutPosition(); // gets item position
			//User user = users.get(position);
			// We can access the data within the views
			//Toast.makeText(context, tvName.getText(), Toast.LENGTH_SHORT).show();
			clickListener.onItemClick(getPosition(), v);
		}
	}

	public void setOnItemClickListener(ClickListener clickListener) {
		this.clickListener = clickListener;
	}

	public RescueRowListRecyclerViewAdapter(Context context, List<Rescue> rescuesList) {
		this.rescuesList = rescuesList;
		this.mContext = context;
	}

	@Override
	public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row_rescue, null);

		CustomViewHolder viewHolder = new CustomViewHolder(view);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
		Rescue rescueItem = rescuesList.get(i);

		//Download image using picasso library
		Picasso.with(mContext).load(rescueItem.getDescription())
				.error(R.drawable.ic_menu_camera)
				.placeholder(R.drawable.ic_menu_camera)
				.into(customViewHolder.ivUserPicture);

		//Setting text view title
		customViewHolder.title.setText(rescueItem.getTitle());
	}

	public void addItem(Rescue rescueItem, int index) {
		rescuesList.add(rescueItem);
		notifyItemInserted(index);
	}

	public void deleteItem(int index) {
		rescuesList.remove(index);
		notifyItemRemoved(index);
	}

	@Override
	public int getItemCount() {
		return (null != rescuesList ? rescuesList.size() : 0);
	}
}