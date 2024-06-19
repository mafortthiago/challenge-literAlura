package com.mafort.book.catalog.service;

public interface IConvertData {
    <T> T  getDate(String json, Class<T> c);
}
