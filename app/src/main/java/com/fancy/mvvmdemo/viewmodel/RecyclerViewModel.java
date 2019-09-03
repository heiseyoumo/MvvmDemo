package com.fancy.mvvmdemo.viewmodel;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.fancy.mvvmdemo.BaseViewModel;
import com.fancy.mvvmdemo.model.AppRepository;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

/**
 * @author pengkuanwang
 * @date 2019-08-21
 */
public class RecyclerViewModel extends BaseViewModel<AppRepository> {
    /**
     * 给RecyclerView添加ObservableList
     */
    public ObservableList<RecyclerItemViewModel> observableList = new ObservableArrayList<>();

    public RecyclerViewModel(@NonNull Application application, AppRepository appRepository) {
        super(application, appRepository);
    }

    public ItemBinding<RecyclerItemViewModel> itemBinding = ItemBinding.of(new OnItemBind<RecyclerItemViewModel>() {
        @Override
        public void onItemBind(ItemBinding itemBinding, int position, RecyclerItemViewModel item) {

        }
    });
}
