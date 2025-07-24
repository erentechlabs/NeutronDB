package com.neutrondb.neutrondb.mapper;

public interface Mapper<A,B> {
    B mapTo(A a);
    A mapFrom(B a);
}
