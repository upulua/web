package ru.msu.cmc.webprack.models;

public interface CommonEntity<ID> {
    ID getId();
    void setId(ID id);
}