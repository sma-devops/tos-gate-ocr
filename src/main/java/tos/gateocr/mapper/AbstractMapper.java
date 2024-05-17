package tos.gateocr.mapper;

import java.util.ArrayList;
import java.util.List;

public interface AbstractMapper<E, M> {
	public M entityToModel(E entity);
	public ArrayList<M> entityListToModelArrayList(List<E> entityList);
	public E modelToEntity(M model);
	public List<E> modelArrayListToEntityList(ArrayList<M> modelArrayList);

}
