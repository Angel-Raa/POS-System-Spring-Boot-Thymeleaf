package com.github.angel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryDTO {
        private Long categoryId;

        @NotBlank(message = "Category name is required")
        @Size(max = 50, message = "Category name must not exceed 50 characters")
        private String name;

        @Size(max = 150, message = "Description must not exceed 150 characters")
        private String description;

        public CategoryDTO() {
        }

        
        public CategoryDTO(Long categoryId,
                        @NotBlank(message = "Category name is required") @Size(max = 50, message = "Category name must not exceed 50 characters") String name) {
                this.categoryId = categoryId;
                this.name = name;
        }


        public CategoryDTO(Long categoryId,
                        @NotBlank(message = "Category name is required") @Size(max = 50, message = "Category name must not exceed 50 characters") String name,
                        @Size(max = 150, message = "Description must not exceed 150 characters") String description) {
                this.categoryId = categoryId;
                this.name = name;
                this.description = description;
        }

        public Long getCategoryId() {
                return categoryId;
        }

        public void setCategoryId(Long categoryId) {
                this.categoryId = categoryId;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        @Override
        public String toString() {
                return "CategoryDTO [categoryId=" + categoryId + ", name=" + name + ", description=" + description
                                + "]";
        }

}
