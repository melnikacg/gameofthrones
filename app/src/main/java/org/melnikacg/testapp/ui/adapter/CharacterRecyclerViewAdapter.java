package org.melnikacg.testapp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.melnikacg.testapp.R;
import org.melnikacg.testapp.model.Character;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class CharacterRecyclerViewAdapter extends
        RealmRecyclerViewAdapter<Character, CharacterRecyclerViewAdapter.CharacterViewHolder>
        implements View.OnClickListener {

    private OnItemClickListener mOnItemClickListener;

    public CharacterRecyclerViewAdapter(Context context, OrderedRealmCollection<Character> data) {
        super(context, data, true);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        itemView.setOnClickListener(this);
        return new CharacterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CharacterViewHolder holder, int position) {
        Character character = getData().get(position);
        holder.characterName.setText(character.getName());
        holder.characterAliases.setText(character.getAliases());

        Picasso.with(context).load(character.getHouse().getCutOfArmsImeageResource())
                .into(holder.houseCutOfArms);
        holder.itemView.setTag(character);
    }

    @Override
    public void onClick(final View v) {
        mOnItemClickListener.onItemClick(v, (Character) v.getTag());
    }

    class CharacterViewHolder extends RecyclerView.ViewHolder {
        public ImageView houseCutOfArms;
        public TextView characterName;
        public TextView characterAliases;

        public CharacterViewHolder(View view) {
            super(view);
            houseCutOfArms = (ImageView) view.findViewById(R.id.coat_of_arms);
            characterName = (TextView) view.findViewById(R.id.character_name);
            characterAliases = (TextView) view.findViewById(R.id.character_aliases);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, Character model);
    }
}
