package com.bootcamp_2024_2.emazon.infrastructure.input.rest;

import com.bootcamp_2024_2.emazon.application.dto.request.ArticleRequest;
import com.bootcamp_2024_2.emazon.application.dto.response.ArticleResponse;
import com.bootcamp_2024_2.emazon.application.handler.ArticleHandler;
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
@RequestMapping("/articles")
public class ArticleRestController {

    private final ArticleHandler articleHandler;

    @GetMapping(path = "/v1/articles")
    @Operation(summary = "Retrieve paginated brands", description = "Returns a paginated list of articles sorted by the specified field and order.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved articles",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ArticleResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    public Page<ArticleResponse> getArticles(
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
        return articleHandler.findAll(pageable);
    }

    @GetMapping(path = "/v1/articles/{id}")
    @Operation(summary = "Retrieve an article by ID", description = "Returns the details of a specific article identified by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the article",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ArticleResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Article not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    public ArticleResponse getArticle(
            @Parameter(description = "ID of the article to be retrieved", example = "1")
            @PathVariable Long id) {
        return articleHandler.findById(id);
    }

    @PostMapping(path = "/v1/articles")
    @Operation(summary = "Create a new article", description = "Creates a new article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Article successfully created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ArticleResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    public ResponseEntity<ArticleResponse> createArticle(
            @Parameter(description = "Article data to be created", required = true)
            @Valid @RequestBody ArticleRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(articleHandler.save(request));
    }
}
