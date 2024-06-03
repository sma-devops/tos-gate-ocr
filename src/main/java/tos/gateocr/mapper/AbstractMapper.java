package tos.gateocr.mapper;

public interface AbstractMapper<S, T> {
	T map(S source);
}
