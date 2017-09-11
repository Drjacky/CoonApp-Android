package com.jodelapp.utilities.rx;


import io.reactivex.SingleTransformer;

public interface ThreadTransformer {
  <T> SingleTransformer<T, T> applySchedulers();
}
