package com.pazukdev;

import com.vaadin.navigator.View;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.ui.*;

import java.util.List;

public class CategoryForm extends FormLayout implements View {
    private CategoryService categoryService=CategoryService.getInstance();

    private Grid<HotelCategory> categoryGrid =new Grid<>(HotelCategory.class);

    private HorizontalLayout categoryMainLayout;
    private HorizontalLayout categoryToolbar;

    private Button addCategory= new Button("Add category");
    private Button deleteCategory = new Button("Delete category");
    private Button editCategory = new Button("Edit category");

    private CategoryEditForm categoryEditForm = new CategoryEditForm(this);



    public CategoryForm() {
        //Components init and settings
        setGrid();
        setButtons();
        setLayouts();

        updateCategoryList();

        addComponents(categoryToolbar, categoryMainLayout);
    }


    private void setGrid() {
        categoryGrid.setColumns(
                //"id",
                "name"
        );
        categoryGrid.setSelectionMode(Grid.SelectionMode.MULTI);
        categoryGrid.sort(categoryGrid.getColumn("name"), SortDirection.ASCENDING);
        categoryGrid.setWidth("444px");
        categoryGrid.addSelectionListener(e -> selectionCheck());
    }


    private void setLayouts() {
        // Toolbar
        categoryToolbar = new HorizontalLayout(addCategory, deleteCategory, editCategory);

        // Category edit form
        categoryEditForm.setVisible(false);

        // Main layout
        categoryMainLayout = new HorizontalLayout(categoryGrid, categoryEditForm);
    }


    private void setButtons() {
        //Add button
        addCategory.addClickListener(event -> {
            categoryGrid.asMultiSelect().clear();
            categoryEditForm.editCategory(new HotelCategory());
        });

        // Delete button
        deleteCategory.setEnabled(false);
        deleteCategory.addClickListener(e -> deleteSelected());

        // Edit button
        editCategory.setEnabled(false);
        editCategory.addClickListener(event -> {
            categoryEditForm.editCategory(categoryGrid.getSelectedItems().iterator().next());
        });
    }


    private void deleteSelected() {
        for (HotelCategory category : categoryGrid.getSelectedItems()) {
            categoryService.delete(category);
        }
        updateCategoryList();
    }


    private void selectionCheck() {
        int selectedRowsNumber = categoryGrid.getSelectedItems().size();
        if(categoryEditForm.isVisible()) {
            categoryEditForm.setVisible(false);
        }
        editCategory.setEnabled(selectedRowsNumber == 1);
        deleteCategory.setEnabled(selectedRowsNumber > 0);
    }


    public void updateCategoryList() {
        List<HotelCategory> categories = categoryService.findAll();
        categoryGrid.setItems(categories);
    }

}
