package com.mitocode.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.SignosVitales;
import com.mitocode.service.ISignosVitalesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/signovital")
public class SignosVitalesController {

    @Autowired
    private ISignosVitalesService service;

    @GetMapping
    public ResponseEntity<List<SignosVitales>> listarSignosVitales() throws Exception {
        List<SignosVitales> lista = service.listar();
        return new ResponseEntity<List<SignosVitales>>(lista, HttpStatus.OK);
    }

    @GetMapping("/{idSignosVitales}")
    public ResponseEntity<SignosVitales> listarPorId(@PathVariable("idSignosVitales") Integer id) throws Exception {
        SignosVitales SignosVitales = service.listarPorId(id);

        if (SignosVitales == null) {
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
        }

        return new ResponseEntity<SignosVitales>(SignosVitales, HttpStatus.OK);
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<SignosVitales> listarPorIdHateoas(@PathVariable("id") Integer id) throws Exception {
        SignosVitales obj = service.listarPorId(id);

        if (obj == null) {
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
        }

        EntityModel<SignosVitales> recurso = EntityModel.of(obj);

        // localhost:8080/pacientes/4
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).listarPorIdHateoas(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).listarPorIdHateoas(id));

        recurso.add(link1.withRel("signos-recurso1"));
        recurso.add(link2.withRel("signos-recurso2"));

        return recurso;
    }

    @PostMapping
    public ResponseEntity<SignosVitales> registrar(@Valid @RequestBody SignosVitales p) throws Exception {

        SignosVitales obj = service.registrar(p);

        // localhost:8080/pacientes/2
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getIdSignosVitales()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<SignosVitales> modificar(@Valid @RequestBody SignosVitales p) throws Exception {
        SignosVitales obj = service.modificar(p);
        return new ResponseEntity<SignosVitales>(obj, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
        SignosVitales obj = service.listarPorId(id);
        if (obj == null) {
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
        }
        service.eliminar(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<SignosVitales>> listarPageable(Pageable pageable) throws Exception {
        Page<SignosVitales> pacientes = service.listarPageable(pageable);
        return new ResponseEntity<Page<SignosVitales>>(pacientes, HttpStatus.OK);
    }
}
