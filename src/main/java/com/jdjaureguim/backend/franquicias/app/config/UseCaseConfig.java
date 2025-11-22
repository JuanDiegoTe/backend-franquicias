package com.jdjaureguim.backend.franquicias.app.config;

import com.jdjaureguim.backend.franquicias.app.domain.ports.BranchRepository;
import com.jdjaureguim.backend.franquicias.app.domain.ports.FranchiseRepository;
import com.jdjaureguim.backend.franquicias.app.domain.ports.ProductRepository;
import com.jdjaureguim.backend.franquicias.app.usecase.branch.AddBranchToFranchiseUseCase;
import com.jdjaureguim.backend.franquicias.app.usecase.branch.GetAllBranchesUseCase;
import com.jdjaureguim.backend.franquicias.app.usecase.branch.GetBranchByIdUseCase;
import com.jdjaureguim.backend.franquicias.app.usecase.branch.UpdateBranchNameUseCase;
import com.jdjaureguim.backend.franquicias.app.usecase.franchise.*;
import com.jdjaureguim.backend.franquicias.app.usecase.product.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    // ------------------------------
    // FRANCHISE USE CASES
    // ------------------------------

    @Bean
    public CreateFranchiseUseCase createFranchiseUseCase(FranchiseRepository franchiseRepository) {
        return new CreateFranchiseUseCase(franchiseRepository);
    }

    @Bean
    public UpdateFranchiseNameUseCase updateFranchiseNameUseCase(FranchiseRepository franchiseRepository) {
        return new UpdateFranchiseNameUseCase(franchiseRepository);
    }

    @Bean
    public GetTopStockProductsByBranchUseCase getTopStockProductsByBranchForFranchiseUseCase(
            BranchRepository branchRepository,
            ProductRepository productRepository
    ) {
        return new GetTopStockProductsByBranchUseCase(branchRepository, productRepository);
    }

    @Bean
    public GetAllFranchisesUseCase getAllFranchiseUseCase(FranchiseRepository franchiseRepository) {
        return new GetAllFranchisesUseCase(franchiseRepository);
    }

    @Bean
    public GetFranchiseByIdUseCase getByIdFranchiseUseCase(FranchiseRepository franchiseRepository) {
        return new GetFranchiseByIdUseCase(franchiseRepository);
    }

    @Bean
    public GetFranchisesWithDetailsUseCase getFranchisesWithDetailsUseCase(
            FranchiseRepository franchiseRepository,
            BranchRepository branchRepository,
            ProductRepository productRepository
    ) {
        return new GetFranchisesWithDetailsUseCase(franchiseRepository, branchRepository, productRepository);
    }

    // ------------------------------
    // BRANCH USE CASES
    // ------------------------------

    @Bean
    public AddBranchToFranchiseUseCase addBranchToFranchiseUseCase(
            FranchiseRepository franchiseRepository,
            BranchRepository branchRepository
    ) {
        return new AddBranchToFranchiseUseCase(franchiseRepository, branchRepository);
    }

    @Bean
    public UpdateBranchNameUseCase updateBranchNameUseCase(BranchRepository branchRepository) {
        return new UpdateBranchNameUseCase(branchRepository);
    }

    @Bean
    public GetAllBranchesUseCase getAllBranchesUseCase(BranchRepository branchRepository) {
        return new GetAllBranchesUseCase(branchRepository);
    }

    @Bean
    public GetBranchByIdUseCase getBranchByIdUseCase(BranchRepository branchRepository) {
        return new GetBranchByIdUseCase(branchRepository);
    }

    // ------------------------------
    // PRODUCT USE CASES
    // ------------------------------

    @Bean
    public AddProductToBranchUseCase addProductToBranchUseCase(
            BranchRepository branchRepository,
            ProductRepository productRepository
    ) {
        return new AddProductToBranchUseCase(branchRepository, productRepository);
    }

    @Bean
    public RemoveProductFromBranchUseCase removeProductFromBranchUseCase(ProductRepository productRepository) {
        return new RemoveProductFromBranchUseCase(productRepository);
    }

    @Bean
    public UpdateProductStockUseCase updateProductStockUseCase(ProductRepository productRepository) {
        return new UpdateProductStockUseCase(productRepository);
    }

    @Bean
    public UpdateProductNameUseCase updateProductNameUseCase(ProductRepository productRepository) {
        return new UpdateProductNameUseCase(productRepository);
    }

    @Bean
    public GetAllProductsUseCase getAllProductsUseCase(ProductRepository productRepository) {
        return new GetAllProductsUseCase(productRepository);
    }

    @Bean
    public GetProductByIdUseCase  getProductByIdUseCase (ProductRepository productRepository) {
        return new GetProductByIdUseCase (productRepository);
    }

}
