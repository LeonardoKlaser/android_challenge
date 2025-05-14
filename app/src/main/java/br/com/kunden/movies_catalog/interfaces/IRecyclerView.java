package br.com.kunden.movies_catalog.interfaces;

public interface IRecyclerView {
    public void OnItemClick(int position);
    public void OnDeleteClick(int position);
    public void OnEditClick(int position);

}
