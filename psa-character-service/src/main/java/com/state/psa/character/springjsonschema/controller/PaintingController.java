package com.state.psa.character.springjsonschema.controller;


import com.state.psa.character.springjsonschema.exception.ValidJson;
import com.state.psa.character.springjsonschema.painting.Painting;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.state.psa.character.springjsonschema.SchemaLocations.PAINTING;


@RestController
public class PaintingController {

    @PostMapping("/paintings")
    public ResponseEntity<Void> createPainting(@ValidJson(PAINTING) Painting painting) {
        System.out.println("Validated painting: " + painting);
        return ResponseEntity.ok().build();
    }
}