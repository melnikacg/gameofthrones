package org.melnikacg.testapp.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.melnikacg.testapp.R;
import org.melnikacg.testapp.model.Character;
import org.melnikacg.testapp.ui.DividerItemDecoration;
import org.melnikacg.testapp.ui.activity.DetailActivity;
import org.melnikacg.testapp.ui.adapter.CharacterRecyclerViewAdapter;

import io.realm.Realm;
import io.realm.RealmResults;

public class CharacterListFragment extends Fragment implements
        CharacterRecyclerViewAdapter.OnItemClickListener {

    private static final String ARG_HOUSE_ID = "house_id";

    public CharacterListFragment() {
    }

    public static CharacterListFragment newInstance(long houseID) {
        CharacterListFragment fragment = new CharacterListFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_HOUSE_ID, houseID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_character_list, container, false);
        setUpRecyclerView((RecyclerView) rootView.findViewById(R.id.character_list));
        return rootView;
    }

    private void setUpRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        CharacterRecyclerViewAdapter adapter = new CharacterRecyclerViewAdapter(getContext(),
                getCharacterCollection(getArguments().getLong(ARG_HOUSE_ID)));
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext()));
    }

    private RealmResults getCharacterCollection(long houseId) {
        Realm realm = Realm.getDefaultInstance();

        return realm
                .where(Character.class)
                .equalTo(Character.HOUSE_ID_FIELD, houseId)
                .findAll()
                .sort(Character.NAME_FIELD);
    }

    @Override
    public void onItemClick(View view, Character model) {
        DetailActivity.navigate(
                (AppCompatActivity) getActivity(), view.findViewById(R.id.coat_of_arms), model);
    }
}
