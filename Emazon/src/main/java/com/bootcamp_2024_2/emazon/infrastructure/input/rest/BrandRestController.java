package com.bootcamp_2024_2.emazon.infrastructure.input.rest;

import com.bootcamp_2024_2.emazon.application.dto.request.BrandRequest;
import com.bootcamp_2024_2.emazon.application.dto.response.BrandResponse;
import com.bootcamp_2024_2.emazon.application.handler.BrandHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brands")
public class BrandRestController {

    private final BrandHandler brandHandler;

    @GetMapping(path = "/v1/brands")
    @Operation(summary = "Retrieve paginated brands", description = "Returns a paginated list of brands sorted by the specified field and order.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved brands",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BrandResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    public Page<BrandResponse> getBrands(
            @Parameter(description = "Field to sort by", example = "name")
            @RequestParam(defaultValue = "name") String sortBy,
            @Parameter(description = "Sort order: asc (ascending) or desc (descending)", example = "asc")
            @RequestParam(defaultValue = "asc") String order,
            @Parameter(description = "Page number to retrieve (0-based index)", example = "0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items per page", example = "10")
            @RequestParam(defaultValue = "10") int size) {

        Sort sort = order.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        final Pageable pageable = PageRequest.of(page, size, sort);
        return brandHandler.findAll(pageable);
    }


    @GetMapping(path = "/v1/brand/{id}")
    @Operation(summary = "Retrieve a brand by ID", description = "Returns the details of a specific brand identified by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the brand",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BrandResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Brand not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    public BrandResponse getBrand(
            @Parameter(description = "ID of the brand to be retrieved", example = "1")
            @PathVariable Long id) {
        return brandHandler.findById(id);
    }

    @PostMapping(path = "/v1/createBrand")
    @Operation(summary = "Create a new brand", description = "Creates a new brand with a unique name and description.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Brand successfully created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BrandResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    public ResponseEntity<BrandResponse> createBrand(
            @Parameter(description = "Brand data to be created", required = true)
            @Valid @RequestBody BrandRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(brandHandler.save(request));
    }

    @PutMapping(path = "/v1/updateBrand/{id}")
    @Operation(summary = "Update an existing brand", description = "Updates the name and description of an existing brand by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Brand successfully updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BrandResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Brand not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    public BrandResponse updateBrand(
            @Parameter(description = "ID of the brand to be updated", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated brand data", required = true)
            @Valid @RequestBody BrandRequest request) {
        return brandHandler.update(id, request);
    }

    @DeleteMapping(path = "/v1/deleteBrand/{id}")
    @Operation(summary = "Delete a brand", description = "Deletes a brand by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Brand successfully deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Brand not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    public ResponseEntity<Void> deleteBrand(
            @Parameter(description = "ID of the brand to be deleted", required = true)
            @PathVariable Long id) {
        brandHandler.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
