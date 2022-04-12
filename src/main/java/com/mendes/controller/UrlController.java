package com.mendes.controller;

import com.mendes.model.dto.UrlDto;
import com.mendes.service.url.UrlServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mendes
 */

@RestController
@RequestMapping("url")
@Api(value = "url")
public class UrlController {

    private final UrlServiceImpl urlServiceImpl;

    public UrlController(UrlServiceImpl urlServiceImpl) {
        this.urlServiceImpl = urlServiceImpl;
    }

    @ApiOperation(value = "save")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "not found!!!"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping("/save")
    public ResponseEntity<UrlDto> save(@RequestBody UrlDto model) {
        return ResponseEntity.ok(urlServiceImpl.save(model));
    }

    @ApiOperation(value = "list")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "not found!!!"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/list")
    public ResponseEntity<List<UrlDto>> list() {
        return ResponseEntity.ok(urlServiceImpl.list());
    }

    @ApiOperation(value = "delete")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "not found!!!"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @DeleteMapping("/delete/{id}")
    public void save(@PathVariable("id") Long id) {
        urlServiceImpl.delete(id);
    }

    @ApiOperation(value = "search")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "not found!!!"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping("/search")
    public ResponseEntity<UrlDto> search(@RequestBody String shortUrl) {
        return ResponseEntity.ok(urlServiceImpl.findByShortUrl(shortUrl));
    }
}
