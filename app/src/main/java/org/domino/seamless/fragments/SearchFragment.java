package org.domino.seamless.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.search.SearchFactory;
import com.yandex.mapkit.search.SearchManager;
import com.yandex.mapkit.search.SearchManagerType;
import org.domino.seamless.R;


public class SearchFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_search, container, false);
        final View home = inflater.inflate(R.layout.fragment_home, container, false);
        final SearchManager searchManager = SearchFactory.getInstance().createSearchManager(SearchManagerType.COMBINED);

//        final SearchView editText = root.findViewById(R.id.search_view_da);
//        final SearchBar searchBar = root.findViewById(R.id.search_bar);
//        final ListView suggestResultView = root.findViewById(R.id.suggest_result);
//
//        final ArrayAdapter<String> resultAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_activated_1, android.R.id.text1);
//        suggestResultView.setAdapter(resultAdapter);
//        suggestResultView.setOnItemClickListener((adapterView, view, i, l) -> Toast.makeText(getContext(), String.valueOf(adapterView.getItemAtPosition(i)), Toast.LENGTH_SHORT).show());
//

        //editText.getEditText().addTextChangedListener(new SearchWatcher(suggestResultView, searchManager, resultAdapter, getContext(), home.findViewById(R.id.mapview)));
        return root;
    }

    @Override
    public void onStop() {
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
    }

}