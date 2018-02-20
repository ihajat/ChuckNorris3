package com.example.iqbalhajat.chucknorris3.utils;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class PredicateUtils {
  public static <T> Predicate<T> check(Consumer<T> consumer) {
    return t -> {
      consumer.accept(t);
      return true;
    };
  }
}
