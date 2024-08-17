/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.github.angel.utils;

import java.util.List;

import org.springframework.data.domain.Page;
import java.util.ArrayList;

/**
 *
 * @author aguero
 */
public class PageRenderUtils<T> {
    private String url;
    private Page<T> page;
    private int totalPages;
    private int numElementsPerPage;
    private int actualPage;
    private List<PageableUtils> pages;

    public PageRenderUtils(String url, Page<T> page) {
        int from;
        int until;
        this.pages = new ArrayList<>();
        this.numElementsPerPage = 5;
        this.url = url;
        this.page = page;
        this.totalPages = page.getTotalPages();
        this.actualPage = page.getNumber() + 1;
        if (totalPages <= numElementsPerPage) {
            // Si hay menos páginas que el número máximo de elementos por página, muestra
            // todas las páginas
            from = 1;
            until = totalPages;
        } else {
            if (actualPage <= numElementsPerPage / 2) {
                // Si la página actual está cerca del principio, muestra las primeras
                // `numElementsPerPage` páginas
                from = 1;
                until = numElementsPerPage;
            } else if (actualPage + numElementsPerPage / 2 >= totalPages) {
                // Si la página actual está cerca del final, muestra las últimas
                // `numElementsPerPage` páginas
                from = totalPages - numElementsPerPage + 1;
                until = totalPages;
            } else {
                // Si la página actual está en medio, muestra un rango de páginas alrededor de
                // la página actual
                from = actualPage - numElementsPerPage / 2;
                until = actualPage + numElementsPerPage / 2;
            }
        }

        for (int i = 0; i < until; i++) {
            pages.add(new PageableUtils(from + i, this.actualPage == from + i));
        }
    }

    public boolean isLastPage() {
        return !page.hasNext();
    }

    public boolean isHasPrevious() {
        return page.hasPrevious();
    }

    public boolean isHasNext() {
        return page.hasNext();
    }

    public boolean isLast() {
        return page.isLast();
    }

    public boolean isFirst() {
        return page.isFirst();
    }

    public String getUrl() {
        return url;
    }

    public Page<T> getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getNumElementsPerPage() {
        return numElementsPerPage;
    }

    public int getActualPage() {
        return actualPage;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setNumElementsPerPage(int numElementsPerPage) {
        this.numElementsPerPage = numElementsPerPage;
    }

    public void setActualPage(int actualPage) {
        this.actualPage = actualPage;
    }

    public List<PageableUtils> getPages() {
        return pages;
    }

    public void setPages(List<PageableUtils> pages) {
        this.pages = pages;
    }

}
