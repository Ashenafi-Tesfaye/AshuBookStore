package com.ashenafiAG.spring.demo.AshuBookStorewithSpring.model;

import java.util.Objects;

public class Item {

	
	private Book book;
	private int quantity;
	
	public Item() {
		
	}

	public Item(Book book, int quantity) {
		
		this.book = book;
		this.quantity = quantity;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(book, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(book, other.book) && quantity == other.quantity;
	}

	@Override
	public String toString() {
		return "Item [book=" + book + ", quantity=" + quantity + "]";
	}
	
	
	
}
