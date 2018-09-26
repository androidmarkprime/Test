package com.example.markprime.test.Home;

public class ViewHolderFragmentPresenter implements ViewHolderFragmentContract.Presenter {

    private ViewHolderFragmentContract.View view;

    ViewHolderFragmentPresenter(ViewHolderFragmentContract.View view){
        this.view = view;
    }

}
